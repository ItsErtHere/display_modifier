package itserthere.displaymodifier.gui;

import itserthere.displaymodifier.Reference;
import itserthere.displaymodifier.gui.widgets.TransformationEntry;
import itserthere.displaymodifier.gui.widgets.TransformationListWidget;
import itserthere.displaymodifier.platform.Services;
import itserthere.displaymodifier.util.DisplayData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import org.joml.Quaternionf;
import org.lwjgl.glfw.GLFW;
import net.minecraft.world.entity.Display;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DisplayTransformationsScreen extends Screen {
    private enum SortType {
        NORMAL,
        A_TO_Z,
        Z_TO_A;

        Button button;

        Component getButtonText() {
            return Component.translatable("displaymodifier.transformations.search." + name().toLowerCase(Locale.ROOT));
        }
    }
    private static final int PADDING = 6;
    private final TransformationListWidget[] transformationListWidget = new TransformationListWidget[2];
    private TransformationListWidget.ListEntry selected = null;
    private List<TransformationEntry> transformations;
    private final List<TransformationEntry> unsortedTransformations;
    private List<TransformationEntry> userTransformations;
    private final List<TransformationEntry> unsortedUserTransformations;
    private Display entityDisplay;
    private DisplayData entityDisplayData;
    private Button applyButton;

    private final int buttonMargin = 1;
    private final int numButtons = SortType.values().length;
    public DisplayModifierScreen displayModifierScreen;
    private String lastFilterText = "";

    private EditBox search;
    private boolean sorted = false;
    private SortType sortType = SortType.NORMAL;

    public DisplayTransformationsScreen parentScreen;

    public DisplayTransformationsScreen(DisplayModifierScreen displayModifierScreen) {
        super(Component.translatable("displaymodifier.gui.transformations.title"));
        this.displayModifierScreen=displayModifierScreen;
        this.entityDisplayData = new DisplayData();
        CompoundTag tag = entityDisplay.saveWithoutId(new CompoundTag());
        if (!tag.contains("left_rotation") || tag.getCompound("left_rotation").isEmpty()) {
            tag.put("left_rotation", entityDisplayData.writeAllTransformations(entityDisplay));
        }
        //Add default transformations
        List<TransformationEntry> rawTransformations = Reference.defaultTransformationMap.entrySet().stream()
                .map(entry -> new TransformationEntry(entry.getKey(), entry.getValue(), false)).collect(Collectors.toList());
        this.unsortedTransformations = Collections.unmodifiableList(rawTransformations);
        Collections.sort(rawTransformations);
        this.transformations = Collections.unmodifiableList(rawTransformations);

        //Add user added transformations
        UserTransformationHandler.loadUserTransformations();
        List<TransformationEntry> rawUserPoses = Reference.userTransformations.stream().map(entry -> new TransformationEntry(entry, true)).collect(Collectors.toList());
        this.unsortedUserTransformations = Collections.unmodifiableList(rawUserPoses);
        Collections.sort(rawUserPoses);
        this.userTransformations = Collections.unmodifiableList(rawUserPoses);
        this.entityDisplayData.readFromNBT(tag);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        int centerWidth = this.width / 2;
        int listWidth = this.width / 4 + 20;
        int structureWidth = this.width - listWidth - (PADDING * 3);
        int closeButtonWidth = Math.min(structureWidth, 160);
        int y = this.height - 20 - PADDING;
        this.addRenderableWidget(Button.builder(Component.translatable("gui.cancel"), b -> DisplayTransformationsScreen.this.onClose())
                .bounds(centerWidth - (closeButtonWidth / 2) + PADDING, y, closeButtonWidth, 20).build());

        y -= 18 + PADDING;
        //Apply
        this.addRenderableWidget(this.applyButton = Button.builder(Component.translatable("displaymodifier.gui.transformations.selection.apply"), b -> {
            if (selected != null) {
                if (!selected.userAdded() && selected.rawName().equals("random")) {
                    //Randomize all fields but the last 3 (as those are position) but don't make the rotations too crazy
                    for (int i = 0; i < this.displayModifierScreen.transformTextFields.length; i++) {
                        //generate a random number between 0 and 2PI
                        Quaternionf randomRotation=new Quaternionf(2*Math.PI*Math.random(),2*Math.PI*Math.random(),2*Math.PI*Math.random(),1);
                        this.displayModifierScreen.transformTextFields[i].setValue(String.valueOf(randomRotation));
                    }
                } else {
                    this.displayModifierScreen.readFieldsFromNBT(selected.getTag());
                }
                this.displayModifierScreen.textFieldUpdated();
                this.displayModifierScreen.updateEntity(selected.getTag());
            }
            this.onClose();
        }).bounds(centerWidth - (closeButtonWidth / 2) + PADDING, y, closeButtonWidth, 20).build());

        y -= 14 + PADDING;
        search = new EditBox(getScreenFont(), centerWidth - listWidth / 2 + PADDING + 1, y, listWidth - 2, 14,
                Component.translatable("displaymodifier.gui.transformations.search"));

        int fullButtonHeight = PADDING + 20 + PADDING;
        this.transformationListWidget[0] = new TransformationListWidget(this, Component.translatable("displaymodifier.gui.transformations.default"), false, listWidth, fullButtonHeight, search.getY() - getScreenFont().lineHeight - PADDING);
        this.transformationListWidget[0].setX(0);
        this.transformationListWidget[0].setY(10);
        this.transformationListWidget[0].setHeight(this.height);

        this.transformationListWidget[1] = new TransformationListWidget(this, Component.translatable("displaymodifier.gui.transformations.user"), true, listWidth, fullButtonHeight, search.getY() - getScreenFont().lineHeight - PADDING);
        this.transformationListWidget[1].setX(width - listWidth);
        this.transformationListWidget[1].setY(10);
        this.transformationListWidget[1].setHeight(this.height);

        addWidget(search);
        addWidget(transformationListWidget[0]);
        addWidget(transformationListWidget[1]);
        search.setFocused(false);
        search.setCanLoseFocus(true);

        final int width = listWidth / numButtons;
        int x = centerWidth + PADDING - width;
        addRenderableWidget(SortType.A_TO_Z.button = Button.builder(SortType.A_TO_Z.getButtonText(), b ->
                        resortTransformations(SortType.A_TO_Z))
                .bounds(x, PADDING, width - buttonMargin, 20).build());
        x += width + buttonMargin;
        addRenderableWidget(SortType.Z_TO_A.button = Button.builder(SortType.Z_TO_A.getButtonText(), b ->
                        resortTransformations(SortType.Z_TO_A))
                .bounds(x, PADDING, width - buttonMargin, 20).build());

        resortTransformations(SortType.A_TO_Z);
        updateCache();
    }

    protected void readFieldsFromNBT(CompoundTag compound) {
        CompoundTag displayTag = entityDisplayData.writeToNBT();
        displayTag.merge(compound);
    }

    @Override
    public void tick() {
        if (transformationListWidget[0].children().contains(selected)) {
            transformationListWidget[0].setSelected(selected);
            transformationListWidget[1].setSelected(null);
        } else if (transformationListWidget[1].children().contains(selected)) {
            transformationListWidget[0].setSelected(null);
            transformationListWidget[1].setSelected(selected);
        }

        if (!search.getValue().equals(lastFilterText)) {
            reloadTransformations();
            sorted = false;
        }

        if (!sorted) {
            reloadTransformations();
            if (sortType == SortType.A_TO_Z) {
                Collections.sort(transformations);
                Collections.sort(userTransformations);
            } else if (sortType == SortType.Z_TO_A) {
                transformations.sort(Collections.reverseOrder());
                userTransformations.sort(Collections.reverseOrder());
            }
            transformationListWidget[0].refreshList(false);
            transformationListWidget[1].refreshList(true);
            if (selected != null) {
                selected = transformationListWidget[0].children().stream().filter(e -> e == selected).findFirst()
                        .orElse(transformationListWidget[1].children().stream().filter(e -> e == selected).findFirst().orElse(null));
            }
            sorted = true;
        }
    }

    private void reloadTransformations() {
        this.transformations = this.unsortedTransformations.stream().
                filter(entry -> entry.getName().toLowerCase(Locale.ROOT).contains(search.getValue().toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());

        this.userTransformations = this.unsortedUserTransformations.stream().
                filter(entry -> entry.getName().toLowerCase(Locale.ROOT).contains(search.getValue().toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());

        lastFilterText = search.getValue();
    }

    public <T extends ObjectSelectionList.Entry<T>> void buildTransList(Consumer<T> ListViewConsumer, Function<TransformationEntry, T> newEntry) {
        transformations.forEach(mod -> ListViewConsumer.accept(newEntry.apply(mod)));
    }

    public <T extends ObjectSelectionList.Entry<T>> void buildUserTransList(Consumer<T> ListViewConsumer, Function<TransformationEntry, T> newEntry) {
        userTransformations.forEach(mod -> ListViewConsumer.accept(newEntry.apply(mod)));
    }

    private void resortTransformations(SortType newSort) {
        this.sortType = newSort;

        for (SortType sort : SortType.values()) {
            if (sort.button != null)
                sort.button.active = sortType != sort;
        }
        sorted = false;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.transformationListWidget[0].render(guiGraphics, mouseX, mouseY, partialTicks);
        this.transformationListWidget[1].render(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        Component text = Component.translatable("displaymodifier.gui.poses.search");
        guiGraphics.drawCenteredString(getScreenFont(), text, this.width / 2 + PADDING,
                search.getY() - getScreenFont().lineHeight - 2, 0xFFFFFF);

        this.search.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_DELETE) {
            if(selected != null && selected.userAdded()) {
                this.minecraft.setScreen(new DeleteTransformationScreen(this.displayModifierScreen, selected));
            }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {}

    public void setSelected(TransformationListWidget.ListEntry entry) {
        this.selected = entry == this.selected ? null : entry;
        updateCache();
    }

    private void updateCache() {
        this.applyButton.active = selected != null;
    }

    /**
     * Clear the search field when right-clicked on it
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean flag = super.mouseClicked(mouseX, mouseY, button);
        if (button == 1 && search.isMouseOver(mouseX, mouseY)) {
            search.setValue("");
        }
        return flag;
    }

    @Override
    public void resize(Minecraft mc, int width, int height) {
        String s = this.search.getValue();
        SortType sort = this.sortType;
        TransformationListWidget.ListEntry selected = this.selected;
        this.init(mc, width, height);
        this.search.setValue(s);
        this.selected = selected;
        if (!this.search.getValue().isEmpty())
            reloadTransformations();
        if (sort != SortType.NORMAL)
            resortTransformations(sort);
        updateCache();
    }

    @Override
    public void onClose() {
        assert this.minecraft != null;
        this.minecraft.setScreen(displayModifierScreen);
    }

    public Minecraft getScreenMinecraft() {
        return this.minecraft;
    }

    public Font getScreenFont() {
        return this.font;
    }
    public void updateEntity(CompoundTag compound) {
        Services.PLATFORM.updateEntity(this.entityDisplay, compound);
    }
}

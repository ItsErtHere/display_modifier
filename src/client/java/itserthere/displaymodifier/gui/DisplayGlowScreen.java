package itserthere.displaymodifier.gui;
import itserthere.displaymodifier.GlowHandler;
import itserthere.displaymodifier.gui.widgets.DisplayGlowWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntitySelector;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DisplayGlowScreen extends Screen {
    private static final int PADDING = 6;

    private DisplayGlowWidget glowListWidget;
    private DisplayGlowWidget.ListEntry selected = null;
    private final List<Display> displays;
    private Button locateButton;
    private Button modifyButton;

    public DisplayModifierScreen parentScreen;

    public DisplayGlowScreen(DisplayModifierScreen parent) {
        super(Component.translatable("displaymodifier.gui.display_list.list"));
        this.parentScreen = parent;

        this.minecraft = Minecraft.getInstance();

        //Add the displays to the list
        if (minecraft.player == null)
            this.onClose();

        List<Display> displays = minecraft.level.getEntitiesOfClass(Display.class,
                minecraft.player.getBoundingBox().inflate(30.0D), EntitySelector.LIVING_ENTITY_STILL_ALIVE).stream().collect(Collectors.toList());
        //Sort the list based on how far the armor stand is from the player
        displays.sort((display, display2) -> {
            double distance1 = display.distanceToSqr(minecraft.player);
            double distance2 = display2.distanceToSqr(minecraft.player);
            return Double.compare(distance1, distance2);
        });
        this.displays = Collections.unmodifiableList(displays);
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
        this.addRenderableWidget(Button.builder(Component.translatable("gui.cancel"), b -> DisplayGlowScreen.this.onClose())
                .bounds(centerWidth - (closeButtonWidth / 2) + PADDING, y, closeButtonWidth, 20).build());

        y -= 18 + PADDING;
        int buttonWidth = (closeButtonWidth / 2) - 1;
        this.addRenderableWidget(this.locateButton = Button.builder(Component.translatable("displaymodifer.gui.display_list.locate"), b -> {
            if (selected != null && minecraft.player != null) {
                GlowHandler.startGlowing(this.selected.getDisplay().getUUID());
                minecraft.player.lookAt(EntityAnchorArgument.Anchor.EYES, selected.getDisplay().position());
            }
        }).bounds(centerWidth - (closeButtonWidth / 2) + PADDING, y, buttonWidth, 20).build());
        this.addRenderableWidget(this.modifyButton = Button.builder(Component.translatable("displaymodifer.gui.display_list.modify"), b -> {
            if (selected != null && minecraft.player != null) {
                minecraft.setScreen(new DisplayModifierScreen(selected.getDisplay()));
            }
        }).bounds(centerWidth - (closeButtonWidth / 2) + PADDING + buttonWidth + 2, y, buttonWidth, 20).build());

        int fullButtonHeight = PADDING + 20 + PADDING;
        this.glowListWidget = new DisplayGlowWidget(this, Component.translatable("displaymodifer.gui.display_list.list"), listWidth, fullButtonHeight, 14 - getScreenFont().lineHeight);
        this.glowListWidget.setX(0);
        this.glowListWidget.setY(10);
        this.glowListWidget.setHeight(this.height);

        addWidget(glowListWidget);

        updateCache();
    }

    @Override
    public void tick() {
        glowListWidget.setSelected(selected);
    }

    public <T extends ObjectSelectionList.Entry<T>> void buildPositionList(Consumer<T> ListViewConsumer, Function<Display, T> newEntry) {
        displays.forEach(disp -> ListViewConsumer.accept(newEntry.apply(disp)));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.parentScreen.render(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {}

    public void setSelected(DisplayGlowWidget.ListEntry entry) {
        this.selected = entry == this.selected ? null : entry;
        updateCache();
    }

    private void updateCache() {
        this.locateButton.active = selected != null;
        this.modifyButton.active = selected != null;
    }

    //Clear the search field when right-clicked on it
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void resize(Minecraft mc, int width, int height) {
        DisplayGlowWidget.ListEntry selected = this.selected;
        this.init(mc, width, height);
        this.selected = selected;
        updateCache();
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(parentScreen);
    }

    public Minecraft getScreenMinecraft() {
        return this.minecraft;
    }

    public Font getScreenFont() {
        return this.font;
    }
}

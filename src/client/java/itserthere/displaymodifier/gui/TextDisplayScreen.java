package itserthere.displaymodifier.gui;

import itserthere.displaymodifier.gui.widgets.NumberFieldBox;
import itserthere.displaymodifier.util.TextDisplayData;
import net.minecraft.client.gui.components.Button;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Display;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.math.Transformation;
import itserthere.displaymodifier.Reference;
import itserthere.displaymodifier.gui.widgets.ToggleButton;
import itserthere.displaymodifier.platform.Services;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Display;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class TextDisplayScreen extends Screen {
    private final Display.TextDisplay textDisplay;
    private final TextDisplayData textDisplayData;
    //"line_width","text_opacity","background","shadow","see_through","default_background","text","alignment"
    public String[] intLabels = new String[]{"background_color_red()",
            "background_color_green", "background_color_blue()", "background_color_alpha()",
            "line_width","text_opacity"};
    protected final NumberFieldBox[] TextModifierFields = new NumberFieldBox[5];
    public String[] boolLabels = new String[]{"shadow","see_through","default_background"};
    protected final ToggleButton[] TextToggles = new ToggleButton[3];
    public String[] editLabels = new String[]{"text","custom_name"};
    EditBox customNameBlank; EditBox textBlank;
    public static boolean contains(int[] list,int i) {
        boolean f=false;
        for(int n:list) {if (i == n) {f = true;break;}}
        return f;
    }
    private final int whiteColor = 16777215;
    protected static final Button.CreateNarration DEFAULT_NARRATION = (supplier) -> (MutableComponent)supplier.get();
    public TextDisplayScreen(Display.TextDisplay entityDisplay) {
        super(Component.translatable("displaymodifier.gui.title_text"));
        this.textDisplay=entityDisplay;
        this.textDisplayData = new TextDisplayData();
        this.textDisplayData.getTextDisplayData(entityDisplay);
    }
    @Override
    public boolean isPauseScreen() {
        return false;
    }
    @Override
    public void init() {
        super.init();
        int offsetX = 110;
        int offsetY = 20;
        // toggle buttons
        for (int i = 0; i < this.TextToggles.length; i++) {
            int x = offsetX;
            int y = offsetY + (i * 22);
            int width = 40;
            int height = 20;
            this.addRenderableWidget(this.TextToggles[i] = new ToggleButton.Builder(this.textDisplayData.getBooleanValue(i), (button) -> {
                ToggleButton toggleButton = ((ToggleButton) button);
                toggleButton.setValue(!toggleButton.getValue());
                this.textFieldUpdated();
            }).bounds(x, y, width, height).build());
            this.TextToggles[i].setTooltip(Tooltip.create(Component.translatable("displaymodifier.gui.tooltip." + boolLabels[i])));
        }
        offsetY+=2;
        //Text Fields
        this.addRenderableWidget(customNameBlank=new EditBox(
                        font,offsetX, offsetY,
                        40,20,
                        Component.translatable("display_modifier.gui.custom_name")))
                .setValue(textDisplayData.getCustomName());
        this.addRenderableWidget(textBlank=new EditBox(
                        font,offsetX, offsetY,
                        40,20,
                        Component.translatable("display_modifier.gui.text_blank")))
                .setValue(textDisplayData.getText());
        offsetX = this.width - 120;
        for (int i = 0; i < this.TextModifierFields.length; i++) {
            int x = 1 + offsetX + ((i % 4) * 35);
            int y = 1 + offsetY + ((i / 4) * 22);
            int width = 28;
            int height = 17;
            String value = String.valueOf(this.textDisplayData.intList[i]);
            this.TextModifierFields[i] = new NumberFieldBox(this.font, x, y, width, height, Component.literal(value));
            this.TextModifierFields[i].setValue(value);
            this.TextModifierFields[i].setMaxLength(4);
            this.TextModifierFields[i].scrollMultiplier = 0.01f;
            this.TextModifierFields[i].modValue = Integer.MAX_VALUE;
            this.TextModifierFields[i].decimalPoints = 2;
            this.TextModifierFields[i].allowDecimal = false;
            this.TextModifierFields[i].setMaxLength(6);
            //Set tooltip
            String tooltip="displaymodifier.gui.tooltip."+intLabels[i];
            this.TextModifierFields[i].setTooltip(Tooltip.create(Component.translatable(tooltip)));

            this.addWidget(this.TextModifierFields[i]);
        }
    }
    protected void textFieldUpdated() {
        this.updateEntity(this.textDisplayData.createTag());
    }
    protected void readFieldsFromNBT(CompoundTag compound) {
        CompoundTag displayTag = this.textDisplayData.createTag();
        displayTag.merge(compound);
        this.textDisplayData.readFromNBT(displayTag);
    }
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 15) { //Tab
            for (int i = 0; i < this.TextModifierFields.length; i++) {
                if (this.TextModifierFields[i].isFocused()) {
                    this.textFieldUpdated();
                    this.TextModifierFields[i].moveCursorToEnd(false);
                    this.TextModifierFields[i].setFocused(false);

                    int j = (!Screen.hasShiftDown() ? (i == this.TextModifierFields.length - 1 ? 0 : i + 1) : (i == 0 ? this.TextModifierFields.length - 1 : i - 1));
                    this.TextModifierFields[j].setFocused(true);
                    this.TextModifierFields[j].moveCursorTo(0, false);
                    this.TextModifierFields[j].setHighlightPos(this.TextModifierFields[j].getValue().length());
                }
            }
        } else {
            for (NumberFieldBox textField : this.TextModifierFields) {
                if (textField.keyPressed(keyCode, scanCode, modifiers)) {
                    this.textFieldUpdated();
                    return true;
                }
            }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }
    public static void openScreen(Display displayEntity) {
        Minecraft.getInstance().setScreen(new DisplayModifierScreen(displayEntity));
    }

    public void updateEntity(CompoundTag compound) {
        Services.PLATFORM.updateEntity(this.textDisplay, compound);
    }
    public static void openScreen(Display.TextDisplay displayEntity) {
        Minecraft.getInstance().setScreen(new TextDisplayScreen(displayEntity));
    }
}

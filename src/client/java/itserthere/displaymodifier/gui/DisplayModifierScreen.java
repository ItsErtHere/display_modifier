package itserthere.displaymodifier.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.math.Transformation;
import itserthere.displaymodifier.Reference;
import itserthere.displaymodifier.gui.widgets.NumberFieldBox;
import itserthere.displaymodifier.gui.widgets.ToggleButton;
import itserthere.displaymodifier.platform.Services;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import itserthere.displaymodifier.util.DisplayData;
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

import java.util.Objects;

public class DisplayModifierScreen extends Screen {
    private Button displayTypeButton;
    private EditBox customNameBlank;
    private final Display entityDisplay;
    private final DisplayData displayData;
    private final String[] buttonLabels = new String[]{"horizontal_billboard","vertical_billboard","custom_name_visible"};
    private final String[] sliderLabelsMain = new String[]{"scale","translation","left_rotation","right_rotation","glow_color_override","shadows"};
    private final String[] sliderLabelsLast = new String[]{"start_interpolation","interpolation_duration","left_rotation","right_rotation","sky_brightness"};
    private final String[] display_options = new String[]{"fixed","none","thirdperson_lefthand","thirdperson_righthand","firstperson_lefthand",
    "firstperson_righthand","head","gui","ground"};
    private final ToggleButton[] toggleButtons = new ToggleButton[buttonLabels.length];
    protected final NumberFieldBox[] transformTextFields = new NumberFieldBox[22];
    public static boolean contains(int[] list,int i) {
        boolean f=false;
        for(int n:list) {if (i == n) {f = true;break;}}
        return f;
    }
    private final int whiteColor = 16777215;
    protected static final Button.CreateNarration DEFAULT_NARRATION = (supplier) -> (MutableComponent)supplier.get();

    public DisplayModifierScreen(Display entityDisplay) {
        super(Component.translatable("displaymodifier.gui.title"));
        this.entityDisplay=entityDisplay;

        this.displayData = new DisplayData();
        CompoundTag tag = entityDisplay.saveWithoutId(new CompoundTag());
        if (!tag.contains("transformation") || tag.getCompound("transformation").isEmpty()) {
            tag.put("transformation", displayData.writeAllTransformations(entityDisplay));
        }
        this.displayData.readFromNBT(tag);
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
        for (int i = 0; i < this.toggleButtons.length; i++) {
            int x = offsetX;
            int y = offsetY + (i * 22);
            int width = 40;
            int height = 20;
            this.addRenderableWidget(this.toggleButtons[i] = new ToggleButton.Builder(this.displayData.getBooleanValue(i), (button) -> {
                ToggleButton toggleButton = ((ToggleButton) button);
                toggleButton.setValue(!toggleButton.getValue());
                this.textFieldUpdated();
            }).bounds(x, y, width, height).build());
            this.toggleButtons[i].setTooltip(Tooltip.create(Component.translatable("displaymodifier.gui.tooltip." + buttonLabels[i])));
        }
        offsetY+=2;
        this.addRenderableWidget(displayTypeButton= new Button.Builder(
                Component.translatable("displaymodifier.gui.display_type_"+displayData.getDisplayType()),
                (button)->{
                    int q=-1;
                    for(int n=0;n<display_options.length-1;n++) {if(display_options[n]== displayData.getDisplayType()) q=n;}
                    displayData.setDisplayType(display_options[(q+1)%(this.display_options.length-1)]);
                    displayTypeButton.setMessage(Component.translatable("displaymodifier.gui.display_type_"+displayData.getDisplayType()));
                }
                ).bounds(offsetX,offsetY + (this.toggleButtons.length * 22),40,20)
                .tooltip(Tooltip.create(Component.translatable(
                        "displaymodifier.gui.display_type_"+displayData.getDisplayType())))
                .build()
        );
        offsetY+=2;
        this.addRenderableWidget(customNameBlank=new EditBox(font,offsetX,
                offsetY + (this.toggleButtons.length * 22)+22,
                40,20,Component.translatable("display_modifier.gui.custom_name")))
                .setValue(displayData.getCustomName());
        // transformation textboxes
        offsetX = this.width - 120;
        for (int i = 0; i < this.transformTextFields.length; i++) {
            int x = 1 + offsetX + ((i % 4) * 35);
            int y = 1 + offsetY + ((i / 4) * 22);
            int width = 28;
            int height = 17;
            String value = String.valueOf(this.displayData.trans[i]);
            this.transformTextFields[i] = new NumberFieldBox(this.font, x, y, width, height, Component.literal(value));
            this.transformTextFields[i].setValue(value);
            this.transformTextFields[i].setMaxLength(4);
            this.transformTextFields[i].scrollMultiplier = 0.01f;
            this.transformTextFields[i].modValue = Integer.MAX_VALUE;
            this.transformTextFields[i].decimalPoints = 2;
            this.transformTextFields[i].allowDecimal = true;
            this.transformTextFields[i].setMaxLength(6);
            //Set tooltip
            String tooltip="displaymodifier.gui.tooltip.";
            if (i % 4 == 0) {
                if(i/4<4) tooltip+="x_";
                else if(i/4==4) tooltip+="red_";
                else if(i/4==5) tooltip+="shadow_radius";
            } else if (i % 4 == 1) {
                if(i/4<4) tooltip+="y_";
                else if(i/4==4) tooltip+="green_";
                else if(i/4==5) tooltip+="shadow_strength";
            } else if (i % 4 == 2) {
                if(i/4<4) tooltip+="z_";
                else if(i/4==4) tooltip+="blue_";
                else if(i/4==5) tooltip+="block_brightness";
            }
            tooltip+=transformTextFields[i];
            if(i%4==3) {tooltip="displaymodifier.gui.tooltip."+sliderLabelsLast[i];}
            this.transformTextFields[i].setTooltip(Tooltip.create(Component.translatable(tooltip)));

            this.addWidget(this.transformTextFields[i]);
        }
        offsetY = this.height / 4 + 134;
        // copy & paste buttons
        offsetX = 20;
        this.addRenderableWidget(new EditBox(font,110,80,80,20, Component.translatable("displaymodifier.gui.custom_name")));
        this.addRenderableWidget(Button.builder(Component.translatable("displaymodifer.gui.label.transformations"), (button) -> this.minecraft.setScreen(new DisplayTransformationsScreen(this)))
                .bounds(offsetX, offsetY, 130, 20)
                .tooltip(Tooltip.create(Component.translatable("displaymodifer.gui.tooltip.transformations"))).build());
        this.addRenderableWidget(Button.builder(Component.translatable("displaymodifer.gui.label.copy"), (button) -> {
            CompoundTag compound = this.writeFieldsToNBT();
            String clipboardData = compound.toString();
            if (this.minecraft != null) {
                this.minecraft.keyboardHandler.setClipboard(clipboardData);
            }
        }).bounds(offsetX, offsetY + 22, 42, 20).tooltip(Tooltip.create(Component.translatable("displaymodifier.gui.tooltip.copy"))).build());
        this.addRenderableWidget(Button.builder(Component.translatable("displaymodifer.gui.label.paste"), (button) -> {
            try {
                String clipboardData = null;
                if (this.minecraft != null) {
                    clipboardData = this.minecraft.keyboardHandler.getClipboard();
                }
                if (clipboardData != null) {
                    CompoundTag compound = TagParser.parseTag(clipboardData);
                    this.readFieldsFromNBT(compound);
                    this.updateEntity(compound);
                }
            } catch (Exception ignored) {}
        }).bounds(offsetX + 44, offsetY + 22, 42, 20).tooltip(Tooltip.create(Component.translatable("armorposer.gui.tooltip.paste"))).build());
        assert this.minecraft!=null;
        this.addRenderableWidget(Button.builder(Component.translatable("displaymodifier.gui.label.save"), (button) -> {
            this.minecraft.setScreen(new SaveTransformationScreen(this));
        }).bounds(offsetX + 88, offsetY + 22, 42, 20).tooltip(Tooltip.create(Component.translatable("armorposer.gui.tooltip.save"))).build());

        // done & cancel buttons
        this.addRenderableWidget(Button.builder(Component.translatable("gui.done"), (button) -> {
            displayData.setCustomName(customNameBlank.getValue());
            this.textFieldUpdated();
            this.minecraft.setScreen((Screen) null);
        }).bounds(offsetX - 194, offsetY + 22, 97, 20).build());
        this.addRenderableWidget(Button.builder(Component.literal("💡"), (button) -> {
            this.minecraft.setScreen(new DisplayGlowScreen(this));
        }).bounds(0, 0, 16, 16).build());
    }
    private double getDesiredOffset(double currentPositionValue, double desiredPositionValue) {
        double value = currentPositionValue - (int) currentPositionValue; //Get the decimal value
        if (value < 0) { //Make it positive if it's a negative position
            value = -value;
        }
        return desiredPositionValue - value;
    }
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        // Draw gui title
        guiGraphics.drawString(this.font, this.title, this.width / 2 - this.font.width(this.title) / 2, 10, whiteColor, true);
        // Draw textboxes
        for (EditBox textField : this.transformTextFields)
            textField.render(guiGraphics, mouseX, mouseY, partialTicks);

        int offsetY = 20;

        // left column labels
        int offsetX = 20;
        for (int i = 0; i < this.buttonLabels.length; i++) {
            int x = offsetX;
            int y = offsetY + (i * 22) + (10 - (this.font.lineHeight / 2));
            guiGraphics.drawString(this.font, I18n.get("displaymodifer.gui.label." + this.buttonLabels[i]), x, y, whiteColor, true);
        }
        // x, y, z
        guiGraphics.drawString(this.font, "X/R", offsetX + 10, 7, whiteColor, true);
        guiGraphics.drawString(this.font, "Y/G", offsetX + 45, 7, whiteColor, true);
        guiGraphics.drawString(this.font, "Z/B", offsetX + 80, 7, whiteColor, true);
        guiGraphics.drawString(this.font, "W", offsetX + 115, 7, whiteColor, true);
        //transformation textboxes
        for (int i = 0; i < this.sliderLabelsMain.length; i++) {
            String translatedLabel = I18n.get("displaymodifier.gui.label." + this.sliderLabelsMain[i]);
            int x = offsetX - this.font.width(translatedLabel) - 10;
            int y = offsetY + (i * 22) + (10 - (this.font.lineHeight / 2));
            guiGraphics.drawString(this.font, translatedLabel, x, y, whiteColor, true);
        }
        //full right labels
        for (int i = 0; i < this.sliderLabelsLast.length; i++) {
            String translatedLabel = I18n.get("displaymodifier.gui.label." + this.sliderLabelsLast[i]);
            int x = offsetX - 10;
            int y = offsetY + (i * 22) + (10 - (this.font.lineHeight / 2));
            guiGraphics.drawString(this.font, translatedLabel, x, y, whiteColor, true);
        }

        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        guiGraphics.drawString(this.font, Component.translatable("displaymodifier.gui.label.scroll"), 21, -width + 10, 11184810, true);
        poseStack.popPose();
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {}

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        boolean typed = super.charTyped(codePoint, modifiers);
        if (typed) {
            this.textFieldUpdated();
        }
        return typed;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double xScroll, double yScroll) {
        var multiplier = Screen.hasShiftDown() ? 10.0f : 1.0f;
        if (xScroll > 0 || yScroll > 0) {
            //Add 1 to the value
            for (NumberFieldBox textField : this.transformTextFields) {
                if (textField.canConsumeInput()) {
                    float nextValue = (textField.getFloat() + multiplier * textField.scrollMultiplier) % textField.modValue;
                    textField.setValue(String.valueOf(nextValue));
                    textField.setCursorPosition(0);
                    textField.setHighlightPos(0);
                    this.textFieldUpdated();
                    return true;
                }
            }
        } else if (xScroll < 0 || yScroll < 0) {
            //Remove 1 to the value
            for (NumberFieldBox textField : this.transformTextFields) {
                if (textField.canConsumeInput()) {
                    float previousValue = (textField.getFloat() - multiplier * textField.scrollMultiplier) % textField.modValue;
                    textField.setValue(String.valueOf(previousValue));
                    textField.setCursorPosition(0);
                    textField.setHighlightPos(0);
                    this.textFieldUpdated();
                    return true;
                }
            }
        }
        return super.mouseScrolled(mouseX, mouseY, xScroll, yScroll);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 15) { //Tab
            for (int i = 0; i < this.transformTextFields.length; i++) {
                if (this.transformTextFields[i].isFocused()) {
                    this.textFieldUpdated();
                    this.transformTextFields[i].moveCursorToEnd(false);
                    this.transformTextFields[i].setFocused(false);

                    int j = (!Screen.hasShiftDown() ? (i == this.transformTextFields.length - 1 ? 0 : i + 1) : (i == 0 ? this.transformTextFields.length - 1 : i - 1));
                    this.transformTextFields[j].setFocused(true);
                    this.transformTextFields[j].moveCursorTo(0, false);
                    this.transformTextFields[j].setHighlightPos(this.transformTextFields[j].getValue().length());
                }
            }
        } else {
            for (NumberFieldBox textField : this.transformTextFields) {
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
    protected void textFieldUpdated() {
        this.updateEntity(this.writeFieldsToNBT());
    }
    protected CompoundTag writeFieldsToNBT() {
        Vector3f v0=new Vector3f(DisplayData.defaultList[0],DisplayData.defaultList[1],DisplayData.defaultList[2]);
        Vector3f v1=new Vector3f(DisplayData.defaultList[4],DisplayData.defaultList[5],DisplayData.defaultList[6]);
        Quaternionf q0=new Quaternionf(DisplayData.defaultList[8],DisplayData.defaultList[9],DisplayData.defaultList[10],DisplayData.defaultList[11]);
        Quaternionf q1=new Quaternionf(DisplayData.defaultList[12],DisplayData.defaultList[13],DisplayData.defaultList[14],DisplayData.defaultList[15]);
        Transformation t0=new Transformation(v1,q0,v0,q1);
        String s=(!Objects.equals(this.displayData.getTransformation(),t0) ?"{\"transformation\":{"+
                (!Objects.equals(this.displayData.getTranslation(),v0)?"\"translation\":"+DisplayData.vector3ToTag(this.displayData.getTranslation()):"")+
                (!Objects.equals(this.displayData.getLeftRotation(),q0)?",\"left_rotation\":"+DisplayData.quaternionToTag(this.displayData.getLeftRotation()):"")+
                (!Objects.equals(this.displayData.getScale(),v1)?",\"scale\":"+DisplayData.vector3ToTag(this.displayData.getScale()):"")+
                (!Objects.equals(this.displayData.getRightRotation(),q1)?",\"left_rotation\":"+DisplayData.quaternionToTag(this.displayData.getLeftRotation()):"")+
                "},":"")+
                (!(this.displayData.getGlowColor().getRGB() ==0) ?"\"glow_color_override\":"+this.displayData.getGlowColor().getRGB():"")+
                (!(this.displayData.getInterpolationStart() ==DisplayData.defaultList[3]) ?",\"start_interpolation\":"+this.displayData.getInterpolationStart():"")+
                (!(this.displayData.getInterpolationDuration() ==DisplayData.defaultList[7]) ?",\"interpolation_duration\":"+this.displayData.getInterpolationDuration():"")+
                (!(this.displayData.getTeleportationDuration() ==DisplayData.defaultList[19]) ?",\"teleport_duration\":"+this.displayData.getTeleportationDuration():"")+
                (!(this.displayData.getViewRange() ==DisplayData.defaultList[23]) ?",\"view_range\":"+this.displayData.getViewRange():"")+
                (!(this.displayData.getShadowRadius() ==DisplayData.defaultList[27]) ?",\"shadow_radius\":"+this.displayData.getShadowRadius():"")+
                (!(this.displayData.getShadowStrength() ==DisplayData.defaultList[29]) ?",\"shadow_strength\":"+this.displayData.getShadowStrength():"")+
                (!(this.displayData.getBlockBrightness() ==DisplayData.defaultList[28]) ?",\"block_brightness\":"+this.displayData.getBlockBrightness():"")+
                (!(this.displayData.getSkyBrightness() ==DisplayData.defaultList[31]) ?",\"sky_brightness\":"+this.displayData.getSkyBrightness():"")+
                (!(this.displayData.getBillboard()=="fixed")?",\"billboard\":"+this.displayData.getBillboard():"");
        CompoundTag compoundTag= new CompoundTag();
        compoundTag.getCompound(s);
        return compoundTag;
    }
    protected void readFieldsFromNBT(CompoundTag compound) {
        CompoundTag displayTag = this.displayData.writeToNBT();
        displayTag.merge(compound);
        this.displayData.readFromNBT(displayTag);
    }


    public static void openScreen(Display displayEntity) {
        Minecraft.getInstance().setScreen(new DisplayModifierScreen(displayEntity));
    }

    public void updateEntity(CompoundTag compound) {
        Services.PLATFORM.updateEntity(this.entityDisplay, compound);
    }
}
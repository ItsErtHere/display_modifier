package itserthere.displaymodifier.gui;

import itserthere.displaymodifier.Reference;
import itserthere.displaymodifier.gui.widgets.TransformationListWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class DeleteTransformationScreen extends Screen {
    private final DisplayModifierScreen parentScreen;
    private Button deleteButton;
    private final TransformationListWidget.ListEntry entry;
    private final Component warning = Component.translatable("displaymodifier.gui.delete_transformation.message");

    public DeleteTransformationScreen(DisplayModifierScreen displayModifierScreen, TransformationListWidget.ListEntry entry) {
        super(Component.translatable("displaymodifier.gui.delete_transformation.title"));
        this.parentScreen = displayModifierScreen;
        this.entry = entry;
    }

    @Override
    protected void init() {
        this.addRenderableWidget(this.deleteButton = Button.builder(CommonComponents.GUI_YES, (button) -> {
            Reference.removeTransformation(entry.rawName());
            this.minecraft.setScreen(parentScreen);
        }).bounds(this.width / 2 - 66, this.height / 2 + 3, 60, 20).build());

        this.addRenderableWidget(Button.builder(CommonComponents.GUI_NO, (button) -> {
            this.minecraft.setScreen(parentScreen);
        }).bounds(this.width / 2 - 4, this.height / 2 + 3, 60, 20).build());
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        this.entry.renderTransf(guiGraphics, this.width / 2 - 5, this.height / 2 - 10, 30);

        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 16777215);
        guiGraphics.drawCenteredString(this.font, this.warning, this.width / 2, 40, 11141120);
    }
}

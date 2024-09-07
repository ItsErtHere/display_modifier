package itserthere.displaymodifier.gui.widgets;

import itserthere.displaymodifier.Reference;
import itserthere.displaymodifier.gui.DisplayTransformationsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Display;
import net.minecraft.world.level.Level;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.function.Function;

public class TransformationListWidget extends ObjectSelectionList<TransformationListWidget.ListEntry> {
    private static final Vector3f TRANSLATION = new Vector3f();
    private static final Quaternionf ROTATION = new Quaternionf().rotationXYZ(0.0F, 0.0F, 0.0F);

    private final DisplayTransformationsScreen parent;
    private final int listWidth;
    private final Component title;

    public TransformationListWidget(DisplayTransformationsScreen parent, Component title, boolean user, int listWidth, int top, int bottom) {
        super(parent.getScreenMinecraft(), listWidth, bottom - top, top, parent.getScreenFont().lineHeight * 2 + 16);
        this.parent = parent;
        this.title = title;
        this.listWidth = listWidth;
        this.refreshList(user);
    }

    @Override
    protected int getScrollbarPosition() {
        return this.getX() + this.listWidth - 6;
    }

    @Override
    public int getRowWidth() {
        return this.listWidth;
    }

    public void refreshList(boolean user) {
        this.clearEntries();
        if (user)
            parent.buildUserTransList(this::addEntry, location -> new ListEntry(location, this.parent));
        else
            parent.buildTransList(this::addEntry, location -> new ListEntry(location, this.parent));
    }

    @Override
    protected void renderSelection(GuiGraphics guiGraphics, int top, int width, int height, int outerColor, int innerColor) {
        int xPos = this.getX() + (this.width - width) / 2;
        int xPos2 = this.getX() + (this.width + width) / 2;
        guiGraphics.fillGradient(xPos, top - 2, xPos2, top + height + 2, -1945083888, -1676648432);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.fillGradient(getX(), 0, getX() + this.listWidth, parent.height, -1945104368, -1676668912);
        super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);
        guiGraphics.drawCenteredString(this.parent.getScreenFont(), title, getX() + this.listWidth / 2, 2, 16777215);
    }

    public class ListEntry extends Entry<ListEntry> {
        private final TransformationEntry transfEntry;
        private final DisplayTransformationsScreen parent;
        private LivingEntity cachedEntity;

        ListEntry(TransformationEntry entry, DisplayTransformationsScreen parent) {
            this.transfEntry = entry;
            this.parent = parent;

            Minecraft mc = parent.getScreenMinecraft();
            if (mc == null) {
                Reference.LOGGER.error("Minecraft is null, cannot create pose entry {}", entry.tdata().name());
                return;
            }
            Level level = mc.hasSingleplayerServer() && mc.getSingleplayerServer() != null ? mc.getSingleplayerServer().getAllLevels().iterator().next() : mc.level;
            if (level != null) {
                try {
                    CompoundTag tag = TagParser.parseTag(entry.tdata().data());

                    CompoundTag nbt = new CompoundTag();
                    nbt.putString("id", "minecraft:block_display");
                    if (!tag.isEmpty()) {
                        nbt.merge(tag);
                    }
                    Display display = (Display) EntityType.loadEntityRecursive(nbt,level,Function.identity());
                } catch (Exception e) {
                    Reference.LOGGER.error("Unable to parse nbt transformation {}", e.getMessage());
                }
            }
        }

        @Override
        public void render(GuiGraphics guiGraphics, int entryIdx, int top, int left, int entryWidth, int entryHeight,
                           int mouseX, int mouseY, boolean hovered, float partialTicks) {
            Font font = this.parent.getScreenFont();
            renderScrollingString(guiGraphics, font, Component.literal(getName()), left + 36, top + 10, left + width - 18, top + 20, 0xFFFFFF);

            renderTransf(guiGraphics, left + 16, top + 28, 15);
        }

        public void renderTransf(GuiGraphics guiGraphics, int xPos, int yPos, int size) {
            if (cachedEntity != null) {
                InventoryScreen.renderEntityInInventory(guiGraphics, xPos, yPos, size,
                        TRANSLATION, ROTATION, (Quaternionf) null, this.cachedEntity);
            }
        }

        @Override
        public void renderBack(GuiGraphics guiGraphics, int mouseX, int mouseY, int $$3, int $$4, int $$5, int $$6, int $$7, boolean $$8, float $$9) {
            super.renderBack(guiGraphics, mouseX, mouseY, $$3, $$4, $$5, $$6, $$7, $$8, $$9);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            parent.setSelected(this);
            if (TransformationListWidget.this.getSelected() == this)
                TransformationListWidget.this.setSelected(null);
            else
                TransformationListWidget.this.setSelected(this);
            return false;
        }

        public CompoundTag getTag() {
            return transfEntry.getTag();
        }

        public String getName() {
            return transfEntry.getName();
        }

        public boolean userAdded() {
            return transfEntry.userAdded();
        }

        public String rawName() {
            return transfEntry.tdata().name();
        }

        @Override
        public Component getNarration() {
            return Component.literal(getName());
        }
    }
}
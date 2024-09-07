package itserthere.displaymodifier.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public record SyncData(UUID entityUUID, CompoundTag tag) {
    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(entityUUID);
        buf.writeNbt(tag);
    }

    public static SyncData decode(final FriendlyByteBuf packetBuffer) {
        return new SyncData(packetBuffer.readUUID(), packetBuffer.readNbt());
    }

    public void handleData(Display display, Player player) {
        CompoundTag entityTag = display.saveWithoutId(new CompoundTag());
        CompoundTag entityTagCopy = entityTag.copy();

        if (!tag.isEmpty()) {
            entityTagCopy.merge(tag);
            display.load(entityTagCopy);
            display.setUUID(entityUUID);
        }
    }
}

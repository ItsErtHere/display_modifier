package itserthere.displaymodifier.packets;

import itserthere.displaymodifier.Reference;
import itserthere.displaymodifier.data.SyncData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;


public record DisplaySyncPayload(SyncData data) implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, DisplaySyncPayload> CODEC = CustomPacketPayload.codec(
            DisplaySyncPayload::write,
            DisplaySyncPayload::new);
    public static final Type<DisplaySyncPayload> ID = new Type<>(Reference.SYNC_PACKET_ID);

    public DisplaySyncPayload(final FriendlyByteBuf packetBuffer) {
        this(SyncData.decode(packetBuffer));
    }

    public void write(FriendlyByteBuf buf) {
        data.encode(buf);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
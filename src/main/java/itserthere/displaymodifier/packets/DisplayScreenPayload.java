package itserthere.displaymodifier.packets;
import itserthere.displaymodifier.Reference;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
public record DisplayScreenPayload(int entityID) implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, DisplayScreenPayload> CODEC = CustomPacketPayload.codec(
            DisplayScreenPayload::write,
            DisplayScreenPayload::new);
    public static final Type<DisplayScreenPayload> ID = new Type<>(Reference.SCREEN_PACKET_ID);
    public DisplayScreenPayload(final FriendlyByteBuf packetBuffer) {
        this(packetBuffer.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(entityID);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}

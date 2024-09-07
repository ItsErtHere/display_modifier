package itserthere.displaymodifier;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;


public class EventHandler {
    private static boolean cancelRightClick = false;

    public static InteractionResult onPlayerEntityInteractSpecific(Player player, Entity target, InteractionHand hand) {
        if (target instanceof Display display) {
            if (player.isShiftKeyDown()) {
                if (hand == InteractionHand.MAIN_HAND && !player.level().isClientSide) {
                    ServerPlayNetworking.send((ServerPlayer) player, new DisplayScreenPayload(display.getId()));
                }
                return InteractionResult.SUCCESS;
            } else {
                ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (!stack.isEmpty() && stack.getItem() == Items.NAME_TAG && stack.has(DataComponents.CUSTOM_NAME)) {
                    cancelRightClick = true;
                    if (hand == InteractionHand.MAIN_HAND && !player.level().isClientSide) {
                        display.setCustomName(stack.getHoverName());
                        display.setCustomNameVisible(true);
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    public static InteractionResultHolder<ItemStack> onPlayerRightClickItem(Player player, InteractionHand hand) {
        if (cancelRightClick) {
            cancelRightClick = false;
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

}

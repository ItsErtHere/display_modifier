package itserthere.displaymodifier;

import itserthere.displaymodifier.gui.DisplayModifierScreen;
import itserthere.displaymodifier.gui.TextDisplayScreen;
import net.minecraft.client.KeyMapping;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3f;

import java.util.Objects;

public class DisplayModifierClient implements ClientModInitializer {
    boolean b0=false;
    boolean b1=false;
    private static final KeyMapping bindBlock=new KeyMapping(
            "displaymodifier.keys.block_display_screen",66,
            "displaymodifier.displays"
    );
    private static final KeyMapping bindText=new KeyMapping(
            "displaymodifier.keys.text_display_screen",67,
            "displaymodifier.displays"
    );
    public static BlockPos getBlockLookingAt(Player player1) {
        player1.
    }
    public static Display.BlockDisplay summonBlockDisplay(Player player1, BlockState blockState) {
        Display.BlockDisplay displ=new Display.BlockDisplay(EntityType.BLOCK_DISPLAY,);
    }
    public static Display.ItemDisplay summonItemDisplay(Player player1) {
        //
    }
    public static Display.TextDisplay summonTextDisplay(Player player1) {
        //
    }
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Player player=Minecraft.getInstance().player;
            Display displ;
            if (bindBlock.isDown()) {b0=true;}
            if(b0&!bindBlock.isDown()) {
                b0=false;
                assert player != null;
                if(player.isCrouching()) {
                    displ=summonItemDisplay(player);
                } else displ=summonBlockDisplay(player);
                DisplayModifierScreen.openScreen(null);
            }
            if (bindText.isDown()) {b1=true;}
            if(b1&!bindText.isDown()) {b1=false;TextDisplayScreen.openScreen(null);}
        });
    }
}

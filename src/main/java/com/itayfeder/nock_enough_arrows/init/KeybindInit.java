package com.itayfeder.nock_enough_arrows.init;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.network.QuiverSwitchSelectionMessage;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NockEnoughArrowsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeybindInit {
    public static KeyMapping SWITCH_ARROW_KEYBIND;

    public static void init() {
        SWITCH_ARROW_KEYBIND = registerKey("switch_arrow_key", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_B);
    }

    private static KeyMapping registerKey(String name, String category, int keycode) {
        final var key = new KeyMapping("key." + NockEnoughArrowsMod.MOD_ID+ "." + name, keycode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }

    @SubscribeEvent
    public static void handleKeyInputEvent(TickEvent.ClientTickEvent event) {
        if (KeybindInit.SWITCH_ARROW_KEYBIND.consumeClick()) {
            NockEnoughArrowsMod.PACKET_HANDLER.sendToServer(new QuiverSwitchSelectionMessage());
        }
    }
}

package com.itayfeder.nock_enough_arrows.init;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.network.QuiverSwitchSelectionMessage;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItem;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItemStackHandler;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = NockEnoughArrowsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeybindInit {
    public static KeyMapping SWITCH_ARROW_KEYBIND = new KeyMapping("key." + NockEnoughArrowsMod.MOD_ID + ".switch_arrow_key", InputConstants.KEY_B, KeyMapping.CATEGORY_GAMEPLAY);

    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(SWITCH_ARROW_KEYBIND);
    }

    @SubscribeEvent
    public static void handleKeyInputEvent(TickEvent.ClientTickEvent event) {
        if (KeybindInit.SWITCH_ARROW_KEYBIND.consumeClick()) {
            NockEnoughArrowsMod.PACKET_HANDLER.sendToServer(new QuiverSwitchSelectionMessage());
        }
    }
}

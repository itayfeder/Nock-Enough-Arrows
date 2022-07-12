package com.itayfeder.nock_enough_arrows.events;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NockEnoughArrowsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorEvents {
    @SubscribeEvent
    public static void registerColorHandlers(RegisterColorHandlersEvent.Item event) {

        registerItemColorHandlers(event.getItemColors());
    }

    private static void registerItemColorHandlers(final ItemColors itemColors) {
        itemColors.register((stack, color) -> {
            return color > 0 ? -1 : ((DyeableLeatherItem)stack.getItem()).getColor(stack);
        }, ItemInit.QUIVER.get());
    }
}

package com.itayfeder.nock_enough_arrows.events;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.compat.curios.CuriosCompat;
import com.itayfeder.nock_enough_arrows.init.EnchantmentInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItem;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItemStackHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NockEnoughArrowsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ArrowEvents {
    @SubscribeEvent
    public static void arrowReleaseHandler(ArrowLooseEvent event) {
        ItemStack usedQuiver = null;
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            if (ModList.get().isLoaded("curios")) {
                usedQuiver = CuriosCompat.getQuiver(player);
            }
            for(int i = 0; i < player.getInventory().getContainerSize() && usedQuiver == null; ++i) {
                ItemStack itemstack1 = player.getInventory().getItem(i);
                if (itemstack1.is(ItemInit.QUIVER.get())) {
                    usedQuiver = itemstack1;
                    break;
                }
            }

            if (usedQuiver != null) {
                int cycleLevel = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.CYCLE.get(), usedQuiver);
                if (cycleLevel > 0) {
                    QuiverItemStackHandler quiverItemStackHandler = QuiverItem.getQuiverItemStackHandler(usedQuiver);
                    if (!quiverItemStackHandler.isAllAir()) {
                        int selection = QuiverItem.getSelected(usedQuiver);
                        while (quiverItemStackHandler.isAir((selection + 1) % quiverItemStackHandler.getSlots())) {
                            selection++;
                        }
                        QuiverItem.setSelected(usedQuiver, (selection + 1) % quiverItemStackHandler.getSlots());
                    }
                }


            }
        }

    }
}

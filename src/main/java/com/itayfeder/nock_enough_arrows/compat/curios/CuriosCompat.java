package com.itayfeder.nock_enough_arrows.compat.curios;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.init.EnchantmentInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import com.itayfeder.nock_enough_arrows.network.QuiverSwitchSelectionMessage;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItem;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItemStackHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.InterModComms;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

public class CuriosCompat {
    public static void InqueueIMC() {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BELT.getMessageBuilder().build());
    }

    public static void Mixin(ItemStack p_36349_, CallbackInfoReturnable<ItemStack> cir, Predicate predicate, ItemStack itemstack, int i, LivingEntity entity, Inventory inventory) {
        Optional<ImmutableTriple<String, Integer, ItemStack>> triplet = CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.QUIVER.get(), entity);
        if (!triplet.isEmpty()) {
            ImmutableTriple<String, Integer, ItemStack> actualTriplet = triplet.get();
            ItemStack itemstack1 = actualTriplet.getRight();
            if (itemstack1.getItem() instanceof QuiverItem && !inventory.player.level.isClientSide) {
                CompoundTag capTag = ((QuiverItem) itemstack1.getItem()).getShareTag(itemstack1).getCompound("cap");
                QuiverItemStackHandler quiverItemStackHandler = ((QuiverItem) itemstack1.getItem()).getQuiverItemStackHandler(itemstack1);
                int selectedSlot = ((QuiverItem) itemstack1.getItem()).getSelected(itemstack1);
                if (predicate.test(quiverItemStackHandler.getStackInSlot(selectedSlot))) {
                    ItemStack ist = quiverItemStackHandler.getStackInSlot(selectedSlot);
                    int recoveryLevel = itemstack1.getEnchantmentLevel(EnchantmentInit.RECOVERY.get());
                    Random rnd = new Random();

                    if (rnd.nextInt(100) < recoveryLevel * 5) {

                        cir.setReturnValue(ist.copy());
                    }
                    else {
                        cir.setReturnValue(ist);
                    }

                }

            }
        }
    }

    public static ItemStack getQuiver(Player player) {
        Optional<ImmutableTriple<String, Integer, ItemStack>> triplet = CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.QUIVER.get(), player);
        if (!triplet.isEmpty()) {
            return triplet.get().getRight();
        }
        return null;
    }
}

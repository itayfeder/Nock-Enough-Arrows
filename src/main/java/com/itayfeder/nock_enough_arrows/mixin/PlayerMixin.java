package com.itayfeder.nock_enough_arrows.mixin;

import com.itayfeder.nock_enough_arrows.init.EnchantmentInit;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItem;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItemStackHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;
import java.util.function.Predicate;

@Mixin(Player.class)
public class PlayerMixin {
    @Shadow
    public Inventory inventory;

    @Inject(locals = LocalCapture.CAPTURE_FAILSOFT,
            method = "getProjectile",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/entity/player/Inventory;getItem(I)Lnet/minecraft/world/item/ItemStack;"),
            cancellable = true
    )
    private void getProjectileInjection(ItemStack p_213356_1_, CallbackInfoReturnable<ItemStack> ci, Predicate<ItemStack> predicate, ItemStack itemstack, int i, ItemStack itemstack1) {
        if (itemstack1.getItem() instanceof QuiverItem && !inventory.player.level.isClientSide) {
            CompoundTag capTag = ((QuiverItem) itemstack1.getItem()).getShareTag(itemstack1).getCompound("cap");
            QuiverItemStackHandler quiverItemStackHandler = ((QuiverItem) itemstack1.getItem()).getQuiverItemStackHandler(itemstack1);
            for (int x = 0; x < quiverItemStackHandler.getSlots(); x++) {
                if (predicate.test(quiverItemStackHandler.getStackInSlot(x))) {
                    ItemStack ist = quiverItemStackHandler.getStackInSlot(x);
                    int recoveryLevel = itemstack1.getEnchantmentLevel(EnchantmentInit.RECOVERY.get());
                    Random rnd = new Random();
                    if (rnd.nextInt(100) < recoveryLevel * 5) {
                        ci.setReturnValue(ist.copy());
                        System.out.println("WEEEEEEEEEEEEEEEEEEEE");
                        break;
                    }
                    else {
                        ci.setReturnValue(ist);
                        break;
                    }

                }
            }

        }
    }
}

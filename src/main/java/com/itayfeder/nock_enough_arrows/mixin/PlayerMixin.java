package com.itayfeder.nock_enough_arrows.mixin;

import com.itayfeder.nock_enough_arrows.init.EnchantmentInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItem;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItemStackHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    @Shadow
    public Inventory inventory;

    @Shadow protected abstract boolean freeAt(BlockPos p_36351_);

    protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Inject(locals = LocalCapture.CAPTURE_FAILSOFT,
            method = "getProjectile",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/entity/player/Inventory;getItem(I)Lnet/minecraft/world/item/ItemStack;"),
            cancellable = true
    )
    private void getProjectileInjection(ItemStack p_213356_1_, CallbackInfoReturnable<ItemStack> cir, Predicate<ItemStack> predicate, ItemStack itemstack, int i, ItemStack itemstack1) {
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

    @Inject(locals = LocalCapture.CAPTURE_FAILSOFT,
            method = "getProjectile",
            at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/world/entity/player/Inventory;getContainerSize()I"),
            cancellable = true
    )
    private void getProjectileInjection2(ItemStack p_36349_, CallbackInfoReturnable<ItemStack> cir, Predicate predicate, ItemStack itemstack, int i) {
        if (ModList.get().isLoaded("curios")) {
            Optional<ImmutableTriple<String, Integer, ItemStack>> triplet = CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.QUIVER.get(), this);
            if (!triplet.isEmpty()) {
                ImmutableTriple<String, Integer, ItemStack> actualTriplet = triplet.get();
                ItemStack itemstack1 = actualTriplet.getRight();
                if (itemstack1.getItem() instanceof QuiverItem && !inventory.player.level.isClientSide) {
                    CompoundTag capTag = ((QuiverItem) itemstack1.getItem()).getShareTag(itemstack1).getCompound("cap");
                    QuiverItemStackHandler quiverItemStackHandler = ((QuiverItem) itemstack1.getItem()).getQuiverItemStackHandler(itemstack1);
                    int selectedSlot = ((QuiverItem) itemstack1.getItem()).getSelected(itemstack1);
//                    for (int x = 0; x < quiverItemStackHandler.getSlots(); x++) {
//                        if (predicate.test(quiverItemStackHandler.getStackInSlot(x))) {
//                            ItemStack ist = quiverItemStackHandler.getStackInSlot(x);
//                            int recoveryLevel = itemstack1.getEnchantmentLevel(EnchantmentInit.RECOVERY.get());
//                            Random rnd = new Random();
//                            if (rnd.nextInt(100) < recoveryLevel * 5) {
//                                cir.setReturnValue(ist.copy());
//                                break;
//                            }
//                            else {
//                                cir.setReturnValue(ist);
//                                break;
//                            }
//
//                        }
//                    }
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


    }
}
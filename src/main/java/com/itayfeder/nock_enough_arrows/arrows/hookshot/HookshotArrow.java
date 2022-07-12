package com.itayfeder.nock_enough_arrows.arrows.hookshot;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class HookshotArrow extends AbstractArrow {
    public HookshotArrow(EntityType<? extends HookshotArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public HookshotArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.HOOKSHOT_ARROW.get(), p_37420_, p_37419_);
    }

    public HookshotArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.HOOKSHOT_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public HookshotArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.HOOKSHOT_ARROW.get(), world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.HOOKSHOT_ARROW.get());
    }

    @Override
    protected void doPostHurtEffects(LivingEntity p_36744_) {
        super.doPostHurtEffects(p_36744_);
        if (!this.level.isClientSide) {
            if (p_36744_ instanceof Mob) {
                ((Mob)p_36744_).setLeashedTo(this.getOwner(), true);
            }
        }
        this.kill();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_36761_) {
        super.readAdditionalSaveData(p_36761_);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_36772_) {
        super.addAdditionalSaveData(p_36772_);
    }
}

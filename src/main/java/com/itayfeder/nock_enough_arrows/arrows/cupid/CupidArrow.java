package com.itayfeder.nock_enough_arrows.arrows.cupid;

import com.itayfeder.nock_enough_arrows.arrows.seeker.SeekerArrow;
import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

import java.util.List;
import java.util.function.Predicate;

public class CupidArrow extends AbstractArrow {
    public CupidArrow(EntityType<? extends CupidArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public CupidArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.CUPID_ARROW.get(), p_37420_, p_37419_);
    }

    public CupidArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.CUPID_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public CupidArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.CUPID_ARROW.get(), world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.CUPID_ARROW.get());
    }


    public void tick() {
        super.tick();
        if (this.level.isClientSide && !this.inGround) {
            this.level.addParticle(ParticleTypes.HEART, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
        this.setBaseDamage(0);
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        if (p_36757_.getEntity() instanceof Animal animal) {
            if (this.getOwner() instanceof Player player) {
                animal.setInLove(player);
            } else {
                animal.setInLove(null);
            }

        }
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
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

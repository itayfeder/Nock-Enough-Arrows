package com.itayfeder.nock_enough_arrows.arrows.teleportation;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

import java.util.Random;

public class TeleportationArrow extends AbstractArrow {
    public TeleportationArrow(EntityType<? extends TeleportationArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
        this.setBaseDamage(1D);
    }

    public TeleportationArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.TELEPORTATION_ARROW.get(), p_37420_, p_37419_);
        this.setBaseDamage(1D);
    }

    public TeleportationArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.TELEPORTATION_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
        this.setBaseDamage(1D);
    }

    public TeleportationArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.TELEPORTATION_ARROW.get(), world);
        this.setBaseDamage(1D);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.TELEPORTATION_ARROW.get());
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide && !this.inGround) {
            this.level.addParticle(ParticleTypes.PORTAL, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }

    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
        this.teleport();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        this.teleport();
    }

    private void teleport() {
        Random rnd = new Random();
        for (int i = 0; i < 15; i++) {
            this.level.addParticle(ParticleTypes.PORTAL, true, this.getX() + (rnd.nextFloat() * (rnd.nextBoolean() ? -1 : 1)), this.getY() + (rnd.nextFloat() * (rnd.nextBoolean() ? -1 : 1)), this.getZ() + (rnd.nextFloat() * (rnd.nextBoolean() ? -1 : 1)), 0.0D, 0.0D, 0.0D);
        }
        if (!this.level.isClientSide) {
            this.getOwner().moveTo(this.getX() ,this.getY() ,this.getZ());
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

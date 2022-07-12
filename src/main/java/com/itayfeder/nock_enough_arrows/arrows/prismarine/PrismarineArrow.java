package com.itayfeder.nock_enough_arrows.arrows.prismarine;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.PlayMessages;

public class PrismarineArrow extends AbstractArrow {
    private static final EntityDataAccessor<Boolean> IN_WATER = SynchedEntityData.defineId(PrismarineArrow.class, EntityDataSerializers.BOOLEAN);
    private boolean handled = false;
    private double origDamage = this.getBaseDamage();

    public void setInWater(boolean par1)
    {
        this.entityData.set(IN_WATER, par1);
    }

    public boolean getInWater()
    {
        return this.entityData.get(IN_WATER);
    }

    public PrismarineArrow(EntityType<? extends PrismarineArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public PrismarineArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.PRISMARINE_ARROW.get(), p_37420_, p_37419_);
    }

    public PrismarineArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.PRISMARINE_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public PrismarineArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.PRISMARINE_ARROW.get(), world);
    }

    public void tick() {
        super.tick();
        if (!this.inGround) {
            this.setInWater(this.level.getBlockState(this.getOnPos()).getBlock() == Blocks.WATER);

            if (this.getInWater()) {
                this.removeTag("SlowAirHandled");
                if (!this.getTags().contains("FastWaterHandled")) {
                    this.setDeltaMovement(this.getDeltaMovement().multiply(2, 2, 2));
                }
                this.setBaseDamage(origDamage * 2);
                this.setNoGravity(this.getInWater());
                this.addTag("FastWaterHandled");

                if (this.xo == this.getX() && this.yo == this.getY() && this.zo == this.getZ() && !this.getTags().contains("FinishedMoving")) {
                    this.addTag("FinishedMoving");
                }
                if (this.getTags().contains("FinishedMoving")) {
                    this.setNoGravity(false);
                }
            }
            if (!this.getInWater()) {
                this.removeTag("FastWaterHandled");
                if (!this.getTags().contains("SlowAirHandled")) {
                    this.setDeltaMovement(this.getDeltaMovement().multiply(0.5, 0.5, 0.5));
                }
                this.setBaseDamage(origDamage / 2);
                this.setNoGravity(this.getInWater());
                this.addTag("SlowAirHandled");
            }
        }

    }

    @Override
    protected float getWaterInertia() {
        return 0.99F;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.PRISMARINE_ARROW.get());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IN_WATER, false);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_36761_) {
        super.readAdditionalSaveData(p_36761_);
        this.setInWater(p_36761_.getBoolean("InWater"));

    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_36772_) {
        super.addAdditionalSaveData(p_36772_);
        p_36772_.putBoolean("InWater", this.getInWater());

    }
}
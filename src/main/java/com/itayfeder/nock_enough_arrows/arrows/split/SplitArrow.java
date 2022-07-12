package com.itayfeder.nock_enough_arrows.arrows.split;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class SplitArrow extends AbstractArrow {
    private float explosionRadius = 1.5F;

    public SplitArrow(EntityType<? extends SplitArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public SplitArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.SPLIT_ARROW.get(), p_37420_, p_37419_);
    }

    public SplitArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.SPLIT_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public SplitArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.SPLIT_ARROW.get(), world);
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide && !this.inGround) {
            this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.ARROW)), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }

    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.SPLIT_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
        this.explode((float)p_36757_.getEntity().getX(), (float)p_36757_.getEntity().getY(), (float)p_36757_.getEntity().getZ());
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        this.explode(p_36755_.getBlockPos().getX(), p_36755_.getBlockPos().getY(), p_36755_.getBlockPos().getZ());
    }

    private void explode(float x, float y, float z) {
        for (int i = 0; i < 360; i += 45) {
            double xMov = Math.cos(Math.toRadians(i));
            double zMov = Math.sin(Math.toRadians(i));
            Arrow arrow = new Arrow(this.level, x, y+1, z);
            arrow.setXRot(i);
            arrow.setYRot(25);
            arrow.yRotO = this.getYRot();
            arrow.xRotO = this.getXRot();
            arrow.refreshDimensions();
            arrow.setDeltaMovement((double)xMov, (double)0.20, (double)zMov);
            this.level.addFreshEntity(arrow);
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

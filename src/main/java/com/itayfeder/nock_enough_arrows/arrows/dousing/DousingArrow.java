package com.itayfeder.nock_enough_arrows.arrows.dousing;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class DousingArrow extends AbstractArrow {

    public DousingArrow(EntityType<? extends DousingArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public DousingArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.DOUSING_ARROW.get(), p_37420_, p_37419_);
    }

    public DousingArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.DOUSING_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public DousingArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.DOUSING_ARROW.get(), world);
    }

    public void tick() {
        super.tick();
        this.clearFire();
        if (this.level.isClientSide && !this.inGround) {
            this.level.addParticle(ParticleTypes.FALLING_WATER, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
        if (!this.level.isClientSide) {
            if (this.level.getBlockState(new BlockPos(position())).getBlock() == Blocks.LAVA) {
                this.level.setBlockAndUpdate(new BlockPos(position()), Blocks.OBSIDIAN.defaultBlockState());
            }
            if (this.level.getBlockState(new BlockPos(position())).getBlock() == Blocks.FIRE) {

                this.level.setBlockAndUpdate(new BlockPos(position()), Blocks.AIR.defaultBlockState());
            }
        }

    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.DOUSING_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
        if (p_36757_.getEntity() instanceof LivingEntity living) {
            if (living.isSensitiveToWater()) super.onHitEntity(p_36757_);
        }
        p_36757_.getEntity().clearFire();
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.SPLASH_POTION_BREAK;
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

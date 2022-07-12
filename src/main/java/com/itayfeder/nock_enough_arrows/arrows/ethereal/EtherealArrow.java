package com.itayfeder.nock_enough_arrows.arrows.ethereal;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsTags;
import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.network.PlayMessages;

public class EtherealArrow extends AbstractArrow {
    private static final EntityDataAccessor<Boolean> CLIPPED = SynchedEntityData.defineId(EtherealArrow.class, EntityDataSerializers.BOOLEAN);

    public boolean getClipped() { return this.entityData.get(CLIPPED); }
    public void setClipped(boolean par1) { this.entityData.set(CLIPPED, par1); }

    public EtherealArrow(EntityType<? extends EtherealArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public EtherealArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.ETHEREAL_ARROW.get(), p_37420_, p_37419_);
    }

    public EtherealArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.ETHEREAL_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public EtherealArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.ETHEREAL_ARROW.get(), world);
    }

    public void tick() {
        super.tick();
        this.setPierceLevel((byte)1);
        if (this.level.isClientSide && !this.inGround) {
            this.level.addParticle(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
        if (!this.level.isClientSide) {
            if (this.level.getBlockState(new BlockPos(position())).is(NockEnoughArrowsTags.ModBlockTags.AIRS)) {
                if (this.isNoPhysics()) setClipped(true);
                this.setNoPhysics(false);
            }
        }

    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        if (this.isNoPhysics()) this.setNoPhysics(false);
        super.onHitEntity(p_36757_);
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        Block currentBlock = this.level.getBlockState(p_36755_.getBlockPos()).getBlock();
        Vec2 rotation = getRotationVector();
        if (currentBlock != Blocks.BEDROCK && currentBlock != Blocks.CRYING_OBSIDIAN && !getClipped()) {
            this.setNoPhysics(true);
            this.setRot(rotation.x,rotation.y);
        }
        else
            super.onHitBlock(p_36755_);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.ETHEREAL_ARROW.get());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CLIPPED, false);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_36761_) {
        super.readAdditionalSaveData(p_36761_);
        if (p_36761_.contains("Clipped")) {
            this.setClipped(p_36761_.getBoolean("Clipped"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_36772_) {
        super.addAdditionalSaveData(p_36772_);
        p_36772_.putBoolean("Clipped", this.getClipped());

    }
}

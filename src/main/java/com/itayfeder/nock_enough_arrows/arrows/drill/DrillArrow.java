package com.itayfeder.nock_enough_arrows.arrows.drill;

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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;

public class DrillArrow extends AbstractArrow {
    private static final EntityDataAccessor<Boolean> BLOCKS_BROKEN = SynchedEntityData.defineId(DrillArrow.class, EntityDataSerializers.BOOLEAN);

    public boolean getBroken() { return this.entityData.get(BLOCKS_BROKEN); }
    public void setBroken(boolean par1) { this.entityData.set(BLOCKS_BROKEN, par1); }

    public DrillArrow(EntityType<? extends DrillArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public DrillArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.DRILL_ARROW.get(), p_37420_, p_37419_);
    }

    public DrillArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.DRILL_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public DrillArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.DRILL_ARROW.get(), world);
    }

    public void tick() {
        super.tick();
        this.setPierceLevel((byte)1);
        if (this.level.isClientSide && !this.inGround) {
            //this.level.addParticle(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }

    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        Vec2 rotation = getRotationVector();
        if (!getBroken() && (isCorrectToolForDrops(this.level.getBlockState(p_36755_.getBlockPos()), new ItemStack(Items.IRON_PICKAXE)) || isCorrectToolForDrops(this.level.getBlockState(p_36755_.getBlockPos()), new ItemStack(Items.IRON_AXE)) || isCorrectToolForDrops(this.level.getBlockState(p_36755_.getBlockPos()), new ItemStack(Items.IRON_SHOVEL)))) {
            setBroken(true);
            this.level.destroyBlock(p_36755_.getBlockPos(), true);
            this.setRot(rotation.x,rotation.y);
        }
        else
            super.onHitBlock(p_36755_);
    }

    public static boolean isCorrectToolForDrops(@NotNull BlockState state, ItemStack stack)
    {
        if (!state.requiresCorrectToolForDrops())
            return true;

        return stack.isCorrectToolForDrops(state);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.DRILL_ARROW.get());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BLOCKS_BROKEN, false);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_36761_) {
        super.readAdditionalSaveData(p_36761_);
        if (p_36761_.contains("BrokenBlocks")) {
            this.setBroken(p_36761_.getBoolean("BrokenBlocks"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_36772_) {
        super.addAdditionalSaveData(p_36772_);
        p_36772_.putBoolean("BrokenBlocks", this.getBroken());

    }
}

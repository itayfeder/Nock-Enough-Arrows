package com.itayfeder.nock_enough_arrows.arrows.growing;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.Nullable;

public class GrowingArrow extends AbstractArrow {
    private static final EntityDataAccessor<Integer> ID_SIZE = SynchedEntityData.defineId(GrowingArrow.class, EntityDataSerializers.INT);

    public GrowingArrow(EntityType<? extends GrowingArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public GrowingArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.GROWING_ARROW.get(), p_37420_, p_37419_);
    }

    public GrowingArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.GROWING_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public GrowingArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.GROWING_ARROW.get(), world);
    }

    public void tick() {
        super.tick();
        if (!this.level.isClientSide && this.random.nextInt(5) == 0 && !this.inGround) this.setSize(this.getSize()+1);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_SIZE, 1);
    }

    public EntityDimensions getDimensions(Pose p_33597_) {
        return super.getDimensions(p_33597_).scale(1 + 0.5F * (float)this.getSize());
    }

    public int getSize() {
        return this.entityData.get(ID_SIZE);
    }

    public void setSize(int p_33594_) {
        this.entityData.set(ID_SIZE, p_33594_);
        this.refreshDimensions();
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.GROWING_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        this.setBaseDamage(this.getBaseDamage() + 0.15 * this.getSize());
        super.onHitEntity(p_36757_);
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
    }

    @Nullable
    @Override
    protected EntityHitResult findHitEntity(Vec3 p_36758_, Vec3 p_36759_) {
        AABB bbox = this.getUpdatedBoundingBox();
        bbox = bbox.expandTowards(this.getDeltaMovement()).inflate(1.0D);
        return ProjectileUtil.getEntityHitResult(this.level, this, p_36758_, p_36759_, bbox, this::canHitEntity);
    }

    public final AABB getUpdatedBoundingBox() {
        AABB bbox = this.getBoundingBox();
        return bbox.inflate(0.5 * this.getSize());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_36761_) {
        super.readAdditionalSaveData(p_36761_);
        this.setSize(p_36761_.getInt("Size"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_36772_) {
        super.addAdditionalSaveData(p_36772_);
        p_36772_.putInt("Size", this.getSize());
    }
}

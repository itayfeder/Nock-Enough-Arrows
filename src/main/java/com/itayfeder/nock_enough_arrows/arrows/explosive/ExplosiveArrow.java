package com.itayfeder.nock_enough_arrows.arrows.explosive;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class ExplosiveArrow extends AbstractArrow {
    private float explosionRadius = 1.5F;

    public ExplosiveArrow(EntityType<? extends ExplosiveArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public ExplosiveArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.EXPLOSIVE_ARROW.get(), p_37420_, p_37419_);
    }

    public ExplosiveArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.EXPLOSIVE_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public ExplosiveArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.EXPLOSIVE_ARROW.get(), world);
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide && !this.inGround) {
            this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }

    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.EXPLOSIVE_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
        this.explode();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        this.explode();
    }

    private void explode() {
        Explosion.BlockInteraction explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius, explosion$mode);
        this.kill();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_36761_) {
        super.readAdditionalSaveData(p_36761_);
        if (p_36761_.contains("ExplosionRadius")) {
            this.explosionRadius = p_36761_.getFloat("ExplosionRadius");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_36772_) {
        super.addAdditionalSaveData(p_36772_);
        p_36772_.putFloat("ExplosionRadius", this.explosionRadius);

    }
}

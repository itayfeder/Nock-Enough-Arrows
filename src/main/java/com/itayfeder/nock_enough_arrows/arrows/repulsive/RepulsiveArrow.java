package com.itayfeder.nock_enough_arrows.arrows.repulsive;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

import java.util.List;
import java.util.function.Predicate;

public class RepulsiveArrow extends AbstractArrow {
    public RepulsiveArrow(EntityType<? extends RepulsiveArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public RepulsiveArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.REPULSIVE_ARROW.get(), p_37420_, p_37419_);
    }

    public RepulsiveArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.REPULSIVE_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public RepulsiveArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.REPULSIVE_ARROW.get(), world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.REPULSIVE_ARROW.get());
    }

    protected void doPostHurtEffects(LivingEntity p_37422_) {
        super.doPostHurtEffects(p_37422_);
        MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.CONFUSION, 400, 0);
        p_37422_.addEffect(mobeffectinstance, this.getEffectSource());
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide) {
            int k = MobEffects.POISON.getColor();
            double d5 = (double)((float)(k >> 16 & 255) / 255.0F);
            double d6 = (double)((float)(k >> 8 & 255) / 255.0F);
            double d7 = (double)((float)(k & 255) / 255.0F);
            this.level.addAlwaysVisibleParticle(ParticleTypes.ENTITY_EFFECT, this.getX(), this.getY(), this.getZ(), d5, d6, d7);
        }

        AABB range = new AABB(this.getX() - 12.0D, this.getY() - 3.0D, this.getZ() - 12.0D, this.getX() + 12.0D, this.getY() + 3.0D, this.getZ() + 12.0D);
        Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and((entity) -> {
            if (entity instanceof LivingEntity) {
                return true;
            }
            return false;
        });
        List<? extends PathfinderMob> entities = this.level.getEntitiesOfClass(PathfinderMob.class, range, ENTITY_PREDICATE);
        for (PathfinderMob entity : entities) {
            if (entity != null) {
                double xt = entity.xOld;
                double yt = entity.yOld;
                double zt = entity.zOld;
                int x1 = Mth.floor(this.xOld);
                int y1 = Mth.floor(this.yOld);
                int z1 = Mth.floor(this.zOld);
                double x2 = xt - (double)x1;
                double y2 = yt - (double)y1;
                double z2 = zt - (double)z1;
                if (Mth.abs((int)x2) < 12 && Mth.abs((int)z2) < 6 && Mth.abs((int)y2) < 12) {
                    entity.setTarget((LivingEntity)null);
                    //entity.reve((LivingEntity)null);
                    Vec3 vec3d = DefaultRandomPos.getPosAway(entity, 18, 7, new Vec3(entity.xOld, entity.yOld, entity.zOld));
                    WorldBorder wb = this.level.getWorldBorder();
                    if (vec3d != null && wb.isWithinBounds(new BlockPos(entity.xOld, entity.yOld, entity.zOld)) && this.distanceToSqr(vec3d.x, vec3d.y, vec3d.z) >= this.distanceToSqr(entity)) {
                        entity.getNavigation().moveTo(vec3d.x, vec3d.y, vec3d.z, 2.0D);
                    }
                }
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
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

package com.itayfeder.nock_enough_arrows.arrows.seeker;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

import java.util.List;
import java.util.function.Predicate;

public class SeekerArrow extends AbstractArrow {
    public SeekerArrow(EntityType<? extends SeekerArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public SeekerArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.SEEKER_ARROW.get(), p_37420_, p_37419_);
    }

    public SeekerArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.SEEKER_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public SeekerArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.SEEKER_ARROW.get(), world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.SEEKER_ARROW.get());
    }


    public void tick() {
        super.tick();
        if (this.level.isClientSide && !this.inGround) {
            this.level.addParticle(ParticleTypes.END_ROD, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
        if (!this.inGround) {
            AABB range = new AABB(this.getX() - 6.0D, this.getY() - 3.0D, this.getZ() - 6.0D, this.getX() + 6.0D, this.getY() + 3.0D, this.getZ() + 6.0D);
            Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and((entity) -> {
                if (entity instanceof LivingEntity) {
                    return true;
                }
                return false;
            });
            List<? extends LivingEntity> entities = this.level.getEntitiesOfClass(LivingEntity.class, range, ENTITY_PREDICATE);
            boolean found = false;
            for (LivingEntity entity : entities) {
                if (entity != null && entity != getOwner()) {
                    found = true;
                    double x = entity.getX() - this.getX();
                    double y = entity.getY() - this.getY();
                    double z = entity.getZ() - this.getZ();
                    double sqrt = Mth.sqrt((float) (x * x +  z * z));
                    shoot(x, y + sqrt * 0.2, z, 1.6F, 2.0F);
                    break;
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

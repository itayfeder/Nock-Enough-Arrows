package com.itayfeder.nock_enough_arrows.arrows.echoing;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.VibrationParticleOption;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

import java.util.List;
import java.util.function.Predicate;

public class EchoingArrow extends AbstractArrow {
    public EchoingArrow(EntityType<? extends EchoingArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public EchoingArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.ECHOING_ARROW.get(), p_37420_, p_37419_);
    }

    public EchoingArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.ECHOING_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public EchoingArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.ECHOING_ARROW.get(), world);
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide && !this.inGround) {
            this.level.addParticle(ParticleTypes.SCULK_CHARGE_POP, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.ECHOING_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        AABB range = new AABB(this.getX() - 12.0D, this.getY() - 8.0D, this.getZ() - 12.0D, this.getX() + 12.0D, this.getY() + 8.0D, this.getZ() + 12.0D);
        Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and((entity) -> {
            if (entity instanceof LivingEntity) {
                return true;
            }
            return false;
        });
        List<? extends LivingEntity> entities = this.level.getEntitiesOfClass(LivingEntity.class, range, ENTITY_PREDICATE);
        for (LivingEntity entity : entities) {
            if (entity != null && entity != this.getOwner()) {
                entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400, 0));
                this.level.addParticle(new VibrationParticleOption(new EntityPositionSource(entity, 0), 10), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}

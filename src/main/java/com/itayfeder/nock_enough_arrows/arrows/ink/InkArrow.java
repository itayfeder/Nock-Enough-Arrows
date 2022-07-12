package com.itayfeder.nock_enough_arrows.arrows.ink;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

import java.util.List;
import java.util.Random;

public class InkArrow extends AbstractArrow {
    public InkArrow(EntityType<? extends InkArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public InkArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.INK_ARROW.get(), p_37420_, p_37419_);
    }

    public InkArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.INK_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public InkArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.INK_ARROW.get(), world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.INK_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
        this.splash();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        this.splash();
    }

    protected void doPostHurtEffects(LivingEntity p_37422_) {
        super.doPostHurtEffects(p_37422_);
        MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.BLINDNESS, 200, 0);
        p_37422_.addEffect(mobeffectinstance, this.getEffectSource());
    }

    private void splash() {
        if (!this.level.isClientSide) {
            Random rnd = new Random();
            for (int i = 0; i < 30; i++) {
                ((ServerLevel)this.level).sendParticles(ParticleTypes.SQUID_INK, this.getX() + (rnd.nextFloat() * (rnd.nextInt(3) + 1) * (rnd.nextBoolean() ? -1 : 1)), this.getY() + (rnd.nextFloat() * (rnd.nextInt(3) + 1) * (rnd.nextBoolean() ? -1 : 1)), this.getZ() + (rnd.nextFloat() * (rnd.nextInt(3) + 1) * (rnd.nextBoolean() ? -1 : 1)), 0, 0.0D, 0.0D, 0.0D, 0.1D);
            }
            List list = this.level.getEntities(this, getBoundingBox().inflate(2.0D, 2.0D, 2.0D));
            for (int i = 0; i < list.size(); ++i)
            {
                if (list.get(i) instanceof LivingEntity) {
                    LivingEntity entity1 = (LivingEntity) list.get(i);
                    entity1.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0));
                }
            }
            this.kill();
        }

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

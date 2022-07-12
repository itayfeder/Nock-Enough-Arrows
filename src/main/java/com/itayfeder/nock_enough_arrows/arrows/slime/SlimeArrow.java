package com.itayfeder.nock_enough_arrows.arrows.slime;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class SlimeArrow extends AbstractArrow {
    private int curBounces = 0;
    private int maxBounces = 3;

    public SlimeArrow(EntityType<? extends SlimeArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
        this.setBaseDamage(1D);
    }

    public SlimeArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.SLIME_ARROW.get(), p_37420_, p_37419_);
        this.setBaseDamage(1D);
    }

    public SlimeArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.SLIME_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
        this.setBaseDamage(1D);
    }

    public SlimeArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.SLIME_ARROW.get(), world);
        this.setBaseDamage(1D);
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide && !this.inGround) {
            this.level.addParticle(ParticleTypes.ITEM_SLIME, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }

    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.SLIME_ARROW.get());
    }

    @Override
    protected void onHit(HitResult raytraceResultIn) {
        if (raytraceResultIn.getType() != HitResult.Type.MISS) {
            this.setSoundEvent(SoundEvents.SLIME_BLOCK_HIT);
            if (raytraceResultIn.getType() == HitResult.Type.ENTITY) {
                this.onHitEntity((EntityHitResult) raytraceResultIn);
            } else if (raytraceResultIn.getType() == HitResult.Type.BLOCK) {
                Vec3 motion = this.getDeltaMovement();
                if (this.isInWater() || this.curBounces >= this.maxBounces) {
                    super.onHit(raytraceResultIn);
                    return;
                }

                BlockHitResult blockraytraceresult = (BlockHitResult)raytraceResultIn;
                switch(blockraytraceresult.getDirection()) {
                    case DOWN:
                    case UP:
                        this.setDeltaMovement(motion.x, motion.y * -1.0D, motion.z);
                        break;
                    case NORTH:
                    case SOUTH:
                        this.setDeltaMovement(motion.x, motion.y, motion.z * -1.0D);
                        break;
                    case WEST:
                    case EAST:
                        this.setDeltaMovement(motion.x * -1.0D, motion.y, motion.z);
                }

                float f = Mth.sqrt((float)(motion.x * motion.x + motion.z * motion.z));
                this.setYRot((float)(Mth.atan2(motion.x, motion.z) * 57.2957763671875D));
                this.setXRot((float)(Mth.atan2(motion.y, (double)f) * 57.2957763671875D));
                this.yRotO = this.getYRot();
                this.xRotO = this.getYRot();
                ++this.curBounces;
            }
        }
    }

    private void summon() {
        Pufferfish entityW1 = (Pufferfish) EntityType.PUFFERFISH.create(this.level);
        entityW1.setPos((double) this.getX(), (double) this.getY(), (double) this.getZ());
        this.level.addFreshEntity(entityW1);
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

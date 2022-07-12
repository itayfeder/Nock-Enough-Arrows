package com.itayfeder.nock_enough_arrows.arrows.pufferfish;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class PufferfishArrow extends AbstractArrow {
    public PufferfishArrow(EntityType<? extends PufferfishArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
        this.setBaseDamage(1D);
    }

    public PufferfishArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.PUFFERFISH_ARROW.get(), p_37420_, p_37419_);
        this.setBaseDamage(1D);
    }

    public PufferfishArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.PUFFERFISH_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
        this.setBaseDamage(1D);
    }

    public PufferfishArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.PUFFERFISH_ARROW.get(), world);
        this.setBaseDamage(1D);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.PUFFERFISH_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
        this.summon();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        this.summon();
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

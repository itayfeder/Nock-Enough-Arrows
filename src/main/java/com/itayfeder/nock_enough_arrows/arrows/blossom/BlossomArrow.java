package com.itayfeder.nock_enough_arrows.arrows.blossom;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class BlossomArrow extends AbstractArrow {
    public BlossomArrow(EntityType<? extends BlossomArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public BlossomArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.BLOSSOM_ARROW.get(), p_37420_, p_37419_);
    }

    public BlossomArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.BLOSSOM_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public BlossomArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.BLOSSOM_ARROW.get(), world);
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide && !this.inGround) {
            this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.BLOSSOM_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        if (this.getOwner() instanceof Player player) {
            if (BoneMealItem.applyBonemeal(this.getPickupItem(), level, p_36755_.getBlockPos(), player)) {
                if (!level.isClientSide) {
                    level.levelEvent(1505, p_36755_.getBlockPos(), 0);
                }

                this.discard();
            }
        }
    }
}

package com.itayfeder.nock_enough_arrows.arrows.message;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class MessageArrow extends AbstractArrow {
    private String message = "temp";

    public MessageArrow(EntityType<? extends MessageArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
        this.setBaseDamage(0.5D);
    }

    public MessageArrow(Level p_37419_, LivingEntity p_37420_, Component name) {
        super(EntityTypeInit.MESSAGE_ARROW.get(), p_37420_, p_37419_);
        this.setCustomName(name);
        this.setCustomNameVisible(true);
        this.setBaseDamage(0.5D);
    }

    public MessageArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.MESSAGE_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
        this.setBaseDamage(0.5D);
    }

    public MessageArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.MESSAGE_ARROW.get(), world);
        this.setBaseDamage(0.5D);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.MESSAGE_ARROW.get());
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

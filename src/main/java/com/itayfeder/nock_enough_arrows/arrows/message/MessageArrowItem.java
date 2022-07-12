package com.itayfeder.nock_enough_arrows.arrows.message;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MessageArrowItem extends ArrowItem {
    public MessageArrowItem(Properties p_40512_) {
        super(p_40512_);
    }

    public AbstractArrow createArrow(Level p_43237_, ItemStack p_43238_, LivingEntity p_43239_) {
        return new MessageArrow(p_43237_, p_43239_, p_43238_.getDisplayName());
    }
}

package com.itayfeder.nock_enough_arrows.arrows.dousing;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DousingArrowItem extends ArrowItem {
    public DousingArrowItem(Properties p_40512_) {
        super(p_40512_);
    }

    public AbstractArrow createArrow(Level p_43237_, ItemStack p_43238_, LivingEntity p_43239_) {
        return new DousingArrow(p_43237_, p_43239_);
    }
}

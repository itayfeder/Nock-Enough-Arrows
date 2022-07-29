package com.itayfeder.nock_enough_arrows.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class CycleEnchantment extends Enchantment {
    public CycleEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_) {
        super(p_44676_, p_44677_, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    public int getMinCost(int p_45223_) {
        return 25;
    }

    public int getMaxCost(int p_45227_) {
        return 50;
    }

    public int getMaxLevel() {
        return 1;
    }
}

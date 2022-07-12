package com.itayfeder.nock_enough_arrows.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class StockpileEnchantment extends Enchantment {
    public StockpileEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_) {
        super(p_44676_, p_44677_, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    public int getMinCost(int p_44666_) {
        return 1 + 10 * (p_44666_ - 1);
    }

    public int getMaxCost(int p_44670_) {
        return super.getMinCost(p_44670_) + 50;
    }

    public int getMaxLevel() {
        return 4;
    }
}

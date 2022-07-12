package com.itayfeder.nock_enough_arrows.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class RecoveryEnchantment extends Enchantment {
    public RecoveryEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_) {
        super(p_44676_, p_44677_, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    public int getMinCost(int p_44652_) {
        return 5 + (p_44652_ - 1) * 8;
    }

    public int getMaxCost(int p_44660_) {
        return super.getMinCost(p_44660_) + 50;
    }

    public int getMaxLevel() {
        return 3;
    }
}

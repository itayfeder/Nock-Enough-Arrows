package com.itayfeder.nock_enough_arrows.init;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.enchantments.RecoveryEnchantment;
import com.itayfeder.nock_enough_arrows.enchantments.StockpileEnchantment;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, NockEnoughArrowsMod.MOD_ID);

    public static final EnchantmentCategory QUIVER = EnchantmentCategory.create("quiver", (item -> item instanceof QuiverItem));

    public static final RegistryObject<Enchantment> STOCKPILE = ENCHANTMENTS.register("stockpile", () -> new StockpileEnchantment(Enchantment.Rarity.UNCOMMON, QUIVER));
    public static final RegistryObject<Enchantment> RECOVERY = ENCHANTMENTS.register("recovery", () -> new RecoveryEnchantment(Enchantment.Rarity.RARE, QUIVER));


}

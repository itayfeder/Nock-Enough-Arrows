package com.itayfeder.nock_enough_arrows.init;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.fletching_table.FletchingTableMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {

    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, NockEnoughArrowsMod.MOD_ID);

    public static final RegistryObject<MenuType<FletchingTableMenu>> FLETCHING_TABLE = CONTAINER_TYPES
            .register("fletching_table", () -> IForgeMenuType.create(FletchingTableMenu::new));
}
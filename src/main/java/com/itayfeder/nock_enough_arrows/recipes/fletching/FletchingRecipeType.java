package com.itayfeder.nock_enough_arrows.recipes.fletching;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.world.item.crafting.RecipeType;

public class FletchingRecipeType implements RecipeType<FletchingRecipe> {

    @Override
    public String toString () {
        return NockEnoughArrowsMod.MOD_ID + ":fletching";
    }
}
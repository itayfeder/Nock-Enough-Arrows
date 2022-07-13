package com.itayfeder.nock_enough_arrows.init;

import com.itayfeder.nock_enough_arrows.mixin.RecipeManagerMixin;
import com.itayfeder.nock_enough_arrows.recipes.fletching.FletchingRecipe;
import com.itayfeder.nock_enough_arrows.recipes.fletching.FletchingRecipeType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public class RecipeInit {
    public static final RecipeType<FletchingRecipe> FLETCHING_RECIPE = new FletchingRecipeType();

    public static void registerRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
        System.out.println(FLETCHING_RECIPE.toString());
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(FLETCHING_RECIPE.toString()), FLETCHING_RECIPE);
        event.getRegistry().register(FletchingRecipe.SERIALIZER);
        //event.getRegistry().register(PaintArrowRecipe.PAINT_ARROW.setRegistryName(new ResourceLocation(AllInTheQuiverMod.MOD_ID, "crafting_special_paintarrow")));
    }

    public static <C extends Container, T extends Recipe<C>> Map<ResourceLocation, Recipe<C>> getRecipes(Level world, RecipeType<T> type) {
        return ((RecipeManagerMixin) world.getRecipeManager()).feder_getRecipes(type);
    }
}

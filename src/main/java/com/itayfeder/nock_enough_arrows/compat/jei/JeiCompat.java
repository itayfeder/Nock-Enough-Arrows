package com.itayfeder.nock_enough_arrows.compat.jei;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.init.RecipeInit;
import com.itayfeder.nock_enough_arrows.recipes.fletching.FletchingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@JeiPlugin
public class JeiCompat implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "jei_compat");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new FletchingRecipeCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    private static final Comparator<Recipe<?>> BY_ID = Comparator.comparing(Recipe::getId);
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<FletchingRecipe> recipes = sortRecipes(RecipeInit.FLETCHING_RECIPE, BY_ID).stream().toList();
        registration.addRecipes(FletchingRecipeCategory.FLETCH_TYPE, recipes);
    }

    private static <T extends Recipe<C>, C extends Container> Collection<T> sortRecipes(RecipeType<T> type, Comparator<? super T> comparator) {
        @SuppressWarnings("unchecked")
        Collection<T> recipes = (Collection<T>) RecipeInit.getRecipes(Minecraft.getInstance().level, type).values();
        List<T> list = new ArrayList<>(recipes);
        list.sort(comparator);
        return list;
    }
}

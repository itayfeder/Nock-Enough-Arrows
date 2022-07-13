package com.itayfeder.nock_enough_arrows.compat.jei;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.recipes.fletching.FletchingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Services;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FletchingRecipeCategory implements IRecipeCategory<FletchingRecipe> {
    public static final RecipeType<FletchingRecipe> FLETCH_TYPE = RecipeType.create(NockEnoughArrowsMod.MOD_ID, "fletching", FletchingRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;
    private static final ResourceLocation FLETCHING_JEI_TEXTURE = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/gui/jei_fletching.png");

    public FletchingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(FLETCHING_JEI_TEXTURE, 0, 0, 116, 54);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks.FLETCHING_TABLE));
    }

    @Override
    public RecipeType<FletchingRecipe> getRecipeType() {
        return FLETCH_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui.jei.fletching");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FletchingRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 37)
                .addIngredients(recipe.fletching);
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 19)
                .addIngredients(recipe.shaft);
        builder.addSlot(RecipeIngredientRole.INPUT, 37, 1)
                .addIngredients(recipe.arrowhead);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 95, 19)
                .addItemStack(recipe.result);
    }
}

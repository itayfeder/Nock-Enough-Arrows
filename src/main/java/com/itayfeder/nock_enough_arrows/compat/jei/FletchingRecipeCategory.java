package com.itayfeder.nock_enough_arrows.compat.jei;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.recipes.fletching.FletchingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;

public class FletchingRecipeCategory implements IRecipeCategory<FletchingRecipe> {
    public static final RecipeType<FletchingRecipe> FLETCH_TYPE = RecipeType.create(NockEnoughArrowsMod.MOD_ID, "fletching", FletchingRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;
    private static final ResourceLocation FLETCHING_JEI_TEXTURE = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/gui/jei_fletching.png");

    public FletchingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(FLETCHING_JEI_TEXTURE, 0, 0, 116, 54);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(Blocks.FLETCHING_TABLE));
    }

    public ResourceLocation getUid() {
        return FLETCH_TYPE.getUid();
    }

    public Class<? extends FletchingRecipe> getRecipeClass() {
        return FLETCH_TYPE.getRecipeClass();
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("gui.jei.fletching");
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
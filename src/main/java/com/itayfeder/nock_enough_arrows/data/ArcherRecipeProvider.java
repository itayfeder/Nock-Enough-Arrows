package com.itayfeder.nock_enough_arrows.data;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import com.itayfeder.nock_enough_arrows.recipes.fletching.FletchingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ArcherRecipeProvider extends RecipeProvider {
    public ArcherRecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> p_176532_) {
        ShapedRecipeBuilder.shaped(ItemInit.QUIVER.get()).define('L', Items.LEATHER).define('I', Items.IRON_INGOT).pattern(" LI").pattern("LLL").pattern("LL ").unlockedBy("has_leather", has(Items.LEATHER)).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.FLINT, Items.ARROW, 4).unlockedByItem(Items.FLINT).save(p_176532_, new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "fletching_arrow"));
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.PUFFERFISH, ItemInit.PUFFERFISH_ARROW.get(), 1).unlockedByItem(Items.PUFFERFISH).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.TNT, ItemInit.EXPLOSIVE_ARROW.get(), 3).unlockedByItem(Items.TNT).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.SLIME_BALL, ItemInit.SLIME_ARROW.get(), 2).unlockedByItem(Items.SLIME_BALL).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.PRISMARINE_SHARD, ItemInit.PRISMARINE_ARROW.get(), 3).unlockedByItem(Items.PRISMARINE_SHARD).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.LEAD, ItemInit.HOOKSHOT_ARROW.get(), 1).unlockedByItem(Items.LEAD).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.PAPER, ItemInit.MESSAGE_ARROW.get(), 2).unlockedByItem(Items.PAPER).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.ENDER_PEARL, ItemInit.TELEPORTATION_ARROW.get(), 1).unlockedByItem(Items.ENDER_PEARL).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.INK_SAC, ItemInit.INK_ARROW.get(), 2).unlockedByItem(Items.INK_SAC).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.TORCH, ItemInit.TORCH_ARROW.get(), 1).unlockedByItem(Items.TORCH).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.SOUL_TORCH, ItemInit.SOUL_TORCH_ARROW.get(), 1).unlockedByItem(Items.SOUL_TORCH).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.REDSTONE_TORCH, ItemInit.REDSTONE_TORCH_ARROW.get(), 1).unlockedByItem(Items.REDSTONE_TORCH).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.WATER_BUCKET, ItemInit.DOUSING_ARROW.get(), 3).unlockedByItem(Items.WATER_BUCKET).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.BONE_MEAL, ItemInit.BLOSSOM_ARROW.get(), 1).unlockedByItem(Items.BONE_MEAL).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.IRON_PICKAXE, ItemInit.DRILL_ARROW.get(), 24).unlockedByItem(Items.IRON_PICKAXE).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.ARROW, ItemInit.SPLIT_ARROW.get(), 1).unlockedByItem(Items.ARROW).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.FIREWORK_ROCKET, ItemInit.PARTY_ARROW.get(), 2).unlockedByItem(Items.FIREWORK_ROCKET).save(p_176532_);
        FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.POISONOUS_POTATO, ItemInit.REPULSIVE_ARROW.get(), 1).unlockedByItem(Items.POISONOUS_POTATO).save(p_176532_);
        //FletchingRecipeBuilder.build(Items.FEATHER, Items.STICK, Items.ECHO_SHARD, ItemInit.ECHOING_ARROW.get(), 2).unlockedByItem(Items.ECHO_SHARD).save(p_176532_);

    }

}

package com.itayfeder.nock_enough_arrows.recipes.fletching;

import com.google.gson.JsonObject;
import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.init.RecipeInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class FletchingRecipe implements Recipe<Container> {
    public static final Serializer SERIALIZER = new Serializer();

    public final Ingredient fletching;
    public final Ingredient shaft;
    public final Ingredient arrowhead;
    public final ItemStack result;
    private final ResourceLocation id;

    public FletchingRecipe(ResourceLocation p_i231600_1_, Ingredient p_i231600_2_, Ingredient p_i231600_3_, Ingredient p_i231600_4_, ItemStack p_i231600_5_) {
        this.id = p_i231600_1_;
        this.fletching = p_i231600_2_;
        this.shaft = p_i231600_3_;
        this.arrowhead = p_i231600_4_;
        this.result = p_i231600_5_;
    }

    @Override
    public boolean matches(Container p_44002_, Level p_44003_) {
        return this.fletching.test(p_44002_.getItem(0)) && this.shaft.test(p_44002_.getItem(1)) && this.arrowhead.test(p_44002_.getItem(2));
    }

    public ItemStack assemble(Container p_44531_) {
        ItemStack itemstack = this.result.copy();
        CompoundTag compoundtag = p_44531_.getItem(0).getTag();
        if (compoundtag != null) {
            itemstack.setTag(compoundtag.copy());
        }

        return itemstack;
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(Blocks.FLETCHING_TABLE);
    }

    public boolean canCraftInDimensions(int p_44528_, int p_44529_) {
        return true;
    }

    public ItemStack getResultItem() {
        return this.result;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    public RecipeType<?> getType() {
        return RecipeInit.FLETCHING_RECIPE;
    }

    public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<FletchingRecipe> {
        Serializer() {
            this.setRegistryName(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "fletching"));
        }

        public FletchingRecipe fromJson(ResourceLocation p_199425_1_, JsonObject p_199425_2_) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_199425_2_, "fletching"));
            Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_199425_2_, "shaft"));
            Ingredient ingredient2 = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_199425_2_, "arrowhead"));
            ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_199425_2_, "result"));
            return new FletchingRecipe(p_199425_1_, ingredient, ingredient1, ingredient2, itemstack);
        }

        public FletchingRecipe fromNetwork(ResourceLocation p_199426_1_, FriendlyByteBuf p_199426_2_) {
            Ingredient ingredient = Ingredient.fromNetwork(p_199426_2_);
            Ingredient ingredient1 = Ingredient.fromNetwork(p_199426_2_);
            Ingredient ingredient2 = Ingredient.fromNetwork(p_199426_2_);
            ItemStack itemstack = p_199426_2_.readItem();
            return new FletchingRecipe(p_199426_1_, ingredient, ingredient1, ingredient2, itemstack);
        }

        public void toNetwork(FriendlyByteBuf p_199427_1_, FletchingRecipe p_199427_2_) {
            p_199427_2_.fletching.toNetwork(p_199427_1_);
            p_199427_2_.shaft.toNetwork(p_199427_1_);
            p_199427_2_.arrowhead.toNetwork(p_199427_1_);
            p_199427_1_.writeItem(p_199427_2_.result);
        }
    }
}

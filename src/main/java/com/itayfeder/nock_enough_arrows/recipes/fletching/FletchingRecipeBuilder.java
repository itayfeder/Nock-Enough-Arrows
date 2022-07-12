package com.itayfeder.nock_enough_arrows.recipes.fletching;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class FletchingRecipeBuilder implements RecipeBuilder {

    public final Ingredient fletching;
    public final Ingredient shaft;
    public final Ingredient arrowhead;
    private final Item result;
    private final int count;    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    @javax.annotation.Nullable
    private String group;
    private final FletchingRecipe.Serializer<?> serializer;

    private FletchingRecipeBuilder(Ingredient p_i231600_2_, Ingredient p_i231600_3_, Ingredient p_i231600_4_, ItemLike p_126180_, int p_126181_, FletchingRecipe.Serializer<?> p_126247_) {
        this.fletching = p_i231600_2_;
        this.shaft = p_i231600_3_;
        this.arrowhead = p_i231600_4_;
        this.result = p_126180_.asItem();
        this.count = p_126181_;
        this.serializer = p_126247_;
    }

    public static FletchingRecipeBuilder build(ItemLike p_126250_, ItemLike p_126249_, ItemLike p_126251_, ItemLike p_126180_, int p_126181_) {
        return new FletchingRecipeBuilder(Ingredient.of(p_126250_), Ingredient.of(p_126249_), Ingredient.of(p_126251_), p_126180_, p_126181_, FletchingRecipe.SERIALIZER);
    }

    public FletchingRecipeBuilder unlockedBy(String p_126255_, CriterionTriggerInstance p_126256_) {
        this.advancement.addCriterion(p_126255_, p_126256_);
        return this;
    }

    public FletchingRecipeBuilder unlockedByItem(ItemLike item) {
        this.advancement.addCriterion("has_" + item.asItem().toString(), has(item));
        return this;
    }

    protected static InventoryChangeTrigger.TriggerInstance has(ItemLike p_125978_) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(p_125978_).build());
    }

    protected static InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate... p_126012_) {
        return new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, p_126012_);
    }

    public FletchingRecipeBuilder group(@Nullable String p_176795_) {
        this.group = p_176795_;
        return this;
    }

    @Override
    public void save(Consumer<FinishedRecipe> p_176499_) {
        RecipeBuilder.super.save(p_176499_);
    }

    public Item getResult() {
        return this.result;
    }

    public void save(Consumer<FinishedRecipe> p_126205_, ResourceLocation p_126206_) {
        this.ensureValid(p_126206_);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126206_)).rewards(AdvancementRewards.Builder.recipe(p_126206_)).requirements(RequirementsStrategy.OR);
        p_126205_.accept(new FletchingRecipeBuilder.Result(p_126206_, this.result, this.count, this.group == null ? "" : this.group, this.fletching, this.shaft, this.arrowhead, this.advancement, new ResourceLocation(p_126206_.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + p_126206_.getPath())));
    }

    private void ensureValid(ResourceLocation p_126208_) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + p_126208_);
        }
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        public final Ingredient fletching;
        public final Ingredient shaft;
        public final Ingredient arrowhead;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation p_126222_, Item p_126223_, int p_126224_, String p_126225_, Ingredient p_i231600_2_, Ingredient p_i231600_3_, Ingredient p_i231600_4_, Advancement.Builder p_126227_, ResourceLocation p_126228_) {
            this.id = p_126222_;
            this.result = p_126223_;
            this.count = p_126224_;
            this.group = p_126225_;
            this.fletching = p_i231600_2_;
            this.shaft = p_i231600_3_;
            this.arrowhead = p_i231600_4_;
            this.advancement = p_126227_;
            this.advancementId = p_126228_;
        }

        public void serializeRecipeData(JsonObject p_126230_) {
            if (!this.group.isEmpty()) {
                p_126230_.addProperty("group", this.group);
            }
            p_126230_.add("fletching", fletching.toJson());
            p_126230_.add("shaft", shaft.toJson());
            p_126230_.add("arrowhead", arrowhead.toJson());

            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
            jsonobject.addProperty("count", this.count);

            p_126230_.add("result", jsonobject);
        }

        public RecipeSerializer<?> getType() {
            return FletchingRecipe.SERIALIZER;
        }

        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}

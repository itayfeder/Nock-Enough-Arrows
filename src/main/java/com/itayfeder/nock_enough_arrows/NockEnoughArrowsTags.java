package com.itayfeder.nock_enough_arrows;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class NockEnoughArrowsTags {
    public static class ModItemTags {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, name));
        }
    }

    public static class ModBlockTags {
        public static final TagKey<Block> AIRS = tag("airs");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, name));
        }
    }
}

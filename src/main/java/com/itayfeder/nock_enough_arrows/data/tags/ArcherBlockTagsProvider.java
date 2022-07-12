package com.itayfeder.nock_enough_arrows.data.tags;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.NockEnoughArrowsTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ArcherBlockTagsProvider extends BlockTagsProvider {
    public ArcherBlockTagsProvider(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, NockEnoughArrowsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(NockEnoughArrowsTags.ModBlockTags.AIRS).add(Blocks.AIR, Blocks.CAVE_AIR, Blocks.VOID_AIR);
    }
}

package com.itayfeder.nock_enough_arrows.data.tags;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ArcherItemTagsProvider extends ItemTagsProvider {

    public ArcherItemTagsProvider(DataGenerator p_126530_, BlockTagsProvider p_126531_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126530_, p_126531_, NockEnoughArrowsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ItemTags.ARROWS).add(ItemInit.PUFFERFISH_ARROW.get(), ItemInit.EXPLOSIVE_ARROW.get(),
                ItemInit.SLIME_ARROW.get(), ItemInit.PRISMARINE_ARROW.get(), ItemInit.HOOKSHOT_ARROW.get(),
                ItemInit.MESSAGE_ARROW.get(), ItemInit.TELEPORTATION_ARROW.get(), ItemInit.INK_ARROW.get(),
                ItemInit.TORCH_ARROW.get(), ItemInit.SOUL_TORCH_ARROW.get(), ItemInit.REDSTONE_TORCH_ARROW.get(),
                ItemInit.ETHEREAL_ARROW.get(), ItemInit.DOUSING_ARROW.get(), ItemInit.BLOSSOM_ARROW.get(),
                ItemInit.GROWING_ARROW.get(), ItemInit.DRILL_ARROW.get(), ItemInit.SPLIT_ARROW.get(),
                ItemInit.PARTY_ARROW.get(), ItemInit.REPULSIVE_ARROW.get(), ItemInit.ECHOING_ARROW.get(),
                ItemInit.SEEKER_ARROW.get(), ItemInit.CUPID_ARROW.get(), ItemInit.REINFORCED_ARROW.get());
    }
}

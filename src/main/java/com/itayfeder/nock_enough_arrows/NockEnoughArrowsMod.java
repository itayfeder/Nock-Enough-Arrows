package com.itayfeder.nock_enough_arrows;

import com.itayfeder.nock_enough_arrows.data.ArcherRecipeProvider;
import com.itayfeder.nock_enough_arrows.data.tags.ArcherBlockTagsProvider;
import com.itayfeder.nock_enough_arrows.data.tags.ArcherItemTagsProvider;
import com.itayfeder.nock_enough_arrows.init.*;
import com.itayfeder.nock_enough_arrows.utils.DispenserRegistry;
import com.itayfeder.nock_enough_arrows.utils.PublicVillagerTrades;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NockEnoughArrowsMod.MOD_ID)
@Mod.EventBusSubscriber(modid = NockEnoughArrowsMod.MOD_ID)
public class NockEnoughArrowsMod
{
    public static final String MOD_ID = "nock_enough_arrows";
    private static final Logger LOGGER = LogUtils.getLogger();

    public NockEnoughArrowsMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        modEventBus.addListener(RecipeInit::registerRecipeSerializers);
        ItemInit.ITEMS.register(modEventBus);
        EntityTypeInit.ENTITY_TYPES.register(modEventBus);
        EnchantmentInit.ENCHANTMENTS.register(modEventBus);
        MenuInit.CONTAINER_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::dataSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        DispenserRegistry.registerBehaviors();

    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        if (event.includeServer()) {
            ArcherBlockTagsProvider blockTags = new ArcherBlockTagsProvider(dataGenerator, existingFileHelper);
            dataGenerator.addProvider(true, blockTags);
            dataGenerator.addProvider(true, new ArcherItemTagsProvider(dataGenerator, blockTags, existingFileHelper));

            dataGenerator.addProvider(true, new ArcherRecipeProvider(dataGenerator));

        }
    }

    @SubscribeEvent
    public static void villagerTradesEvent(WandererTradesEvent event) {
        event.getGenericTrades().add(new PublicVillagerTrades.ItemsForEmeralds(ItemInit.GROWING_ARROW.get(), 6, 8, 8));
        event.getGenericTrades().add(new PublicVillagerTrades.ItemsForEmeralds(ItemInit.ETHEREAL_ARROW.get(), 6, 8, 8));
        event.getGenericTrades().add(new PublicVillagerTrades.ItemsForEmeralds(ItemInit.ECHOING_ARROW.get(), 6, 8, 8));
    }
}

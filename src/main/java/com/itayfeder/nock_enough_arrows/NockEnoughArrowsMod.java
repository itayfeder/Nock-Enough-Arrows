package com.itayfeder.nock_enough_arrows;

import com.itayfeder.nock_enough_arrows.compat.CuriosCompat;
import com.itayfeder.nock_enough_arrows.data.ArcherRecipeProvider;
import com.itayfeder.nock_enough_arrows.data.tags.ArcherBlockTagsProvider;
import com.itayfeder.nock_enough_arrows.data.tags.ArcherItemTagsProvider;
import com.itayfeder.nock_enough_arrows.init.*;
import com.itayfeder.nock_enough_arrows.network.QuiverSwitchSelectionMessage;
import com.itayfeder.nock_enough_arrows.utils.DispenserRegistry;
import com.itayfeder.nock_enough_arrows.utils.PublicVillagerTrades;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NockEnoughArrowsMod.MOD_ID)
@Mod.EventBusSubscriber(modid = NockEnoughArrowsMod.MOD_ID)
public class NockEnoughArrowsMod
{
    public static final String MOD_ID = "nock_enough_arrows";
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, MOD_ID), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

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
        modEventBus.addListener(this::enqueueIMC);

        modEventBus.addListener(KeybindInit::registerKeyMappings);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        DispenserRegistry.registerBehaviors();
        NockEnoughArrowsMod.addMessage(QuiverSwitchSelectionMessage.class, QuiverSwitchSelectionMessage::buffer, QuiverSwitchSelectionMessage::new, QuiverSwitchSelectionMessage::handler);

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        if (ModList.get().isLoaded("curios")) {
            CuriosCompat.InqueueIMC();
        }
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

    public static <T> void addMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder,
                                      BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        System.out.println("REGISTERED MESSAGE: " + messageType.getName());
        messageID++;
    }
}

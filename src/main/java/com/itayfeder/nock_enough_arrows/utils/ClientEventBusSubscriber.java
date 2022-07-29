package com.itayfeder.nock_enough_arrows.utils;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.arrows.blossom.BlossomArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.cupid.CupidArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.dousing.DousingArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.drill.DrillArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.echoing.EchoingArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.ethereal.EtherealArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.explosive.ExplosiveArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.growing.GrowingArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.hookshot.HookshotArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.ink.InkArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.message.MessageArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.party.PartyArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.prismarine.PrismarineArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.pufferfish.PufferfishArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.redstone_torch.RedstoneTorchArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.reinforced.ReinforcedArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.repulsive.RepulsiveArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.seeker.SeekerArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.slime.SlimeArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.soul_torch.SoulTorchArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.split.SplitArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.teleportation.TeleportationArrowRenderer;
import com.itayfeder.nock_enough_arrows.arrows.torch.TorchArrowRenderer;
import com.itayfeder.nock_enough_arrows.fletching_table.FletchingTableScreen;
import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.KeybindInit;
import com.itayfeder.nock_enough_arrows.init.MenuInit;
import com.itayfeder.nock_enough_arrows.quiver.tooltip.ClientQuiverTooltip;
import com.itayfeder.nock_enough_arrows.quiver.tooltip.QuiverTooltip;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NockEnoughArrowsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityTypeInit.PUFFERFISH_ARROW.get(), PufferfishArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.EXPLOSIVE_ARROW.get(), ExplosiveArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.SLIME_ARROW.get(), SlimeArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.PRISMARINE_ARROW.get(), PrismarineArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.HOOKSHOT_ARROW.get(), HookshotArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.MESSAGE_ARROW.get(), MessageArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.TELEPORTATION_ARROW.get(), TeleportationArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.INK_ARROW.get(), InkArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.TORCH_ARROW.get(), TorchArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.SOUL_TORCH_ARROW.get(), SoulTorchArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.REDSTONE_TORCH_ARROW.get(), RedstoneTorchArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.ETHEREAL_ARROW.get(), EtherealArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.DOUSING_ARROW.get(), DousingArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.BLOSSOM_ARROW.get(), BlossomArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.GROWING_ARROW.get(), GrowingArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.DRILL_ARROW.get(), DrillArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.SPLIT_ARROW.get(), SplitArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.PARTY_ARROW.get(), PartyArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.REPULSIVE_ARROW.get(), RepulsiveArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.ECHOING_ARROW.get(), EchoingArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.SEEKER_ARROW.get(), SeekerArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.CUPID_ARROW.get(), CupidArrowRenderer::new);
        EntityRenderers.register(EntityTypeInit.REINFORCED_ARROW.get(), ReinforcedArrowRenderer::new);

        MenuScreens.register(MenuInit.FLETCHING_TABLE.get(), FletchingTableScreen::new);

        MinecraftForgeClient.registerTooltipComponentFactory(QuiverTooltip.class, ClientQuiverTooltip::new);
        KeybindInit.init();

    }
}

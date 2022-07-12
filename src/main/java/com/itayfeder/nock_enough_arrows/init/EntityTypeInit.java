package com.itayfeder.nock_enough_arrows.init;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.arrows.blossom.BlossomArrow;
import com.itayfeder.nock_enough_arrows.arrows.dousing.DousingArrow;
import com.itayfeder.nock_enough_arrows.arrows.drill.DrillArrow;
import com.itayfeder.nock_enough_arrows.arrows.echoing.EchoingArrow;
import com.itayfeder.nock_enough_arrows.arrows.ethereal.EtherealArrow;
import com.itayfeder.nock_enough_arrows.arrows.explosive.ExplosiveArrow;
import com.itayfeder.nock_enough_arrows.arrows.growing.GrowingArrow;
import com.itayfeder.nock_enough_arrows.arrows.hookshot.HookshotArrow;
import com.itayfeder.nock_enough_arrows.arrows.ink.InkArrow;
import com.itayfeder.nock_enough_arrows.arrows.message.MessageArrow;
import com.itayfeder.nock_enough_arrows.arrows.party.PartyArrow;
import com.itayfeder.nock_enough_arrows.arrows.prismarine.PrismarineArrow;
import com.itayfeder.nock_enough_arrows.arrows.pufferfish.PufferfishArrow;
import com.itayfeder.nock_enough_arrows.arrows.redstone_torch.RedstoneTorchArrow;
import com.itayfeder.nock_enough_arrows.arrows.repulsive.RepulsiveArrow;
import com.itayfeder.nock_enough_arrows.arrows.slime.SlimeArrow;
import com.itayfeder.nock_enough_arrows.arrows.soul_torch.SoulTorchArrow;
import com.itayfeder.nock_enough_arrows.arrows.split.SplitArrow;
import com.itayfeder.nock_enough_arrows.arrows.teleportation.TeleportationArrow;
import com.itayfeder.nock_enough_arrows.arrows.torch.TorchArrow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeInit {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, NockEnoughArrowsMod.MOD_ID);

    public static final RegistryObject<EntityType<PufferfishArrow>> PUFFERFISH_ARROW = ENTITY_TYPES.register("pufferfish_arrow",
            () -> EntityType.Builder.<PufferfishArrow>of(PufferfishArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(PufferfishArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "pufferfish_arrow").toString()));

    public static final RegistryObject<EntityType<ExplosiveArrow>> EXPLOSIVE_ARROW = ENTITY_TYPES.register("explosive_arrow",
            () -> EntityType.Builder.<ExplosiveArrow>of(ExplosiveArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(ExplosiveArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "explosive_arrow").toString()));

    public static final RegistryObject<EntityType<SlimeArrow>> SLIME_ARROW = ENTITY_TYPES.register("slime_arrow",
            () -> EntityType.Builder.<SlimeArrow>of(SlimeArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(SlimeArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "slime_arrow").toString()));

    public static final RegistryObject<EntityType<PrismarineArrow>> PRISMARINE_ARROW = ENTITY_TYPES.register("prismarine_arrow",
            () -> EntityType.Builder.<PrismarineArrow>of(PrismarineArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(PrismarineArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "prismarine_arrow").toString()));

    public static final RegistryObject<EntityType<HookshotArrow>> HOOKSHOT_ARROW = ENTITY_TYPES.register("hookshot_arrow",
            () -> EntityType.Builder.<HookshotArrow>of(HookshotArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(HookshotArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "hookshot_arrow").toString()));

    public static final RegistryObject<EntityType<MessageArrow>> MESSAGE_ARROW = ENTITY_TYPES.register("message_arrow",
            () -> EntityType.Builder.<MessageArrow>of(MessageArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(MessageArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "message_arrow").toString()));

    public static final RegistryObject<EntityType<TeleportationArrow>> TELEPORTATION_ARROW = ENTITY_TYPES.register("teleportation_arrow",
            () -> EntityType.Builder.<TeleportationArrow>of(TeleportationArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(TeleportationArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "teleportation_arrow").toString()));

    public static final RegistryObject<EntityType<InkArrow>> INK_ARROW = ENTITY_TYPES.register("ink_arrow",
            () -> EntityType.Builder.<InkArrow>of(InkArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(InkArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "ink_arrow").toString()));

    public static final RegistryObject<EntityType<TorchArrow>> TORCH_ARROW = ENTITY_TYPES.register("torch_arrow",
            () -> EntityType.Builder.<TorchArrow>of(TorchArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(TorchArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "torch_arrow").toString()));

    public static final RegistryObject<EntityType<SoulTorchArrow>> SOUL_TORCH_ARROW = ENTITY_TYPES.register("soul_torch_arrow",
            () -> EntityType.Builder.<SoulTorchArrow>of(SoulTorchArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(SoulTorchArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "soul_torch_arrow").toString()));

    public static final RegistryObject<EntityType<RedstoneTorchArrow>> REDSTONE_TORCH_ARROW = ENTITY_TYPES.register("redstone_torch_arrow",
            () -> EntityType.Builder.<RedstoneTorchArrow>of(RedstoneTorchArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(RedstoneTorchArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "redstone_torch_arrow").toString()));

    public static final RegistryObject<EntityType<EtherealArrow>> ETHEREAL_ARROW = ENTITY_TYPES.register("ethereal_arrow",
            () -> EntityType.Builder.<EtherealArrow>of(EtherealArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(EtherealArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "ethereal_arrow").toString()));

    public static final RegistryObject<EntityType<DousingArrow>> DOUSING_ARROW = ENTITY_TYPES.register("dousing_arrow",
            () -> EntityType.Builder.<DousingArrow>of(DousingArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(DousingArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "dousing_arrow").toString()));

    public static final RegistryObject<EntityType<BlossomArrow>> BLOSSOM_ARROW = ENTITY_TYPES.register("blossom_arrow",
            () -> EntityType.Builder.<BlossomArrow>of(BlossomArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(BlossomArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "blossom_arrow").toString()));

    public static final RegistryObject<EntityType<GrowingArrow>> GROWING_ARROW = ENTITY_TYPES.register("growing_arrow",
            () -> EntityType.Builder.<GrowingArrow>of(GrowingArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(GrowingArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "growing_arrow").toString()));

    public static final RegistryObject<EntityType<DrillArrow>> DRILL_ARROW = ENTITY_TYPES.register("drill_arrow",
            () -> EntityType.Builder.<DrillArrow>of(DrillArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(DrillArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "drill_arrow").toString()));

    public static final RegistryObject<EntityType<SplitArrow>> SPLIT_ARROW = ENTITY_TYPES.register("split_arrow",
            () -> EntityType.Builder.<SplitArrow>of(SplitArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(SplitArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "split_arrow").toString()));

    public static final RegistryObject<EntityType<PartyArrow>> PARTY_ARROW = ENTITY_TYPES.register("party_arrow",
            () -> EntityType.Builder.<PartyArrow>of(PartyArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(PartyArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "party_arrow").toString()));

    public static final RegistryObject<EntityType<RepulsiveArrow>> REPULSIVE_ARROW = ENTITY_TYPES.register("repulsive_arrow",
            () -> EntityType.Builder.<RepulsiveArrow>of(RepulsiveArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(RepulsiveArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "repulsive_arrow").toString()));

    public static final RegistryObject<EntityType<EchoingArrow>> ECHOING_ARROW = ENTITY_TYPES.register("echoing_arrow",
            () -> EntityType.Builder.<EchoingArrow>of(EchoingArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).setCustomClientFactory(EchoingArrow::new)
                    .build(new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "echoing_arrow").toString()));
}

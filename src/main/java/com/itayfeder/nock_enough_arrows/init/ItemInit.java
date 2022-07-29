package com.itayfeder.nock_enough_arrows.init;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.itayfeder.nock_enough_arrows.arrows.blossom.BlossomArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.cupid.CupidArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.dousing.DousingArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.drill.DrillArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.echoing.EchoingArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.ethereal.EtherealArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.explosive.ExplosiveArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.growing.GrowingArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.hookshot.HookshotArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.ink.InkArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.message.MessageArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.party.PartyArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.prismarine.PrismarineArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.pufferfish.PufferfishArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.redstone_torch.RedstoneTorchArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.reinforced.ReinforcedArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.repulsive.RepulsiveArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.seeker.SeekerArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.slime.SlimeArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.soul_torch.SoulTorchArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.split.SplitArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.teleportation.TeleportationArrowItem;
import com.itayfeder.nock_enough_arrows.arrows.torch.TorchArrowItem;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItem;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NockEnoughArrowsMod.MOD_ID);

    public static final CreativeModeTab TAB = new CreativeModeTab("quiver") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(QUIVER.get(), 1);
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> p_40778_) {
            super.fillItemList(p_40778_);
            p_40778_.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(EnchantmentInit.STOCKPILE.get(), EnchantmentInit.STOCKPILE.get().getMaxLevel())));
            p_40778_.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(EnchantmentInit.RECOVERY.get(), EnchantmentInit.RECOVERY.get().getMaxLevel())));
            p_40778_.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(EnchantmentInit.CYCLE.get(), EnchantmentInit.CYCLE.get().getMaxLevel())));

        }
    };

    public static final RegistryObject<Item> QUIVER = ITEMS.register("quiver", () -> new QuiverItem((new Item.Properties()).stacksTo(1).tab(TAB)));

    public static final RegistryObject<Item> PUFFERFISH_ARROW = ITEMS.register("pufferfish_arrow", () -> new PufferfishArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> EXPLOSIVE_ARROW = ITEMS.register("explosive_arrow", () -> new ExplosiveArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> SLIME_ARROW = ITEMS.register("slime_arrow", () -> new SlimeArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> PRISMARINE_ARROW = ITEMS.register("prismarine_arrow", () -> new PrismarineArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> HOOKSHOT_ARROW = ITEMS.register("hookshot_arrow", () -> new HookshotArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> MESSAGE_ARROW = ITEMS.register("message_arrow", () -> new MessageArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> TELEPORTATION_ARROW = ITEMS.register("teleportation_arrow", () -> new TeleportationArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> INK_ARROW = ITEMS.register("ink_arrow", () -> new InkArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> TORCH_ARROW = ITEMS.register("torch_arrow", () -> new TorchArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> SOUL_TORCH_ARROW = ITEMS.register("soul_torch_arrow", () -> new SoulTorchArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> REDSTONE_TORCH_ARROW = ITEMS.register("redstone_torch_arrow", () -> new RedstoneTorchArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> ETHEREAL_ARROW = ITEMS.register("ethereal_arrow", () -> new EtherealArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> DOUSING_ARROW = ITEMS.register("dousing_arrow", () -> new DousingArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> BLOSSOM_ARROW = ITEMS.register("blossom_arrow", () -> new BlossomArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> GROWING_ARROW = ITEMS.register("growing_arrow", () -> new GrowingArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> DRILL_ARROW = ITEMS.register("drill_arrow", () -> new DrillArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> SPLIT_ARROW = ITEMS.register("split_arrow", () -> new SplitArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> PARTY_ARROW = ITEMS.register("party_arrow", () -> new PartyArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> REPULSIVE_ARROW = ITEMS.register("repulsive_arrow", () -> new RepulsiveArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> ECHOING_ARROW = ITEMS.register("echoing_arrow", () -> new EchoingArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> SEEKER_ARROW = ITEMS.register("seeker_arrow", () -> new SeekerArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> CUPID_ARROW = ITEMS.register("cupid_arrow", () -> new CupidArrowItem((new Item.Properties()).tab(TAB)));
    public static final RegistryObject<Item> REINFORCED_ARROW = ITEMS.register("reinforced_arrow", () -> new ReinforcedArrowItem((new Item.Properties()).tab(TAB)));

}

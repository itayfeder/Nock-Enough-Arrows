package com.itayfeder.nock_enough_arrows.utils;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class PublicVillagerTrades {
    public static class DyedArmorForEmeralds implements VillagerTrades.ItemListing {
        private final Item item;
        private final int value;
        private final int maxUses;
        private final int villagerXp;

        public DyedArmorForEmeralds(Item p_35639_, int p_35640_) {
            this(p_35639_, p_35640_, 12, 1);
        }

        public DyedArmorForEmeralds(Item p_35642_, int p_35643_, int p_35644_, int p_35645_) {
            this.item = p_35642_;
            this.value = p_35643_;
            this.maxUses = p_35644_;
            this.villagerXp = p_35645_;
        }

        public MerchantOffer getOffer(Entity p_219679_, RandomSource p_219680_) {
            ItemStack itemstack = new ItemStack(Items.EMERALD, this.value);
            ItemStack itemstack1 = new ItemStack(this.item);
            if (this.item instanceof DyeableArmorItem) {
                List<DyeItem> list = Lists.newArrayList();
                list.add(getRandomDye(p_219680_));
                if (p_219680_.nextFloat() > 0.7F) {
                    list.add(getRandomDye(p_219680_));
                }

                if (p_219680_.nextFloat() > 0.8F) {
                    list.add(getRandomDye(p_219680_));
                }

                itemstack1 = DyeableLeatherItem.dyeArmor(itemstack1, list);
            }

            return new MerchantOffer(itemstack, itemstack1, this.maxUses, this.villagerXp, 0.2F);
        }

        private static DyeItem getRandomDye(RandomSource p_219677_) {
            return DyeItem.byColor(DyeColor.byId(p_219677_.nextInt(16)));
        }
    }

    public static class EmeraldForItems implements VillagerTrades.ItemListing {
        private final Item item;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EmeraldForItems(ItemLike p_35657_, int p_35658_, int p_35659_, int p_35660_) {
            this.item = p_35657_.asItem();
            this.cost = p_35658_;
            this.maxUses = p_35659_;
            this.villagerXp = p_35660_;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity p_219682_, RandomSource p_219683_) {
            ItemStack itemstack = new ItemStack(this.item, this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    public static class EmeraldsForVillagerTypeItem implements VillagerTrades.ItemListing {
        private final Map<VillagerType, Item> trades;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;

        public EmeraldsForVillagerTypeItem(int p_35669_, int p_35670_, int p_35671_, Map<VillagerType, Item> p_35672_) {
            Registry.VILLAGER_TYPE.stream().filter((p_35680_) -> {
                return !p_35672_.containsKey(p_35680_);
            }).findAny().ifPresent((p_35677_) -> {
                throw new IllegalStateException("Missing trade for villager type: " + Registry.VILLAGER_TYPE.getKey(p_35677_));
            });
            this.trades = p_35672_;
            this.cost = p_35669_;
            this.maxUses = p_35670_;
            this.villagerXp = p_35671_;
        }

        @Nullable
        public MerchantOffer getOffer(Entity p_219685_, RandomSource p_219686_) {
            if (p_219685_ instanceof VillagerDataHolder) {
                ItemStack itemstack = new ItemStack(this.trades.get(((VillagerDataHolder)p_219685_).getVillagerData().getType()), this.cost);
                return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, 0.05F);
            } else {
                return null;
            }
        }
    }

    public static class EnchantBookForEmeralds implements VillagerTrades.ItemListing {
        private final int villagerXp;

        public EnchantBookForEmeralds(int p_35683_) {
            this.villagerXp = p_35683_;
        }

        public MerchantOffer getOffer(Entity p_219688_, RandomSource p_219689_) {
            List<Enchantment> list = Registry.ENCHANTMENT.stream().filter(Enchantment::isTradeable).collect(Collectors.toList());
            Enchantment enchantment = list.get(p_219689_.nextInt(list.size()));
            int i = Mth.nextInt(p_219689_, enchantment.getMinLevel(), enchantment.getMaxLevel());
            ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i));
            int j = 2 + p_219689_.nextInt(5 + i * 10) + 3 * i;
            if (enchantment.isTreasureOnly()) {
                j *= 2;
            }

            if (j > 64) {
                j = 64;
            }

            return new MerchantOffer(new ItemStack(Items.EMERALD, j), new ItemStack(Items.BOOK), itemstack, 12, this.villagerXp, 0.2F);
        }
    }

    public static class EnchantedItemForEmeralds implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int baseEmeraldCost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EnchantedItemForEmeralds(Item p_35693_, int p_35694_, int p_35695_, int p_35696_) {
            this(p_35693_, p_35694_, p_35695_, p_35696_, 0.05F);
        }

        public EnchantedItemForEmeralds(Item p_35698_, int p_35699_, int p_35700_, int p_35701_, float p_35702_) {
            this.itemStack = new ItemStack(p_35698_);
            this.baseEmeraldCost = p_35699_;
            this.maxUses = p_35700_;
            this.villagerXp = p_35701_;
            this.priceMultiplier = p_35702_;
        }

        public MerchantOffer getOffer(Entity p_219691_, RandomSource p_219692_) {
            int i = 5 + p_219692_.nextInt(15);
            ItemStack itemstack = EnchantmentHelper.enchantItem(p_219692_, new ItemStack(this.itemStack.getItem()), i, false);
            int j = Math.min(this.baseEmeraldCost + i, 64);
            ItemStack itemstack1 = new ItemStack(Items.EMERALD, j);
            return new MerchantOffer(itemstack1, itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    public static class ItemsAndEmeraldsToItems implements VillagerTrades.ItemListing {
        private final ItemStack fromItem;
        private final int fromCount;
        private final int emeraldCost;
        private final ItemStack toItem;
        private final int toCount;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsAndEmeraldsToItems(ItemLike p_35725_, int p_35726_, Item p_35727_, int p_35728_, int p_35729_, int p_35730_) {
            this(p_35725_, p_35726_, 1, p_35727_, p_35728_, p_35729_, p_35730_);
        }

        public ItemsAndEmeraldsToItems(ItemLike p_35717_, int p_35718_, int p_35719_, Item p_35720_, int p_35721_, int p_35722_, int p_35723_) {
            this.fromItem = new ItemStack(p_35717_);
            this.fromCount = p_35718_;
            this.emeraldCost = p_35719_;
            this.toItem = new ItemStack(p_35720_);
            this.toCount = p_35721_;
            this.maxUses = p_35722_;
            this.villagerXp = p_35723_;
            this.priceMultiplier = 0.05F;
        }

        @Nullable
        public MerchantOffer getOffer(Entity p_219696_, RandomSource p_219697_) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.fromItem.getItem(), this.fromCount), new ItemStack(this.toItem.getItem(), this.toCount), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    public static class ItemsForEmeralds implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForEmeralds(Block p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
            this(new ItemStack(p_35765_), p_35766_, p_35767_, p_35768_, p_35769_);
        }

        public ItemsForEmeralds(Item p_35741_, int p_35742_, int p_35743_, int p_35744_) {
            this(new ItemStack(p_35741_), p_35742_, p_35743_, 12, p_35744_);
        }

        public ItemsForEmeralds(Item p_35746_, int p_35747_, int p_35748_, int p_35749_, int p_35750_) {
            this(new ItemStack(p_35746_), p_35747_, p_35748_, p_35749_, p_35750_);
        }

        public ItemsForEmeralds(ItemStack p_35752_, int p_35753_, int p_35754_, int p_35755_, int p_35756_) {
            this(p_35752_, p_35753_, p_35754_, p_35755_, p_35756_, 0.05F);
        }

        public ItemsForEmeralds(ItemStack p_35758_, int p_35759_, int p_35760_, int p_35761_, int p_35762_, float p_35763_) {
            this.itemStack = p_35758_;
            this.emeraldCost = p_35759_;
            this.numberOfItems = p_35760_;
            this.maxUses = p_35761_;
            this.villagerXp = p_35762_;
            this.priceMultiplier = p_35763_;
        }

        public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    public static class SuspiciousStewForEmerald implements VillagerTrades.ItemListing {
        final MobEffect effect;
        final int duration;
        final int xp;
        private final float priceMultiplier;

        public SuspiciousStewForEmerald(MobEffect p_186313_, int p_186314_, int p_186315_) {
            this.effect = p_186313_;
            this.duration = p_186314_;
            this.xp = p_186315_;
            this.priceMultiplier = 0.05F;
        }

        @Nullable
        public MerchantOffer getOffer(Entity p_219702_, RandomSource p_219703_) {
            ItemStack itemstack = new ItemStack(Items.SUSPICIOUS_STEW, 1);
            SuspiciousStewItem.saveMobEffect(itemstack, this.effect, this.duration);
            return new MerchantOffer(new ItemStack(Items.EMERALD, 1), itemstack, 12, this.xp, this.priceMultiplier);
        }
    }

    public static class TippedArrowForItemsAndEmeralds implements VillagerTrades.ItemListing {
        private final ItemStack toItem;
        private final int toCount;
        private final int emeraldCost;
        private final int maxUses;
        private final int villagerXp;
        private final Item fromItem;
        private final int fromCount;
        private final float priceMultiplier;

        public TippedArrowForItemsAndEmeralds(Item p_35793_, int p_35794_, Item p_35795_, int p_35796_, int p_35797_, int p_35798_, int p_35799_) {
            this.toItem = new ItemStack(p_35795_);
            this.emeraldCost = p_35797_;
            this.maxUses = p_35798_;
            this.villagerXp = p_35799_;
            this.fromItem = p_35793_;
            this.fromCount = p_35794_;
            this.toCount = p_35796_;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity p_219705_, RandomSource p_219706_) {
            ItemStack itemstack = new ItemStack(Items.EMERALD, this.emeraldCost);
            List<Potion> list = Registry.POTION.stream().filter((p_35804_) -> {
                return !p_35804_.getEffects().isEmpty() && PotionBrewing.isBrewablePotion(p_35804_);
            }).collect(Collectors.toList());
            Potion potion = list.get(p_219706_.nextInt(list.size()));
            ItemStack itemstack1 = PotionUtils.setPotion(new ItemStack(this.toItem.getItem(), this.toCount), potion);
            return new MerchantOffer(itemstack, new ItemStack(this.fromItem, this.fromCount), itemstack1, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    public static class TreasureMapForEmeralds implements VillagerTrades.ItemListing {
        private final int emeraldCost;
        private final TagKey<Structure> destination;
        private final String displayName;
        private final MapDecoration.Type destinationType;
        private final int maxUses;
        private final int villagerXp;

        public TreasureMapForEmeralds(int p_207767_, TagKey<Structure> p_207768_, String p_207769_, MapDecoration.Type p_207770_, int p_207771_, int p_207772_) {
            this.emeraldCost = p_207767_;
            this.destination = p_207768_;
            this.displayName = p_207769_;
            this.destinationType = p_207770_;
            this.maxUses = p_207771_;
            this.villagerXp = p_207772_;
        }

        @Nullable
        public MerchantOffer getOffer(Entity p_219708_, RandomSource p_219709_) {
            if (!(p_219708_.level instanceof ServerLevel)) {
                return null;
            } else {
                ServerLevel serverlevel = (ServerLevel)p_219708_.level;
                BlockPos blockpos = serverlevel.findNearestMapStructure(this.destination, p_219708_.blockPosition(), 100, true);
                if (blockpos != null) {
                    ItemStack itemstack = MapItem.create(serverlevel, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
                    MapItem.renderBiomePreviewMap(serverlevel, itemstack);
                    MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
                    itemstack.setHoverName(Component.translatable(this.displayName));
                    return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
                } else {
                    return null;
                }
            }
        }
    }
}

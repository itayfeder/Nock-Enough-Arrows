package com.itayfeder.nock_enough_arrows.quiver;

import com.itayfeder.nock_enough_arrows.init.EnchantmentInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class QuiverItem extends Item implements DyeableLeatherItem {
    private static final String TAG_ITEMS = "Items";
    public static final int MAX_WEIGHT = 64;
    private static final int BUNDLE_IN_BUNDLE_WEIGHT = 4;
    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);

    public QuiverItem(Item.Properties p_150726_) {
        super(p_150726_);
    }

    public boolean overrideStackedOnOther(ItemStack p_150733_, Slot p_150734_, ClickAction p_150735_, Player p_150736_) {
        if (p_150735_ != ClickAction.SECONDARY) {
            return false;
        } else {
            ItemStack itemstack = p_150734_.getItem();
            if (itemstack.isEmpty()) {
                this.playRemoveOneSound(p_150736_);
                p_150734_.set(removeOne(p_150733_).get());
            } else if (itemstack.getItem().canFitInsideContainerItems() && itemstack.is(ItemTags.ARROWS)) {
                QuiverItemStackHandler handler = getQuiverItemStackHandler(p_150733_);
                itemstack.setCount(handler.addStack(itemstack).getCount());

            }

            return true;
        }
    }

    public boolean overrideOtherStackedOnMe(ItemStack p_150742_, ItemStack p_150743_, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (p_150743_.isEmpty()) {
                removeOne(p_150742_).ifPresent((p_186347_) -> {
                    this.playRemoveOneSound(p_150746_);
                    p_150747_.set(p_186347_);
                });
            } else {
                if (p_150743_.is(ItemTags.ARROWS)) {
                    QuiverItemStackHandler handler = getQuiverItemStackHandler(p_150742_);
                    ItemStack addedStack = handler.addStack(p_150743_);
                    if (addedStack != p_150743_) {
                        this.playInsertSound(p_150746_);
                        p_150743_.setCount(addedStack.getCount());
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public InteractionResultHolder<ItemStack> use(Level p_150760_, Player p_150761_, InteractionHand p_150762_) {
        ItemStack itemstack = p_150761_.getItemInHand(p_150762_);
        if (dropContents(itemstack, p_150761_)) {
            this.playDropContentsSound(p_150761_);
            p_150761_.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.sidedSuccess(itemstack, p_150760_.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    public boolean isBarVisible(ItemStack p_150769_) {
        return getContentWeight(p_150769_) > 0;
    }

    public int getBarWidth(ItemStack p_150771_) {
        QuiverItemStackHandler handler = getQuiverItemStackHandler(p_150771_);

        return Math.min(1 + 12 * getContentWeight(p_150771_) / handler.getSlots(), 13);
    }

    public int getBarColor(ItemStack p_150773_) {
        return BAR_COLOR;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new QuiverCapabilityProvider();
    }

    public static QuiverItemStackHandler getQuiverItemStackHandler(ItemStack stack) {
        IItemHandler quiver = (IItemHandler)stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse((IItemHandler) null);
        if (quiver != null && quiver instanceof QuiverItemStackHandler) {
            return (QuiverItemStackHandler)quiver;
        } else {
            return new QuiverItemStackHandler();
        }
    }

    @Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag baseTag = stack.getTag();
        QuiverItemStackHandler quiverItemStackHandler = getQuiverItemStackHandler(stack);
        CompoundTag capTag = quiverItemStackHandler.serializeNBT();
        CompoundTag combinedTag = new CompoundTag();
        if (baseTag != null) {
            combinedTag.put("base", baseTag);
        }

        if (capTag != null) {
            combinedTag.put("cap", capTag);
        }

        return combinedTag;
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt) {
        if (nbt == null) {
            stack.setTag((CompoundTag)null);
        } else {
            CompoundTag baseTag = nbt.getCompound("base");
            stack.setTag(baseTag);
            CompoundTag capTag = nbt.getCompound("cap");
            QuiverItemStackHandler quiverItemStackHandler = getQuiverItemStackHandler(stack);
            quiverItemStackHandler.deserializeNBT(capTag);
        }
    }

    public static float getFullnessDisplay(ItemStack p_150767_) {
        QuiverItemStackHandler handler = getQuiverItemStackHandler(p_150767_);

        return (float)getContentWeight(p_150767_) / handler.getSlots();
    }

    private static int getContentWeight(ItemStack p_150779_) {
        return getContents(p_150779_).toList().size();
    }

    private static Optional<ItemStack> removeOne(ItemStack p_150781_) {
        QuiverItemStackHandler handler = getQuiverItemStackHandler(p_150781_);
        for (int i = 0; i < handler.getSlots(); i++) {
            if (handler.getStackInSlot(i) != ItemStack.EMPTY) {
                ItemStack firstStack = handler.getStackInSlot(i);
                handler.extractItem(i, firstStack.getCount(), false);
                return Optional.of(firstStack);
            }
        }
        return Optional.empty();
    }

    private static boolean dropContents(ItemStack p_150730_, Player p_150731_) {
        QuiverItemStackHandler handler = getQuiverItemStackHandler(p_150730_);
        if (handler.isEmpty()) return false;
        else {
            if (p_150731_ instanceof ServerPlayer) {
                List<ItemStack> listtag = handler.getItems();

                for(int i = 0; i < listtag.size(); ++i) {
                    ItemStack itemstack = listtag.get(i);
                    p_150731_.drop(itemstack, true);
                    handler.extractItem(i, itemstack.getCount(), false);
                }
            }


            return true;
        }
    }



    private static Stream<ItemStack> getContents(ItemStack p_150783_) {
        QuiverItemStackHandler handler = getQuiverItemStackHandler(p_150783_);
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < handler.getSlots(); i++) {
            if (handler.getStackInSlot(i) != ItemStack.EMPTY) {
                items.add(handler.getStackInSlot(i));
            }
        }
        return items.stream();
    }

    public Optional<TooltipComponent> getTooltipImage(ItemStack p_150775_) {
        NonNullList<ItemStack> nonnulllist = NonNullList.create();
        getContents(p_150775_).forEach(nonnulllist::add);
        return Optional.of(new BundleTooltip(nonnulllist, getContentWeight(p_150775_)));
    }

    public void appendHoverText(ItemStack p_150749_, Level p_150750_, List<Component> p_150751_, TooltipFlag p_150752_) {
        QuiverItemStackHandler handler = getQuiverItemStackHandler(p_150749_);

        p_150751_.add((new TranslatableComponent("item.minecraft.bundle.fullness", getContentWeight(p_150749_), handler.getSlots())).withStyle(ChatFormatting.GRAY));
    }


    public void onDestroyed(ItemEntity p_150728_) {
        ItemUtils.onContainerDestroyed(p_150728_, getContents(p_150728_.getItem()));
    }

    private void playRemoveOneSound(Entity p_186343_) {
        p_186343_.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + p_186343_.getLevel().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity p_186352_) {
        p_186352_.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + p_186352_.getLevel().getRandom().nextFloat() * 0.4F);
    }

    private void playDropContentsSound(Entity p_186354_) {
        p_186354_.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + p_186354_.getLevel().getRandom().nextFloat() * 0.4F);
    }


    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        QuiverItemStackHandler handler = getQuiverItemStackHandler(p_41404_);
        int level = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.STOCKPILE.get(), p_41404_);
        if (handler.getSlots() != 5 + level) {
            if (handler.getSlots() > 5 + level) {
                List<ItemStack> stacks = handler.getItems();
                handler.setSize(5 + level);
                int i = 0;
                for (i = 0; i < 5 + level; i++) {
                    handler.addStack(stacks.get(i));
                }
                if (p_41406_ instanceof Player player) {
                    for(int j = i; j < stacks.size(); j++) {
                        ItemStack itemstack = stacks.get(j);
                        player.drop(itemstack, true);
                    }
                }


            }
            else {
                List<ItemStack> stacks = handler.getItems();
                handler.setSize(5 + level);
                for (ItemStack stack : stacks) {
                    handler.addStack(stack);
                }
            }
        }
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return 5;
    }
}

package com.itayfeder.nock_enough_arrows.quiver;

import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class QuiverItemStackHandler extends ItemStackHandler {
    public static final int NUMBER_SLOTS = 5;

    public QuiverItemStackHandler() {
        super(5);
    }

    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        if (slot >= 0 && slot < this.getSlots()) {
            if (stack.isEmpty()) {
                return false;
            } else {
                return (stack.getItem() instanceof ArrowItem);
            }
        } else {
            throw new IllegalArgumentException("Invalid slot number: " + slot);
        }
    }

    public boolean isEmpty() {
        return this.stacks.isEmpty();
    }

    public List<ItemStack> getItems() {
        return this.stacks.stream().toList();
    }

    public int getFilledStacks() {
        int count = 0;
        for(int i = 0; i < getSlots(); i++) {
            if (!isAir(i)) count++;
        }
        return count;
    }

    public ItemStack addStack(ItemStack stack) {
        ItemStack addedStack = stack.copy();
        for (int i = 0; i < this.stacks.size(); i++) {
            addedStack = this.insertItem(i, addedStack, false);
            if (addedStack == ItemStack.EMPTY) i = this.stacks.size();
        }

        return addedStack;
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack extracted = super.extractItem(slot, amount, simulate);
        return extracted;
    }

    public int getFirstFilledSlot() {
        if (isAllAir()) return 0;
        else {
            for(int i = 0; i < getSlots(); i++) {
                if (!isAir(i)) return i;
            }
        }
        return 0;
    }

    public boolean isAllAir() {
        for (ItemStack stack : getItems()) {
            if (stack.getItem() != Items.AIR) return false;
        }
        return true;
    }

    public boolean isAir(int slot) {
        return getItems().get(slot).getItem() == Items.AIR;
    }
}

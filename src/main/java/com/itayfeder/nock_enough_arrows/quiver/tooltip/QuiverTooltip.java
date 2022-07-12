package com.itayfeder.nock_enough_arrows.quiver.tooltip;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public class QuiverTooltip implements TooltipComponent {
    private final NonNullList<ItemStack> items;
    private final ItemStack quiver;
    private final int weight;

    public QuiverTooltip(NonNullList<ItemStack> p_150677_, int p_150678_, ItemStack quiver) {
        this.items = p_150677_;
        this.weight = p_150678_;
        this.quiver = quiver;
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public ItemStack getQuiver() {
        return this.quiver;
    }

    public int getWeight() {
        return this.weight;
    }
}
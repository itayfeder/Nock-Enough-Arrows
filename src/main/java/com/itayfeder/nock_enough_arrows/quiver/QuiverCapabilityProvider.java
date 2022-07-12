package com.itayfeder.nock_enough_arrows.quiver;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class QuiverCapabilityProvider implements ICapabilitySerializable<CompoundTag> {
    private QuiverItemStackHandler quiverItemStackHandler;
    private final LazyOptional<IItemHandler> lazyInitialisationSupplier = LazyOptional.of(this::getCachedInventory);


    @Nonnull
    private QuiverItemStackHandler getCachedInventory() {
        if (this.quiverItemStackHandler == null) {
            this.quiverItemStackHandler = new QuiverItemStackHandler();
        }

        return this.quiverItemStackHandler;
    }

    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (LazyOptional<T>) this.lazyInitialisationSupplier : LazyOptional.empty();
    }

    public CompoundTag serializeNBT() {
        return this.getCachedInventory().serializeNBT();
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.getCachedInventory().deserializeNBT(nbt);
    }
}

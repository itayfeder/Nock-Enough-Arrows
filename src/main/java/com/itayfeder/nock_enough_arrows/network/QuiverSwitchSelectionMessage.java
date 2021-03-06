package com.itayfeder.nock_enough_arrows.network;

import com.itayfeder.nock_enough_arrows.compat.curios.CuriosCompat;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItem;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItemStackHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class QuiverSwitchSelectionMessage {
    public QuiverSwitchSelectionMessage(FriendlyByteBuf buffer) {

    }

    public QuiverSwitchSelectionMessage() {

    }

    public static void buffer(QuiverSwitchSelectionMessage message, FriendlyByteBuf buffer) {
    }

    public static void handler(QuiverSwitchSelectionMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ItemStack usedQuiver = null;
            Player player = context.getSender();
            if (ModList.get().isLoaded("curios")) {
                usedQuiver = CuriosCompat.getQuiver(player);
            }
            for(int i = 0; i < player.getInventory().getContainerSize() && usedQuiver == null; ++i) {
                ItemStack itemstack1 = player.getInventory().getItem(i);
                if (itemstack1.is(ItemInit.QUIVER.get())) {
                    usedQuiver = itemstack1;
                    break;
                }
            }

            if (usedQuiver != null) {
                QuiverItemStackHandler quiverItemStackHandler = ((QuiverItem) usedQuiver.getItem()).getQuiverItemStackHandler(usedQuiver);
                if (!quiverItemStackHandler.isAllAir()) {
                    int selection = ((QuiverItem) usedQuiver.getItem()).getSelected(usedQuiver);
                    while (quiverItemStackHandler.isAir((selection + 1) % quiverItemStackHandler.getSlots())) {
                        selection++;
                    }
                    ((QuiverItem) usedQuiver.getItem()).setSelected(usedQuiver, (selection + 1) % quiverItemStackHandler.getSlots());
                }

            }
        });
        context.setPacketHandled(true);
    }
}

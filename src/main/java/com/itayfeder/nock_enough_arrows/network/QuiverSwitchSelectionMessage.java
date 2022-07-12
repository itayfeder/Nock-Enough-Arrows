package com.itayfeder.nock_enough_arrows.network;

import com.itayfeder.nock_enough_arrows.init.ItemInit;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItem;
import com.itayfeder.nock_enough_arrows.quiver.QuiverItemStackHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkEvent;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
                Optional<ImmutableTriple<String, Integer, ItemStack>> triplet = CuriosApi.getCuriosHelper().findEquippedCurio(ItemInit.QUIVER.get(), player);
                if (!triplet.isEmpty()) {
                    usedQuiver = triplet.get().getRight();
                }
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

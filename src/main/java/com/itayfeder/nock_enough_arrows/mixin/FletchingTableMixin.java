package com.itayfeder.nock_enough_arrows.mixin;

import com.itayfeder.nock_enough_arrows.fletching_table.FletchingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FletchingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FletchingTableBlock.class)
public class FletchingTableMixin {
    private static final Component SCREEN_TITLE = new TranslatableComponent("container.fletch");

    @Inject(locals = LocalCapture.CAPTURE_FAILHARD,
            method = "use",
            at = @At(value = "RETURN"),
            cancellable = true
    )
    private void openContainerInjection(BlockState p_53501_, Level p_53502_, BlockPos p_53503_, Player p_53504_, InteractionHand p_53505_, BlockHitResult p_53506_, CallbackInfoReturnable<InteractionResult> cir) {
        if (p_53502_.isClientSide) {
            cir.setReturnValue(InteractionResult.SUCCESS);
        } else {
            p_53504_.openMenu(getContainer(p_53501_, p_53502_, p_53503_));
            p_53504_.awardStat(Stats.INTERACT_WITH_SMITHING_TABLE);
            cir.setReturnValue(InteractionResult.CONSUME);
        }
    }

    public MenuProvider getContainer(BlockState p_220052_1_, Level p_220052_2_, BlockPos p_220052_3_) {
        return new SimpleMenuProvider((p_235576_2_, p_235576_3_, p_235576_4_) -> {
            return new FletchingTableMenu(p_235576_2_, p_235576_3_, ContainerLevelAccess.create(p_220052_2_, p_220052_3_));
        }, SCREEN_TITLE);
    }
}

package com.itayfeder.nock_enough_arrows.mixin;

import com.itayfeder.nock_enough_arrows.arrows.reinforced.ReinforcedArrow;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(DamageSource.class)
public class DamageSourceMixin {
    @Inject(locals = LocalCapture.CAPTURE_FAILHARD,
            method = "arrow",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private static void reinforcedArrowInjection(AbstractArrow p_19347_, Entity p_19348_, CallbackInfoReturnable<DamageSource> cir) {
        if (p_19347_ instanceof ReinforcedArrow) {
            cir.setReturnValue((new IndirectEntityDamageSource("arrow", p_19347_, p_19348_)).setProjectile().bypassArmor());
        }
    }
}

package com.itayfeder.nock_enough_arrows.arrows.teleportation;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TeleportationArrowRenderer extends ArrowRenderer<TeleportationArrow> {
    public static final ResourceLocation RES_TELEPORTATION_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/teleportation_arrow.png");

    public TeleportationArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(TeleportationArrow p_116001_) {
        return RES_TELEPORTATION_ARROW;
    }
}
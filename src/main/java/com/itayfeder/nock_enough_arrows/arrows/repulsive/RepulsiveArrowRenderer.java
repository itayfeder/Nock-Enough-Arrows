package com.itayfeder.nock_enough_arrows.arrows.repulsive;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RepulsiveArrowRenderer extends ArrowRenderer<RepulsiveArrow> {
    public static final ResourceLocation RES_REPULSIVE_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/repulsive_arrow.png");

    public RepulsiveArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(RepulsiveArrow p_116001_) {
        return RES_REPULSIVE_ARROW;
    }
}
package com.itayfeder.nock_enough_arrows.arrows.pufferfish;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PufferfishArrowRenderer extends ArrowRenderer<PufferfishArrow> {
    public static final ResourceLocation RES_PUFFERFISH_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/pufferfish_arrow.png");

    public PufferfishArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(PufferfishArrow p_116001_) {
        return RES_PUFFERFISH_ARROW;
    }
}
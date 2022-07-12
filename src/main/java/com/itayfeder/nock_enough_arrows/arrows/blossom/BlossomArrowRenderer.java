package com.itayfeder.nock_enough_arrows.arrows.blossom;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BlossomArrowRenderer extends ArrowRenderer<BlossomArrow> {
    public static final ResourceLocation RES_BLOSSOM_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/blossom_arrow.png");

    public BlossomArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(BlossomArrow p_116001_) {
        return RES_BLOSSOM_ARROW;
    }
}
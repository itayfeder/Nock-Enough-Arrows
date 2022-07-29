package com.itayfeder.nock_enough_arrows.arrows.cupid;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class CupidArrowRenderer extends ArrowRenderer<CupidArrow> {
    public static final ResourceLocation RES_CUPID_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/cupid_arrow.png");

    public CupidArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(CupidArrow p_116001_) {
        return RES_CUPID_ARROW;
    }
}
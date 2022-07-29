package com.itayfeder.nock_enough_arrows.arrows.seeker;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SeekerArrowRenderer extends ArrowRenderer<SeekerArrow> {
    public static final ResourceLocation RES_SEARCHER_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/seeker_arrow.png");

    public SeekerArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(SeekerArrow p_116001_) {
        return RES_SEARCHER_ARROW;
    }
}
package com.itayfeder.nock_enough_arrows.arrows.split;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SplitArrowRenderer extends ArrowRenderer<SplitArrow> {
    public static final ResourceLocation RES_SPLIT_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/split_arrow.png");

    public SplitArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(SplitArrow p_116001_) {
        return RES_SPLIT_ARROW;
    }
}
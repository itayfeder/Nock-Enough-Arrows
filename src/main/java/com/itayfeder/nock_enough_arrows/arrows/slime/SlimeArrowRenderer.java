package com.itayfeder.nock_enough_arrows.arrows.slime;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SlimeArrowRenderer extends ArrowRenderer<SlimeArrow> {
    public static final ResourceLocation RES_SLIME_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/slime_arrow.png");

    public SlimeArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(SlimeArrow p_116001_) {
        return RES_SLIME_ARROW;
    }
}
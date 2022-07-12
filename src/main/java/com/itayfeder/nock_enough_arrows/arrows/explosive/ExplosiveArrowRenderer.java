package com.itayfeder.nock_enough_arrows.arrows.explosive;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ExplosiveArrowRenderer extends ArrowRenderer<ExplosiveArrow> {
    public static final ResourceLocation RES_EXPLOSIVE_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/explosive_arrow.png");

    public ExplosiveArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(ExplosiveArrow p_116001_) {
        return RES_EXPLOSIVE_ARROW;
    }
}
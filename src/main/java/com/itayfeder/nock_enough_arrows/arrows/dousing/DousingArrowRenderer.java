package com.itayfeder.nock_enough_arrows.arrows.dousing;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class DousingArrowRenderer extends ArrowRenderer<DousingArrow> {
    public static final ResourceLocation RES_DOUSING_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/dousing_arrow.png");

    public DousingArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(DousingArrow p_116001_) {
        return RES_DOUSING_ARROW;
    }
}
package com.itayfeder.nock_enough_arrows.arrows.ethereal;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EtherealArrowRenderer extends ArrowRenderer<EtherealArrow> {
    public static final ResourceLocation RES_ETHEREAL_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/ethereal_arrow.png");

    public EtherealArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(EtherealArrow p_116001_) {
        return RES_ETHEREAL_ARROW;
    }
}
package com.itayfeder.nock_enough_arrows.arrows.echoing;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EchoingArrowRenderer extends ArrowRenderer<EchoingArrow> {
    public static final ResourceLocation RES_ECHOING_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/echoing_arrow.png");

    public EchoingArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(EchoingArrow p_116001_) {
        return RES_ECHOING_ARROW;
    }
}
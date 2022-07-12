package com.itayfeder.nock_enough_arrows.arrows.hookshot;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class HookshotArrowRenderer extends ArrowRenderer<HookshotArrow> {
    public static final ResourceLocation RES_HOOKSHOT_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/hookshot_arrow.png");

    public HookshotArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(HookshotArrow p_116001_) {
        return RES_HOOKSHOT_ARROW;
    }
}
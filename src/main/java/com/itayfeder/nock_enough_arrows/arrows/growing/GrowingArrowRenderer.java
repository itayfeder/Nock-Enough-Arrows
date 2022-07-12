package com.itayfeder.nock_enough_arrows.arrows.growing;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class GrowingArrowRenderer extends ArrowRenderer<GrowingArrow> {
    public static final ResourceLocation RES_GROWING_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/growing_arrow.png");

    public GrowingArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    @Override
    public void render(GrowingArrow p_113839_, float p_113840_, float p_113841_, PoseStack p_113842_, MultiBufferSource p_113843_, int p_113844_) {
        int f1 = p_113839_.getSize();
        p_113842_.scale(1 + f1 * 0.5F, 1 + f1 * 0.5F, 1 + f1 * 0.5F);
        super.render(p_113839_, p_113840_, p_113841_, p_113842_, p_113843_, p_113844_);
    }

    public ResourceLocation getTextureLocation(GrowingArrow p_116001_) {
        return RES_GROWING_ARROW;
    }
}
package com.itayfeder.nock_enough_arrows.arrows.soul_torch;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SoulTorchArrowRenderer extends ArrowRenderer<SoulTorchArrow> {
    public static final ResourceLocation RES_TORCH_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/soul_torch_arrow.png");

    public SoulTorchArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(SoulTorchArrow p_116001_) {
        return RES_TORCH_ARROW;
    }
}
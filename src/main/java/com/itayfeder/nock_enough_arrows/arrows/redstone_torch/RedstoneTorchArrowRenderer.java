package com.itayfeder.nock_enough_arrows.arrows.redstone_torch;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RedstoneTorchArrowRenderer extends ArrowRenderer<RedstoneTorchArrow> {
    public static final ResourceLocation RES_TORCH_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/redstone_torch_arrow.png");

    public RedstoneTorchArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(RedstoneTorchArrow p_116001_) {
        return RES_TORCH_ARROW;
    }
}
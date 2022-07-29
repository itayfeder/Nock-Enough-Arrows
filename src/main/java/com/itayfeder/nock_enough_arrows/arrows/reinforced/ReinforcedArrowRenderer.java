package com.itayfeder.nock_enough_arrows.arrows.reinforced;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ReinforcedArrowRenderer extends ArrowRenderer<ReinforcedArrow> {
    public static final ResourceLocation RES_REINFORCED_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/reinforced_arrow.png");

    public ReinforcedArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(ReinforcedArrow p_116001_) {
        return RES_REINFORCED_ARROW;
    }
}
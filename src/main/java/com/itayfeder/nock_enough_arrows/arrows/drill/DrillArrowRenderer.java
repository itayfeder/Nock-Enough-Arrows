package com.itayfeder.nock_enough_arrows.arrows.drill;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class DrillArrowRenderer extends ArrowRenderer<DrillArrow> {
    public static final ResourceLocation RES_DRILL_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/drill_arrow.png");

    public DrillArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(DrillArrow p_116001_) {
        return RES_DRILL_ARROW;
    }
}
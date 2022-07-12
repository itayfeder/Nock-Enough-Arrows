package com.itayfeder.nock_enough_arrows.arrows.message;

import com.itayfeder.nock_enough_arrows.NockEnoughArrowsMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MessageArrowRenderer extends ArrowRenderer<MessageArrow> {
    public static final ResourceLocation RES_MESSAGE_ARROW = new ResourceLocation(NockEnoughArrowsMod.MOD_ID, "textures/entity/projectiles/message_arrow.png");

    public MessageArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(MessageArrow p_116001_) {
        return RES_MESSAGE_ARROW;
    }
}
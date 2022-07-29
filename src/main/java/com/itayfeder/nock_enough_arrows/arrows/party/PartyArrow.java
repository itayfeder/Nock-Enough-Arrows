package com.itayfeder.nock_enough_arrows.arrows.party;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.network.PlayMessages;

public class PartyArrow extends AbstractArrow {
    private int exist;

    public PartyArrow(EntityType<? extends PartyArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public PartyArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.PARTY_ARROW.get(), p_37420_, p_37419_);
    }

    public PartyArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.PARTY_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public PartyArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.PARTY_ARROW.get(), world);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.exist;
        if (exist >= 20) {
            this.splash();
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.PARTY_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
        this.splash();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        this.splash();
    }

    private void splash() {
        ItemStack fireworkStack = new ItemStack(Items.FIREWORK_ROCKET);
        CompoundTag compoundtag = fireworkStack.getOrCreateTagElement("Fireworks");
        ListTag listtag = new ListTag();
        int i = 0;

        CompoundTag explosion = new CompoundTag();
        explosion.putIntArray("Colors", new int[]{11546150, 16351261, 16701501, 8439583, 3847130, 8991416});
        explosion.putByte("Type", (byte)0);
        listtag.add(explosion);

        compoundtag.putByte("Flight", (byte)i);
        compoundtag.put("Explosions", listtag);

        FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(level, this.getOwner(), this.getX(), this.getY(), this.getZ(), fireworkStack);

        level.addFreshEntity(fireworkrocketentity);
        fireworkrocketentity.lifetime = 0;
        //ObfuscationReflectionHelper.setPrivateValue(FireworkRocketEntity.class, fireworkrocketentity, 0, "lifetime");

        this.discard();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_36761_) {
        super.readAdditionalSaveData(p_36761_);
        this.exist = p_36761_.getShort("exist");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_36772_) {
        super.addAdditionalSaveData(p_36772_);
        p_36772_.putShort("exist", (short)this.exist);

    }
}

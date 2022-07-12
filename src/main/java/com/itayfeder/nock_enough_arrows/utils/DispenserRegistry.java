package com.itayfeder.nock_enough_arrows.utils;

import com.itayfeder.nock_enough_arrows.arrows.blossom.BlossomArrow;
import com.itayfeder.nock_enough_arrows.arrows.dousing.DousingArrow;
import com.itayfeder.nock_enough_arrows.arrows.drill.DrillArrow;
import com.itayfeder.nock_enough_arrows.arrows.echoing.EchoingArrow;
import com.itayfeder.nock_enough_arrows.arrows.ethereal.EtherealArrow;
import com.itayfeder.nock_enough_arrows.arrows.explosive.ExplosiveArrow;
import com.itayfeder.nock_enough_arrows.arrows.growing.GrowingArrow;
import com.itayfeder.nock_enough_arrows.arrows.hookshot.HookshotArrow;
import com.itayfeder.nock_enough_arrows.arrows.ink.InkArrow;
import com.itayfeder.nock_enough_arrows.arrows.message.MessageArrow;
import com.itayfeder.nock_enough_arrows.arrows.party.PartyArrow;
import com.itayfeder.nock_enough_arrows.arrows.prismarine.PrismarineArrow;
import com.itayfeder.nock_enough_arrows.arrows.pufferfish.PufferfishArrow;
import com.itayfeder.nock_enough_arrows.arrows.redstone_torch.RedstoneTorchArrow;
import com.itayfeder.nock_enough_arrows.arrows.repulsive.RepulsiveArrow;
import com.itayfeder.nock_enough_arrows.arrows.slime.SlimeArrow;
import com.itayfeder.nock_enough_arrows.arrows.soul_torch.SoulTorchArrow;
import com.itayfeder.nock_enough_arrows.arrows.split.SplitArrow;
import com.itayfeder.nock_enough_arrows.arrows.teleportation.TeleportationArrow;
import com.itayfeder.nock_enough_arrows.arrows.torch.TorchArrow;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class DispenserRegistry {
    public static void registerBehaviors() {
        DispenserBlock.registerBehavior(ItemInit.PUFFERFISH_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new PufferfishArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.EXPLOSIVE_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new ExplosiveArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.SLIME_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new SlimeArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.PRISMARINE_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new PrismarineArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.HOOKSHOT_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new HookshotArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.MESSAGE_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new MessageArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.TELEPORTATION_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new TeleportationArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.INK_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new InkArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.TORCH_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new TorchArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.SOUL_TORCH_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new SoulTorchArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.REDSTONE_TORCH_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new RedstoneTorchArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.ETHEREAL_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new EtherealArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.DOUSING_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new DousingArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.BLOSSOM_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new BlossomArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.GROWING_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new GrowingArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.DRILL_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new DrillArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.SPLIT_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new SplitArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.PARTY_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new PartyArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.REPULSIVE_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new RepulsiveArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
        DispenserBlock.registerBehavior(ItemInit.ECHOING_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
                AbstractArrow abstractarrow = new EchoingArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
                abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return abstractarrow;
            }
        });
    }
}

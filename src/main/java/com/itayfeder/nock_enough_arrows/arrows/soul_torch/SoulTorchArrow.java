package com.itayfeder.nock_enough_arrows.arrows.soul_torch;

import com.itayfeder.nock_enough_arrows.init.EntityTypeInit;
import com.itayfeder.nock_enough_arrows.init.ItemInit;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class SoulTorchArrow extends AbstractArrow {
    public SoulTorchArrow(EntityType<? extends SoulTorchArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public SoulTorchArrow(Level p_37419_, LivingEntity p_37420_) {
        super(EntityTypeInit.SOUL_TORCH_ARROW.get(), p_37420_, p_37419_);
    }

    public SoulTorchArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(EntityTypeInit.SOUL_TORCH_ARROW.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    public SoulTorchArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(EntityTypeInit.SOUL_TORCH_ARROW.get(), world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.SOUL_TORCH_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
        p_36757_.getEntity().setSecondsOnFire(5);
    }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        switch (p_36755_.getDirection()) {
            case DOWN:
            default:
                break;
            case UP:
                if (this.level.isEmptyBlock(p_36755_.getBlockPos().above())) {
                    this.level.setBlockAndUpdate(p_36755_.getBlockPos().above(), Blocks.SOUL_TORCH.defaultBlockState());
                    this.kill();
                }
                break;
            case NORTH:
                if (this.level.isEmptyBlock(p_36755_.getBlockPos().north())) {
                    this.level.setBlockAndUpdate(p_36755_.getBlockPos().north(), Blocks.SOUL_WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, Direction.NORTH));
                    this.kill();
                }
                break;
            case SOUTH:
                if (this.level.isEmptyBlock(p_36755_.getBlockPos().south())) {
                    this.level.setBlockAndUpdate(p_36755_.getBlockPos().south(), Blocks.SOUL_WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, Direction.SOUTH));
                    this.kill();
                }
                break;
            case EAST:
                if (this.level.isEmptyBlock(p_36755_.getBlockPos().east())) {
                    this.level.setBlockAndUpdate(p_36755_.getBlockPos().east(), Blocks.SOUL_WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, Direction.EAST));
                    this.kill();
                }
                break;
            case WEST:
                if (this.level.isEmptyBlock(p_36755_.getBlockPos().west())) {
                    this.level.setBlockAndUpdate(p_36755_.getBlockPos().west(), Blocks.SOUL_WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, Direction.WEST));
                    this.kill();
                }
                break;
        }
    }

    private void summon() {
        Pufferfish entityW1 = (Pufferfish) EntityType.PUFFERFISH.create(this.level);
        entityW1.setPos((double) this.getX(), (double) this.getY(), (double) this.getZ());
        this.level.addFreshEntity(entityW1);
        this.kill();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_36761_) {
        super.readAdditionalSaveData(p_36761_);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_36772_) {
        super.addAdditionalSaveData(p_36772_);
    }
}

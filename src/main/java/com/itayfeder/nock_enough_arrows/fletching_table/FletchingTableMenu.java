package com.itayfeder.nock_enough_arrows.fletching_table;

import com.itayfeder.nock_enough_arrows.init.MenuInit;
import com.itayfeder.nock_enough_arrows.init.RecipeInit;
import com.itayfeder.nock_enough_arrows.recipes.fletching.FletchingRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class FletchingTableMenu extends AbstractContainerMenu {
    protected final ResultContainer output = new ResultContainer();
    protected final Container input = new SimpleContainer(3) {
        public void setChanged() {
            super.setChanged();
            FletchingTableMenu.this.slotsChanged(this);
        }
    };
    protected final ContainerLevelAccess context;
    protected final Player player;

    private final Level world;
    private FletchingRecipe recipe;
    private final List<FletchingRecipe> field_25668;


    public FletchingTableMenu(int i, Inventory playerInventory, FriendlyByteBuf packetBuffer) {
        this(i, playerInventory, ContainerLevelAccess.NULL);
    }

    public FletchingTableMenu(int p_i231587_3_, Inventory p_i231587_2_, ContainerLevelAccess p_i231587_4_) {
        super(MenuInit.FLETCHING_TABLE.get(), p_i231587_3_);
        this.context = p_i231587_4_;
        this.player = p_i231587_2_.player;
        this.world = p_i231587_2_.player.level;
        this.field_25668 = this.world.getRecipeManager().getAllRecipesFor(RecipeInit.FLETCHING_RECIPE);

        this.addSlot(new Slot(this.input, 0, 30, 53));
        this.addSlot(new Slot(this.input, 1, 48, 35));
        this.addSlot(new Slot(this.input, 2, 66, 17));
        this.addSlot(new Slot(this.output, 3, 124, 35) {
            /**
             * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
             */
            public boolean mayPlace(ItemStack p_75214_1_) {
                return false;
            }

            public boolean mayPickup(Player p_82869_1_) {
                return FletchingTableMenu.this.canTakeOutput(p_82869_1_, this.hasItem());
            }

            public void onTake(Player p_190901_1_, ItemStack p_190901_2_) {
                FletchingTableMenu.this.onTakeOutput(p_190901_1_, p_190901_2_);
            }

        });

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(p_i231587_2_, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(p_i231587_2_, l, 8 + l * 18, 142));
        }
    }

    public boolean stillValid(Player p_39149_) {
        return stillValid(this.context, p_39149_, Blocks.FLETCHING_TABLE);
    }

    public ItemStack quickMoveStack(Player p_39391_, int p_39392_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_39392_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (p_39392_ == 0) {
                this.context.execute((p_39378_, p_39379_) -> {
                    itemstack1.getItem().onCraftedBy(itemstack1, p_39378_, p_39391_);
                });
                if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (p_39392_ >= 4 && p_39392_ < 40) {
                if (!this.moveItemStackTo(itemstack1, 0, 4, false)) {
                    if (p_39392_ < 31) {
                        if (!this.moveItemStackTo(itemstack1, 31, 40, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, 4, 31, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(p_39391_, itemstack1);
            if (p_39392_ == 0) {
                p_39391_.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    public void slotsChanged(Container p_39778_) {
        super.slotsChanged(p_39778_);
        if (p_39778_ == this.input) {
            this.updateRepairOutput();
        }

    }

    public void updateRepairOutput() {
        List<FletchingRecipe> list = this.world.getRecipeManager().getRecipesFor(RecipeInit.FLETCHING_RECIPE, this.input, this.world);
        if (list.isEmpty()) {
            this.output.setItem(0, ItemStack.EMPTY);
        } else {
            this.recipe = list.get(0);
            ItemStack itemstack = this.recipe.assemble(this.input);
            this.output.setRecipeUsed(this.recipe);
            this.output.setItem(0, itemstack);
        }

    }

    protected boolean canTakeOutput(Player p_40268_, boolean p_40269_) {
        return this.recipe != null && this.recipe.matches(this.input, this.world);
    }

    protected void onTakeOutput(Player p_230301_1_, ItemStack p_230301_2_) {
        p_230301_2_.onCraftedBy(p_230301_1_.level, p_230301_1_, p_230301_2_.getCount());
        this.output.awardUsedRecipes(p_230301_1_);
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.shrinkStackInSlot(2);
        this.context.execute((p_234653_0_, p_234653_1_) -> {
            p_234653_0_.levelEvent(1044, p_234653_1_, 0);
        });
    }

    private void shrinkStackInSlot(int p_40271_) {
        ItemStack itemstack = this.input.getItem(p_40271_);
        itemstack.shrink(1);
        this.input.setItem(p_40271_, itemstack);
    }

    public void removed(Player p_39790_) {
        super.removed(p_39790_);
        this.context.execute((p_39796_, p_39797_) -> {
            this.clearContainer(p_39790_, this.input);
        });
    }
}

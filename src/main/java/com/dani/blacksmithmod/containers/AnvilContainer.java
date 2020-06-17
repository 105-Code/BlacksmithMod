package com.dani.blacksmithmod.containers;

import com.dani.blacksmithmod.objects.IngredientSlot;
import com.dani.blacksmithmod.setup.BlockRegister;
import com.dani.blacksmithmod.setup.ContainerRegister;
import com.dani.blacksmithmod.tiles.AnvilTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class AnvilContainer extends Container {

    private AnvilTileEntity tileEntity;
    private PlayerEntity playerEntity;
    private ItemStackHandler ingredients;

    public AnvilContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(ContainerRegister.ANVIL_CONTAINER, windowId);
        tileEntity = (AnvilTileEntity) world.getTileEntity(pos);
        this.ingredients = tileEntity.getMaterials();
        this.playerEntity = player;
        this.addPlayerSlots(playerInventory);
        this.anvilSlots();

    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < this.inventorySlots.size()) {
                // From the block breaker inventory to player's inventory
                if (!this.mergeItemStack(current, 0,  36, true))
                    return ItemStack.EMPTY;
            } else {
                // From the player's inventory to block breaker's inventory
                if (!this.mergeItemStack(current, 0, 9, false))
                    return ItemStack.EMPTY;
            }

            if (current.getCount() == 0) //Use func_190916_E() instead of stackSize 1.11 only 1.11.2 use getCount()
                slot.putStack(ItemStack.EMPTY); //Use ItemStack.field_190927_a instead of (ItemStack)null for a blank item stack. In 1.11.2 use ItemStack.EMPTY
            else
                slot.onSlotChanged();

            if (current.getCount() == previous.getCount())
                return null;
            slot.onTake(playerIn, current);
        }
        return previous;
    }

    /**
     * Agrega los Slots del Inventario en la vista
     * @param playerInventory Jugador que abrio la GUI
     */
    private void addPlayerSlots(final PlayerInventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 79 + row * 18));
            }
        }
        for (int hotbar = 0; hotbar < 9; ++hotbar) {
            this.addSlot(new Slot(playerInventory, hotbar, 8 + hotbar * 18, 137));
        }
    }

    /**
     * Agrega los Slots de la interfaz
     */
    private void anvilSlots(){
        int index = 0;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                this.addSlot(new IngredientSlot(this.ingredients, index, 62 + col  * 18, 14 + row * 18));
                ++index;
            }
        }

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, BlockRegister.ANVIL);
    }


}

package com.dani.blacksmithmod.tiles.anviltileentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class MaterialStackHandler extends ItemStackHandler  implements IInventory {


    public MaterialStackHandler(int size){
        super(size);
    }

    @Override
    public int getSizeInventory() {
        return this.stacks.size();
    }

    @Override
    public boolean isEmpty() {
        return this.stacks.isEmpty();
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = this.getStackInSlot(index);
        if (stack.getCount() < count)
            return stack;
        stack.setCount(stack.getCount() - count);
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return this.stacks.remove(index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

    }

    @Override
    public void markDirty() {

    }


    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {

    }

    @Override
    protected void onContentsChanged(int slot) {
        System.out.println("Cambio la matriz");

    }
}

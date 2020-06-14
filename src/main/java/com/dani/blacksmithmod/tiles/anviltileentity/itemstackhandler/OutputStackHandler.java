package com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class OutputStackHandler extends ItemStackHandler {

    private boolean canInsertItem;

    public OutputStackHandler(){
        this.canInsertItem = false;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return this.canInsertItem;
    }

    public boolean outputItem(ItemStack itemStack){
        this.canInsertItem = true;
        this.setStackInSlot(0,itemStack);
        this.canInsertItem = false;
        return true;

    }
}

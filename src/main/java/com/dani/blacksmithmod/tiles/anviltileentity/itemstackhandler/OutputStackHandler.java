package com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class OutputStackHandler extends ItemStackHandler {

    public OutputStackHandler(int size){
        super(size);
    }

    public OutputStackHandler(){

    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return ItemStack.EMPTY;
    }


}

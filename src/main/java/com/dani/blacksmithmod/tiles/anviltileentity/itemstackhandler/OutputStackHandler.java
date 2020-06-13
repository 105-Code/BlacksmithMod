package com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler;

import com.dani.blacksmithmod.items.itemabstract.Pattern;
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
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return false;
    }

}

package com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler;

import com.dani.blacksmithmod.setup.ItemRegister;
import com.dani.blacksmithmod.tiles.anviltileentity.enums.TypePattern;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

    public void outputItem(TypePattern pattern){
        this.canInsertItem = true;
        switch (pattern){
            case SADDLE:
                this.setStackInSlot(0,new ItemStack(Items.SADDLE,1));
                break;
            case DIAMOND_SHIELD:
                this.setStackInSlot(0,new ItemStack(ItemRegister.DIAMOND_SHIELD,1));
                break;
            case GOLD_SHIELD:
                this.setStackInSlot(0,new ItemStack(ItemRegister.GOLD_SHIELD,1));
                break;
            case IRON_SHIELD:
                this.setStackInSlot(0,new ItemStack(ItemRegister.IRON_SHIELD,1));
                break;
            case SHIELD:
                this.setStackInSlot(0,new ItemStack(Items.SHIELD,1));
                break;
        }
        this.canInsertItem = false;

    }
}

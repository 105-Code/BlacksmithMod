package com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler;

import com.dani.blacksmithmod.items.DiamondShieldPattern;
import com.dani.blacksmithmod.items.GoldShieldPattern;
import com.dani.blacksmithmod.items.IronShield;
import com.dani.blacksmithmod.items.itemabstract.Pattern;
import com.dani.blacksmithmod.setup.RegistryHandler;
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
                this.setStackInSlot(0,new ItemStack(Items.SADDLE,1));
                break;
            case GOLD_SHIELD:
                this.setStackInSlot(0,new ItemStack(new IronShield(),1));
                break;
            case IRON_SHIELD:
                this.setStackInSlot(0,new ItemStack(RegistryHandler.IRON_SHIELD.get(),1));
                break;
            case SHIELD:
                this.setStackInSlot(0,new ItemStack(Items.SHIELD,1));
                break;
        }
        this.canInsertItem = false;

    }
}

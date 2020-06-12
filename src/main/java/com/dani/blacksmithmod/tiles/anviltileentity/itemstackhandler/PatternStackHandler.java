package com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler;

import com.dani.blacksmithmod.items.*;
import com.dani.blacksmithmod.items.itemabstract.Pattern;
import com.dani.blacksmithmod.tiles.anviltileentity.enums.TypePattern;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class PatternStackHandler extends ItemStackHandler {

    private TypePattern typePattern;

    public PatternStackHandler(int size){
        super(size);
    }

    public PatternStackHandler(){
        this.typePattern = TypePattern.NONE;
    }

    public TypePattern getTypePattern(){
        return this.typePattern;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {

        return stack.getItem() instanceof Pattern;
    }

    @Override
    protected void onContentsChanged(int slot) {
        Item item = this.getStackInSlot(slot).getItem();
        if(item instanceof Pattern)
            this.typePattern = ((Pattern)item).getTypePattern();
        else
            this.typePattern= TypePattern.NONE;
        
        System.out.println("Tipo de patron es" + this.typePattern.toString());

    }

}

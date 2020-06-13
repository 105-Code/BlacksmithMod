package com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler;

import com.dani.blacksmithmod.items.*;
import com.dani.blacksmithmod.items.itemabstract.Pattern;
import com.dani.blacksmithmod.tiles.anviltileentity.enums.TypePattern;
import com.dani.blacksmithmod.tiles.anviltileentity.interfaces.VerifyItemsPattern;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class PatternStackHandler extends ItemStackHandler implements VerifyItemsPattern {

    private ItemStack[] materialPattern;

    private TypePattern typePattern;



    public PatternStackHandler(int size){
        super(size);
    }

    public PatternStackHandler(){
        this.materialPattern= new ItemStack[]{};
        this.typePattern = TypePattern.NONE;
    }

    public ItemStack[] getMaterialPattern(){
        return this.materialPattern;
    }

    public TypePattern getTypePattern(){
        return  this.typePattern;
    }



    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {

        return stack.getItem() instanceof Pattern;
    }

    @Override
    protected void onContentsChanged(int slot) {
        Item item = this.getStackInSlot(slot).getItem();
        if(item instanceof Pattern) {
            this.materialPattern = ((Pattern) item).getPatternMaterials();
            this.typePattern = ((Pattern) item).getTypePattern();
        }
        else
            this.materialPattern= new ItemStack[]{};

    }


    @Override
    public boolean itemInPattern(Item item) {
        for (ItemStack value : this.materialPattern) {
            if (item.getRegistryName() == value.getItem().getRegistryName())
                return true;
        }
        return false;
    }
}

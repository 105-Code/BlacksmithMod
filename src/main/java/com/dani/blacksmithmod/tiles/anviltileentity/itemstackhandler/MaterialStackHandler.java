package com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler;

import com.dani.blacksmithmod.items.itemabstract.Pattern;
import com.dani.blacksmithmod.tiles.anviltileentity.interfaces.VerifyItemsPattern;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class MaterialStackHandler extends ItemStackHandler {

    private VerifyItemsPattern inter;

    public MaterialStackHandler(VerifyItemsPattern inter,int size){
        super(size);
       this.inter=inter;
    }


    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {

        return this.inter.itemInPattern(stack.getItem());
    }

    public boolean isEqual(ItemStack[] materials){
        int max = this.getSlots();
        short equals = 0;
        for(ItemStack stack:materials){
            for(int i = 0; i< max; i++){
                ItemStack stackInSlot = this.getStackInSlot(i);
                if(stackInSlot.isItemEqual(stack)){
                    if(stackInSlot.getCount() == stack.getCount())
                        equals++;
                }
            }
        }

        return equals == materials.length;
    }

    public void clear(){
        for(int i = 0; i < this.getSlots();i++){
            ItemStack stack= this.getStackInSlot(i);
            this.setStackInSlot(i,new ItemStack(Items.AIR) );
        }

    }

}

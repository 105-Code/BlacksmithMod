package com.dani.blacksmithmod.items.recipes;

import com.dani.blacksmithmod.items.itemabstract.Recipe;
import com.dani.blacksmithmod.setup.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.ItemStackHandler;

public class SaddleRecipe extends Recipe {


    public SaddleRecipe() {
        super( new Item[]{
                Items.LEATHER,Items.LEATHER,Items.LEATHER,
                Items.IRON_INGOT,Items.AIR,Items.IRON_INGOT,
                Items.AIR,Items.AIR,Items.AIR
        });
    }

    @Override
    public ItemStack getCraftingResult(ItemStackHandler inv) {
        inv.extractItem(0,1,false);
        inv.extractItem(1,1,false);
        inv.extractItem(2,1,false);
        inv.extractItem(3,1,false);
        inv.extractItem(5,1,false);
        return new ItemStack(Items.SADDLE);
    }

}

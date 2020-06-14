package com.dani.blacksmithmod.items.recipes;

import com.dani.blacksmithmod.items.itemabstract.Recipe;
import com.dani.blacksmithmod.setup.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SaddleRecipe extends Recipe {


    public SaddleRecipe() {
        super( new Item[]{
                Items.LEATHER,Items.LEATHER,Items.LEATHER,
                Items.IRON_INGOT,Items.AIR,Items.IRON_INGOT,
                Items.AIR,Items.AIR,Items.AIR
        });
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        inv.decrStackSize(0,1);
        inv.decrStackSize(1,1);
        inv.decrStackSize(2,1);
        inv.decrStackSize(3,1);
        inv.decrStackSize(5,1);
        return new ItemStack(Items.SADDLE);
    }

}

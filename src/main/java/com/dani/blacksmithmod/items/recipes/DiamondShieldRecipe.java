package com.dani.blacksmithmod.items.recipes;

import com.dani.blacksmithmod.items.itemabstract.Recipe;
import com.dani.blacksmithmod.setup.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class DiamondShieldRecipe extends Recipe {


    public DiamondShieldRecipe() {
        super( new Item[]{
                Items.AIR,Items.DIAMOND,Items.AIR,
                Items.DIAMOND,Items.AIR,Items.DIAMOND,
                Items.AIR,Items.DIAMOND,Items.AIR
        });
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        inv.decrStackSize(1,1);
        inv.decrStackSize(3,1);
        inv.decrStackSize(5,1);
        inv.decrStackSize(7,1);
        return new ItemStack(ItemRegister.GOLD_SHIELD);
    }

}

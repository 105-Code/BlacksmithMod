package com.dani.blacksmithmod.items.recipes;

import com.dani.blacksmithmod.items.itemabstract.Recipe;
import com.dani.blacksmithmod.setup.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.ItemStackHandler;

public class GoldShieldRecipe extends Recipe {


    public GoldShieldRecipe() {
        super( new Item[]{
                Items.AIR,Items.GOLD_INGOT,Items.AIR,
                Items.GOLD_INGOT,Items.AIR,Items.GOLD_INGOT,
                Items.AIR,Items.GOLD_INGOT,Items.AIR
        });
    }

    @Override
    public ItemStack getCraftingResult(ItemStackHandler inv) {
        inv.extractItem(1,1,false);
        inv.extractItem(3,1,false);
        inv.extractItem(5,1,false);
        inv.extractItem(7,1,false);
        return new ItemStack(ItemRegister.GOLD_SHIELD,1);
    }

}

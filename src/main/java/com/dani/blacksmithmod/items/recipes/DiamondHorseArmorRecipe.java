package com.dani.blacksmithmod.items.recipes;

import com.dani.blacksmithmod.items.itemabstract.Recipe;
import com.dani.blacksmithmod.setup.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.ItemStackHandler;

public class DiamondHorseArmorRecipe extends Recipe {


    public DiamondHorseArmorRecipe() {
        super( new Item[]{
                Items.DIAMOND,Items.AIR,Items.DIAMOND,
                Items.DIAMOND,Items.DIAMOND,Items.DIAMOND,
                Items.DIAMOND,Items.AIR,Items.DIAMOND
        });
    }

    @Override
    public ItemStack getCraftingResult(ItemStackHandler inv) {
        inv.extractItem(0,1,false);
        inv.extractItem(2,1,false);
        inv.extractItem(3,1,false);
        inv.extractItem(4,1,false);
        inv.extractItem(5,1,false);
        inv.extractItem(6,1,false);
        inv.extractItem(8,1,false);
        return new ItemStack(Items.DIAMOND_HORSE_ARMOR,1);
    }

}

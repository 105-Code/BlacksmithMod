package com.dani.blacksmithmod.items.recipes;

import com.dani.blacksmithmod.items.itemabstract.Recipe;
import com.dani.blacksmithmod.setup.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.ItemStackHandler;

public class IronHorseArmorRecipe extends Recipe {


    public IronHorseArmorRecipe() {
        super( new Item[]{
                Items.IRON_INGOT,Items.AIR,Items.IRON_INGOT,
                Items.IRON_INGOT,Items.IRON_INGOT,Items.IRON_INGOT,
                Items.IRON_INGOT,Items.AIR,Items.IRON_INGOT
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
        return new ItemStack(Items.IRON_HORSE_ARMOR,1);
    }

}

package com.dani.blacksmithmod.items.recipes;

import com.dani.blacksmithmod.items.itemabstract.Recipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class IronHorseArmorRecipe extends Recipe {


    public IronHorseArmorRecipe() {
        super( new Item[]{
                Items.IRON_INGOT,Items.AIR,Items.IRON_INGOT,
                Items.IRON_INGOT,Items.IRON_INGOT,Items.IRON_INGOT,
                Items.IRON_INGOT,Items.AIR,Items.IRON_INGOT
        });
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        inv.decrStackSize(0,1);
        inv.decrStackSize(2,1);
        inv.decrStackSize(3,1);
        inv.decrStackSize(4,1);
        inv.decrStackSize(5,1);
        inv.decrStackSize(6,1);
        inv.decrStackSize(8,1);
        return new ItemStack(Items.IRON_HORSE_ARMOR);
    }

}

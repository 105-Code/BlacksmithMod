package com.dani.blacksmithmod.items.recipes;

import com.dani.blacksmithmod.items.itemabstract.Recipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class GoldHorseArmorRecipe extends Recipe {


    public GoldHorseArmorRecipe() {
        super( new Item[]{
                Items.GOLD_INGOT,Items.AIR,Items.GOLD_INGOT,
                Items.GOLD_INGOT,Items.GOLD_INGOT,Items.GOLD_INGOT,
                Items.GOLD_INGOT,Items.AIR,Items.GOLD_INGOT
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
        return new ItemStack(Items.GOLDEN_HORSE_ARMOR);
    }

}

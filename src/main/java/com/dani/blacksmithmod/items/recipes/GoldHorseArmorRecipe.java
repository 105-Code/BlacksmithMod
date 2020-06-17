package com.dani.blacksmithmod.items.recipes;

import com.dani.blacksmithmod.items.itemabstract.Recipe;
import com.dani.blacksmithmod.setup.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.ItemStackHandler;

public class GoldHorseArmorRecipe extends Recipe {


    public GoldHorseArmorRecipe() {
        super( new Item[]{
                Items.GOLD_INGOT,Items.AIR,Items.GOLD_INGOT,
                Items.GOLD_INGOT,Items.GOLD_INGOT,Items.GOLD_INGOT,
                Items.GOLD_INGOT,Items.AIR,Items.GOLD_INGOT
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
        return new ItemStack(Items.GOLDEN_HORSE_ARMOR,1);
    }


}

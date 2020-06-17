package com.dani.blacksmithmod.items.recipes;

import com.dani.blacksmithmod.items.itemabstract.Recipe;
import com.dani.blacksmithmod.setup.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class IronShieldRecipe extends Recipe {


    public IronShieldRecipe() {
        super( new Item[]{
                Items.AIR,Items.IRON_INGOT,Items.AIR,
                Items.IRON_INGOT,Items.AIR,Items.IRON_INGOT,
                Items.AIR,Items.IRON_INGOT,Items.AIR
        });
    }
    @Override
    public ItemStack getCraftingResult(ItemStackHandler inv) {
        inv.extractItem(1,1,false);
        inv.extractItem(3,1,false);
        inv.extractItem(5,1,false);
        inv.extractItem(7,1,false);
        return new ItemStack(ItemRegister.IRON_SHIELD,1);
    }
}

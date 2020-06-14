package com.dani.blacksmithmod.items.recipes;

import com.dani.blacksmithmod.setup.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class IronShieldRecipe implements IRecipe {

    private Item[] recipe = new Item[]{
            Items.AIR,Items.IRON_INGOT,Items.AIR,
            Items.IRON_INGOT,Items.AIR,Items.IRON_INGOT,
            Items.AIR,Items.IRON_INGOT,Items.AIR
    };

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        for(int i = 0;i < inv.getSizeInventory();i++){
            if(inv.getStackInSlot(i).getItem() != recipe[i])
                return false;
        }
        return true;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        inv.decrStackSize(1,1);
        inv.decrStackSize(3,1);
        inv.decrStackSize(5,1);
        inv.decrStackSize(7,1);
        return new ItemStack(ItemRegister.IRON_SHIELD);
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return null;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public IRecipeType<?> getType() {
        return null;
    }
}

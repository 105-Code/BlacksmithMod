package com.dani.blacksmithmod.items.itemabstract;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class Recipe implements IRecipe {
    protected final Item[] recipe;

    public Recipe(Item[] recipe){
        this.recipe = recipe;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        for(int i = 0;i < inv.getSizeInventory();i++)
            if(inv.getStackInSlot(i).getItem() != recipe[i])
                return false;
        return true;
    }

    @Override
    public abstract ItemStack getCraftingResult(IInventory inv);

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

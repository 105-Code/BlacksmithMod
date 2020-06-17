package com.dani.blacksmithmod.items.itemabstract;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public abstract class Recipe{
    protected final Item[] recipe;

    public Recipe(Item[] recipe){
        this.recipe = recipe;
    }


    public boolean matches(ItemStackHandler handler, World worldIn) {
        System.out.println("hello matches!!");
        for(int i = 0;i < handler.getSlots();i++)
            if(handler.getStackInSlot(i).getItem() != recipe[i])
                return false;
        return true;
    }

    public ItemStack decrStackSize(ItemStack stack,int count){
        if(stack.getCount() < count)
            return stack;
        stack.setCount(stack.getCount()-count);
        return stack;
    }

    public abstract ItemStack getCraftingResult(ItemStackHandler inv);
}

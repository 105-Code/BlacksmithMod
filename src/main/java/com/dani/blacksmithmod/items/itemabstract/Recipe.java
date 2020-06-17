package com.dani.blacksmithmod.items.itemabstract;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public abstract class Recipe{

    protected final Item[] recipe; //recipe pattern

    public Recipe(Item[] recipe){
        this.recipe = recipe;
    }

    /**
     * compare recipe pattern with ItemStackHandler incoming
     * @param handler items to compare
     * @param worldIn world where the action it happening.
     * @return true if match whit the pattern o False if not.
     */
    public boolean matches(ItemStackHandler handler, World worldIn) {
        System.out.println("hello matches!!");
        for(int i = 0;i < handler.getSlots();i++)
            if(handler.getStackInSlot(i).getItem() != recipe[i])
                return false;
        return true;
    }

    /**
     * return itemStack whit the crafting result.
     * @param inv materials used to create this recipe
     * @return item stack without materials.
     */
    public abstract ItemStack getCraftingResult(ItemStackHandler inv);

}

package com.dani.blacksmithmod.objects;

import net.minecraft.block.Blocks;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class IngredientSlot extends SlotItemHandler {

    public IngredientSlot(final IItemHandler handler, final int index, final int xPos, final int yPos) {
        super(handler, index, xPos, yPos);
    }

}

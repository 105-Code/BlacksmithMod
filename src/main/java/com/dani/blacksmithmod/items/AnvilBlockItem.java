package com.dani.blacksmithmod.items;

import com.dani.blacksmithmod.BlacksmithMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class AnvilBlockItem extends BlockItem {
    public AnvilBlockItem(Block blockIn) {
        super(blockIn, new Item.Properties().group(BlacksmithMod.TAB));
        this.setRegistryName(BlacksmithMod.MODID,"anvil");
    }
}

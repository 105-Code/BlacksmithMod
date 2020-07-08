package com.dani.blacksmithmod.items;

import com.dani.blacksmithmod.BlacksmithMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class RunicStoneBlockItem extends BlockItem {

    public RunicStoneBlockItem(Block blockIn) {
        super(blockIn, new Properties().group(BlacksmithMod.TAB));
        this.setRegistryName(BlacksmithMod.MODID,"runic_stone");
    }

}

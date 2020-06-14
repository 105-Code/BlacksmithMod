package com.dani.blacksmithmod.items;

import com.dani.blacksmithmod.BlacksmithMod;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.common.ToolType;

public class Hammer extends PickaxeItem {

    public Hammer() {
        super(ItemTier.IRON,2,0.5f,new Item.Properties()
                .group(BlacksmithMod.TAB));
        this.setRegistryName(BlacksmithMod.MODID,"hammer");
    }
}

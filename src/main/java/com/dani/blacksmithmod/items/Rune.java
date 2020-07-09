package com.dani.blacksmithmod.items;

import com.dani.blacksmithmod.BlacksmithMod;
import net.minecraft.item.Item;

public class Rune extends Item {



    public Rune(String name) {
        super(new Item.Properties().group(BlacksmithMod.TAB));
        this.setRegistryName(BlacksmithMod.MODID,name);
    }



}

package com.dani.blacksmithmod.items;

import com.dani.blacksmithmod.BlacksmithMod;
import net.minecraft.item.ShieldItem;

public class ShieldBase extends ShieldItem {

    public ShieldBase(String name,int durability) {
        super(new Properties().group(BlacksmithMod.TAB).maxDamage(durability));
        this.setRegistryName(BlacksmithMod.MODID,name);
    }

}

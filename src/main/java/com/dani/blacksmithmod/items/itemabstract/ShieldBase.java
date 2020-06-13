package com.dani.blacksmithmod.items.itemabstract;

import com.dani.blacksmithmod.BlacksmithMod;
import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;

public class ShieldBase extends ShieldItem {

    protected int maxDamge;
    public ShieldBase(String name, int damage, Item.Properties prop) {
        super(prop);
        this.setRegistryName(BlacksmithMod.MODID,name);
        this.maxDamge = damage;
    }

}

package com.dani.blacksmithmod.items;

import com.dani.blacksmithmod.items.itemabstract.ShieldBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class ShieldBasic extends ShieldBase {

    protected IItemTier material;

    public ShieldBasic(String name, IItemTier tierMaterial, int damage, Item.Properties prop) {
        super(name,damage,prop.maxDamage(damage));
        this.material = tierMaterial;
    }
}

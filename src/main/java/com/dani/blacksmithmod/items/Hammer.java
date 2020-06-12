package com.dani.blacksmithmod.items;

import com.dani.blacksmithmod.BlacksmithMod;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;

public class Hammer extends Item {

    public Hammer() {
        super(new Item.Properties()
                .group(BlacksmithMod.TAB)
                .maxDamage(2));
    }
}

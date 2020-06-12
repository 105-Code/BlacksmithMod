package com.dani.blacksmithmod.items;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.items.itemabstract.Pattern;
import com.dani.blacksmithmod.tiles.anviltileentity.enums.TypePattern;
import net.minecraft.item.Item;

public class IronShieldPattern extends Pattern {

    public IronShieldPattern() {
        super(new Item.Properties()
                .group(BlacksmithMod.TAB)
                .maxDamage(1), TypePattern.IRON_SHIELD);
    }


}
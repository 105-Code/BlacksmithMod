package com.dani.blacksmithmod.items;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.items.itemabstract.Pattern;
import com.dani.blacksmithmod.tiles.anviltileentity.enums.TypePattern;

public class ShieldPattern extends Pattern {

    public ShieldPattern() {
        super(new Properties()
                .group(BlacksmithMod.TAB)
                .maxDamage(1), TypePattern.SHIELD);
    }


}
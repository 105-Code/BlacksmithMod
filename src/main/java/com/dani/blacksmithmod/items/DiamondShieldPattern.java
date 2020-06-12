package com.dani.blacksmithmod.items;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.items.itemabstract.Pattern;
import com.dani.blacksmithmod.tiles.anviltileentity.enums.TypePattern;

public class DiamondShieldPattern extends Pattern {

    public DiamondShieldPattern() {
        super(new Properties()
                .group(BlacksmithMod.TAB)
                .maxDamage(1), TypePattern.DIAMOND_SHIELD);
    }


}
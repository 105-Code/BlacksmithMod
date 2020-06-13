package com.dani.blacksmithmod.items;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.items.itemabstract.Pattern;
import com.dani.blacksmithmod.tiles.anviltileentity.enums.TypePattern;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;

public class DiamondShieldPattern extends Pattern {



    public DiamondShieldPattern() {
        super(new Properties()
                .group(BlacksmithMod.TAB)
                .maxDamage(1), TypePattern.DIAMOND_SHIELD);
        this.setRegistryName(BlacksmithMod.MODID,"diamond_shield_pattern");
        this.patternMaterials = new ItemStack[]{new ItemStack(Items.DIAMOND, 4)};
    }


}
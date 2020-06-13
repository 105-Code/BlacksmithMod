package com.dani.blacksmithmod.items;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.items.itemabstract.Pattern;
import com.dani.blacksmithmod.tiles.anviltileentity.enums.TypePattern;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class GoldShieldPattern extends Pattern {

    public GoldShieldPattern() {
        super(new Properties()
                .group(BlacksmithMod.TAB)
                .maxDamage(1), TypePattern.GOLD_SHIELD);
        this.setRegistryName(BlacksmithMod.MODID,"gold_shield_pattern");
        this.patternMaterials = new ItemStack[]{new ItemStack(Items.GOLD_INGOT, 4)};
    }


}
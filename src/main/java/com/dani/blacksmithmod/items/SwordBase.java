package com.dani.blacksmithmod.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class SwordBase extends SwordItem {

    private Rune slot1;
    private Rune slot2;

    public SwordBase(String name,IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
        this.setRegistryName("minecraft",name);//Override vanilla object
    }

    public boolean addRune(Rune rune){
        if(this.slot1 != null){
            if(this.slot2 != null){
                return false;
            }
            this.slot2 = rune;
            return true;
        }
        this.slot1 = rune;
        return true;
    }

}

package com.dani.blacksmithmod.items.itemabstract;

import com.dani.blacksmithmod.tiles.anviltileentity.enums.TypePattern;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public abstract class Pattern extends Item {
    protected final TypePattern typePattern;

    protected ItemStack[] patternMaterials;

    public Pattern(Properties properties,TypePattern typePattern) {
        super(properties);
        this.typePattern=typePattern;

    }

    public ItemStack[] getPatternMaterials(){
        return this.patternMaterials;
    }

    public  TypePattern getTypePattern(){
        return this.typePattern;
    }
}

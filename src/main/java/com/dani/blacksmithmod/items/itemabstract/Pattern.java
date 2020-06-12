package com.dani.blacksmithmod.items.itemabstract;

import com.dani.blacksmithmod.tiles.anviltileentity.enums.TypePattern;
import net.minecraft.item.Item;

public abstract class Pattern extends Item {
    protected final TypePattern typePattern;

    public Pattern(Properties properties,TypePattern typePattern) {
        super(properties);
        this.typePattern=typePattern;
    }


    public  TypePattern getTypePattern(){
        return this.typePattern;
    }
}

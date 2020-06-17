package com.dani.blacksmithmod.util;

import com.dani.blacksmithmod.tiles.AnvilTileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NBTHelper {

    public static CompoundNBT ToNBT(Object object){
        if( object instanceof ItemStack){
            return writeItemStack((ItemStack) object);
        }

        if( object instanceof AnvilTileEntity){
            return writeAnvil((AnvilTileEntity) object);
        }

        return null;
    }

    private static CompoundNBT writeAnvil( AnvilTileEntity tileEntity){
        CompoundNBT compound = new CompoundNBT();
        compound.putInt("hits",tileEntity.getHits());
        return compound;
    }

    private static CompoundNBT writeItemStack(ItemStack stack){
        CompoundNBT compound = new CompoundNBT();
        compound.putInt("count",stack.getCount());
        compound.putString("item", stack.getItem().getRegistryName().toString());
        compound.putByte("type",(byte) 0);
        return compound;
    }


    @Nullable
    public static Object fromNBT(@Nonnull CompoundNBT compound) {
        switch (compound.getByte("type")) {
            case 0:
                return readItemStack(compound);
            default:
                return null;
        }
    }

    private static ItemStack readItemStack(CompoundNBT compound) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(compound.getString("item")));
        int count = compound.getInt("count");
        return new ItemStack(item, count);
    }
}

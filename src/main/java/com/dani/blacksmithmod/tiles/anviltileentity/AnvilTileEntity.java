package com.dani.blacksmithmod.tiles.anviltileentity;


import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.containers.anvilcontainer.AnvilContainer;
import com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler.MaterialStackHandler;
import com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler.OutputStackHandler;
import com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler.PatternStackHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

import static com.dani.blacksmithmod.setup.RegistryHandler.ANVIL_TILE_ENTITY;


public class AnvilTileEntity extends TileEntity  implements  INamedContainerProvider {

    public final PatternStackHandler pattern;
    public final MaterialStackHandler ingredients;
    public final OutputStackHandler output;
    private short hit;


    public AnvilTileEntity() {
        super(ANVIL_TILE_ENTITY.get());
        this.pattern = new PatternStackHandler();
        this.ingredients = new MaterialStackHandler(this.pattern,6);
        this.output = new OutputStackHandler();
        this.hit=0;

    }


    public boolean addHit() {
        if (this.hit < 4){
            this.hit++;
            return true;
        }
        this.hit = 0;
        if(this.ingredients.isEqual(this.pattern.getMaterialPattern())){
            this.output.outputItem(this.pattern.getTypePattern());
            this.ingredients.clear();
            return true;
        }

        return false;
    }


    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new AnvilContainer(i, world, pos, playerInventory, playerEntity);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return super.write(compound);

    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
    }



}

package com.dani.blacksmithmod.tiles.anviltileentity;


import com.dani.blacksmithmod.containers.anvilcontainer.AnvilContainer;
import com.dani.blacksmithmod.items.recipes.IronShieldRecipe;
import com.dani.blacksmithmod.setup.TileEntityRegister;
import com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler.MaterialStackHandler;
import com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler.OutputStackHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;



public class AnvilTileEntity extends TileEntity  implements  INamedContainerProvider {

    public MaterialStackHandler materials;
    private final IRecipe<CraftingInventory>[] recipes = new IRecipe[]{new IronShieldRecipe()};
    public final OutputStackHandler output;
    private short hit;

    private Container container;


    public AnvilTileEntity() {
        super(TileEntityRegister.ANVIL_TILE_ENTITY);
        this.materials= new MaterialStackHandler(9);
        this.output = new OutputStackHandler();
        this.hit=0;
    }


    public boolean addHit() {
        if (this.hit < 4){
            this.hit++;
            return true;
        }
        this.hit = 0;
        for(IRecipe recipe : this.recipes){
            if (recipe.matches(this.materials,world)){
                return this.output.outputItem(recipe.getCraftingResult(this.materials));
            }
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
        return this.container = new AnvilContainer(i, world, pos, playerInventory, playerEntity);

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

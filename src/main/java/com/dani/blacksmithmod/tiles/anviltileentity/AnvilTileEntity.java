package com.dani.blacksmithmod.tiles.anviltileentity;


import com.dani.blacksmithmod.containers.anvilcontainer.AnvilContainer;
import com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler.OutputStackHandler;
import com.dani.blacksmithmod.tiles.anviltileentity.itemstackhandler.PatternStackHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

import static com.dani.blacksmithmod.setup.RegistryHandler.ANVIL_TILE_ENTITY;


public class AnvilTileEntity extends TileEntity  implements  INamedContainerProvider  {

    public final PatternStackHandler pattern;
    public final ItemStackHandler ingredients;
    public final OutputStackHandler output;


    public AnvilTileEntity() {
        super(ANVIL_TILE_ENTITY.get());
        this.pattern = new PatternStackHandler();
        this.ingredients = new ItemStackHandler(6);
        this.output = new OutputStackHandler();
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


}

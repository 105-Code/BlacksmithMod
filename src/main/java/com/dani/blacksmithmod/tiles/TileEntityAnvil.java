package com.dani.blacksmithmod.tiles;

import com.dani.blacksmithmod.containers.AnvilContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

import static com.dani.blacksmithmod.setup.RegistryHandler.ANVIL_TILE_ENTITY;


public class TileEntityAnvil extends TileEntity  implements ITickableTileEntity, INamedContainerProvider  {

    public TileEntityAnvil() {
        super(ANVIL_TILE_ENTITY.get());
    }

    @Override
    public void tick() {
        if (world.isRemote())
            System.out.println("AnvilTileEntity Test!!");
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

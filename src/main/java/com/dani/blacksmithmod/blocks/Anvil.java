package com.dani.blacksmithmod.blocks;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.items.Hammer;
import com.dani.blacksmithmod.tiles.anviltileentity.AnvilTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class Anvil extends Block {

    public Anvil() {
        super(Block.Properties.create(Material.ANVIL)
                .hardnessAndResistance(4.8f,5.0f)
                .sound(SoundType.ANVIL)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE));
        this.setRegistryName(BlacksmithMod.MODID,"anvil");

    }

    /**
     * Verifica si este blqoue tiene un TileEntity
     * @param state Estado del bloque
     * @return Retorna TRUE, porque posee un tileEntity
     */
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    /**
     * Este metodo crea el tileEntity al bloque
     * @param state Estado del bloque
     * @param world Mundo donde esta el bloque
     * @return retorna un AnvilTileEntity
     */
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AnvilTileEntity();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit){
        if(worldIn.isRemote)
            return ActionResultType.PASS;

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof INamedContainerProvider) {
            NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
        }
        return ActionResultType.SUCCESS;
    }

    // Cuando es True isremote() es el Cliente y si es False es El servidor
    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if(!worldIn.isRemote()){
            if(player.inventory.getCurrentItem().getItem() instanceof Hammer){

                TileEntity tileEntity = worldIn.getTileEntity(pos);
                if(tileEntity instanceof AnvilTileEntity){
                    ((AnvilTileEntity) tileEntity).addHit();
                }
            }
        }

        if(player.inventory.getCurrentItem().getItem() instanceof Hammer && worldIn.isRemote()){
            worldIn.playSound(player.getPosX(),player.getPosY(),player.getPosZ(),SoundType.ANVIL.getPlaceSound(), SoundCategory.BLOCKS,1.0f,1.0f,true);
        }


    }
}

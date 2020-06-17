package com.dani.blacksmithmod.blocks;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.items.Hammer;
import com.dani.blacksmithmod.setup.TileEntityRegister;
import com.dani.blacksmithmod.tiles.AnvilTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;


/**
 * Anvil block, this blocks is used to craft items from blacksmith mod and minecraft vanilla
 */
public class Anvil extends Block {

    //VoxelShape for the anvil block.
    private static final VoxelShape PART_BASE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 4.0D, 15.0D);
    private static final VoxelShape PART_LOWER_X = Block.makeCuboidShape(3.0D, 4.0D, 3.0D, 13.0D, 5.0D, 13.0D);
    private static final VoxelShape PART_MID_X = Block.makeCuboidShape(4.0D, 5.0D, 4.0D, 12.0D, 10.0D, 12.0D);
    private static final VoxelShape PART_UPPER_X = Block.makeCuboidShape(1.0D, 10.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    private static final VoxelShape X_AXIS_AABB = VoxelShapes.or(PART_BASE, PART_LOWER_X, PART_MID_X, PART_UPPER_X);


    public Anvil() {
        super(Block.Properties.create(Material.ANVIL)
                .hardnessAndResistance(4.8f,5.0f)
                .sound(SoundType.ANVIL)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE));
        this.setRegistryName(BlacksmithMod.MODID,"anvil");
    }

    /**
     * Verify if this block has TileEntity
     * @param state Block state
     * @return True, it has TileEntity
     */
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    /**
     * This method make a TileEntity to Anvil Block
     * @param state Block state
     * @param world World where it's.
     * @return new AnvilTileEntity object.
     */
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityRegister.ANVIL_TILE_ENTITY.create();
    }

    /**
     * Create a custom VoxelShape
     * @param state Block state
     * @param worldIn World Render
     * @param pos Block position
     * @param context Context
     * @return Custom VoxelShape
     */
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return X_AXIS_AABB;
    }

    /**
     * When the player use right click in it
     * @param state Block State
     * @param worldIn World where player and block stay
     * @param pos Block position
     * @param player Player who interact
     * @param handIn what had use
     * @param hit where the player hit the block
     * @return interaction result
     */
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit){
        if(!worldIn.isRemote()){ //when is True is Client side, and if is false es serve side
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if( tileEntity instanceof INamedContainerProvider)
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    /**
     * When the player use left click in it
     * @param state Block State
     * @param worldIn World where player and block stay
     * @param pos lock position
     * @param player  Player who interact
     */
    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (player.inventory.getCurrentItem().getItem() instanceof Hammer){
            if(!worldIn.isRemote()){ //server Side
                player.inventory.getCurrentItem().getItem().setDamage(player.inventory.getCurrentItem(),1);
                TileEntity tileEntity = worldIn.getTileEntity(pos);
                if(tileEntity instanceof AnvilTileEntity)
                    ((AnvilTileEntity) tileEntity).addHit();

            }else  //client side
                worldIn.playSound(player.getPosX(),player.getPosY(),player.getPosZ(),SoundType.ANVIL.getPlaceSound(), SoundCategory.BLOCKS,1.0f,1.0f,true);
        }
    }

}

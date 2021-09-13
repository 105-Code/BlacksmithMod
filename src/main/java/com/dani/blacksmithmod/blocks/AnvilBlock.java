package com.dani.blacksmithmod.blocks;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.items.Hammer;
import com.dani.blacksmithmod.common.TileEntityRegister;
import com.dani.blacksmithmod.tiles.AnvilTileEntity;
import com.dani.blacksmithmod.tiles.ForgeTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;


/**
 * AnvilBlock block, this blocks is used to craft items from blacksmith mod and minecraft vanilla
 */
public class AnvilBlock extends ContainerBlock {

    //VoxelShape for the anvil block.
    private static final VoxelShape PART_BASE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 4.0D, 15.0D);
    private static final VoxelShape PART_LOWER_X = Block.makeCuboidShape(3.0D, 4.0D, 3.0D, 13.0D, 5.0D, 13.0D);
    private static final VoxelShape PART_MID_X = Block.makeCuboidShape(4.0D, 5.0D, 4.0D, 12.0D, 10.0D, 12.0D);
    private static final VoxelShape PART_UPPER_X = Block.makeCuboidShape(1.0D, 10.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    private static final VoxelShape X_AXIS_AABB = VoxelShapes.or(PART_BASE, PART_LOWER_X, PART_MID_X, PART_UPPER_X);


    public AnvilBlock() {
        super(Block.Properties.create(Material.ANVIL)
                .hardnessAndResistance(4.8f,5.0f)
                .sound(SoundType.ANVIL)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE));
        this.setRegistryName(BlacksmithMod.MODID,"anvil");
    }

    public ForgeTileEntity getForgeTileEntity(World world, BlockPos position){
        for (int x = position.getX()-1; x <= position.getX()+1; x++){
            for (int z = position.getZ()-1; z<= position.getZ()+1; z++){
                BlockPos searchPos = new BlockPos(x,position.getY(),z);
                TileEntity tileentity = world.getTileEntity(searchPos);
                if (tileentity instanceof ForgeTileEntity) {
                    return (ForgeTileEntity) tileentity;
                }

            }
        }
       return null;
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
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof AnvilTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (AnvilTileEntity) tileentity, pos);
            }
            return ActionResultType.SUCCESS;
        }
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
                    ((AnvilTileEntity) tileEntity).addHit(worldIn);

            }else  //client side
                worldIn.playSound(player.getPosX(),player.getPosY(),player.getPosZ(),SoundType.ANVIL.getPlaceSound(), SoundCategory.BLOCKS,1.0f,1.0f,true);
        }
    }

    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new AnvilTileEntity();
    }

    @Override
    public BlockRenderType getRenderType(BlockState iBlockState) {
        return BlockRenderType.MODEL;
    }


}

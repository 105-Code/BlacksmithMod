package com.dani.blacksmithmod.blocks;

import com.dani.blacksmithmod.tiles.TileEntityAnvil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class Anvil extends Block {

    private static final Logger LOGGER = LogManager.getLogger();

    public Anvil() {
        super(Block.Properties.create(Material.ANVIL)
                .hardnessAndResistance(5.8f,6.0f)
                .sound(SoundType.ANVIL)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE));
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
     * @return retorna un TileEntityAnvil
     */
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityAnvil();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit){
        LOGGER.info("Click");
        if(worldIn.isRemote)
            return ActionResultType.PASS;

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof INamedContainerProvider) {
            NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
        }
        return ActionResultType.SUCCESS;
    }

}

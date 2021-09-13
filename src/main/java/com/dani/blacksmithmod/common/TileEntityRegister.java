package com.dani.blacksmithmod.common;

import com.dani.blacksmithmod.tiles.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TileEntityRegister {

    public static final TileEntityType<?> ANVIL_TILE_ENTITY = TileEntityType.Builder.create(AnvilTileEntity::new, BlockRegister.ANVIL).build(null);
    public static final TileEntityType<?> FORGE_TILE_ENTITY = TileEntityType.Builder.create(ForgeTileEntity::new, BlockRegister.NETHER_FORGE,BlockRegister.STONE_FORGE,BlockRegister.BRICK_FORGE).build(null);



    /**
     * Add all blacksmith's items in game.
     * @param ev event where the items will be registered
     */
    @SubscribeEvent
    public static void onRegister(final RegistryEvent.Register<TileEntityType<?>> ev) {
        ANVIL_TILE_ENTITY.setRegistryName("anvil_tile_entity");
        FORGE_TILE_ENTITY.setRegistryName("forge_tile_entity");
        
        ev.getRegistry().register(ANVIL_TILE_ENTITY);
        ev.getRegistry().register(FORGE_TILE_ENTITY);
    }

}

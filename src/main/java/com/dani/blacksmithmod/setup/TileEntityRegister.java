package com.dani.blacksmithmod.setup;

import com.dani.blacksmithmod.tiles.AnvilTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityRegister {

    public static final TileEntityType<?> ANVIL_TILE_ENTITY = TileEntityType.Builder.create(AnvilTileEntity::new, BlockRegister.ANVIL).build(null).setRegistryName("anvil");

    /**
     * Add all blacksmith's items in game.
     * @param ev event where the items will be registered
     */
    @SubscribeEvent
    public static void onRegister(final RegistryEvent.Register<TileEntityType<?>> ev) {
        final IForgeRegistry<TileEntityType<?>> reg = ev.getRegistry();

        reg.register(ANVIL_TILE_ENTITY);
    }

}

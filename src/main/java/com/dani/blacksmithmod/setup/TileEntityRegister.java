package com.dani.blacksmithmod.setup;

import com.dani.blacksmithmod.tiles.anviltileentity.AnvilTileEntity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityRegister {

    public static final TileEntityType ANVIL_TILE_ENTITY = TileEntityType.Builder.create(AnvilTileEntity::new, BlockRegister.ANVIL).build(null);


    @SubscribeEvent
    public static void onRegister(final RegistryEvent.Register<TileEntityType<?>> ev) {
        final IForgeRegistry<TileEntityType<?>> reg = ev.getRegistry();
        ANVIL_TILE_ENTITY.setRegistryName("anvil");
        reg.register(ANVIL_TILE_ENTITY);


    }
}

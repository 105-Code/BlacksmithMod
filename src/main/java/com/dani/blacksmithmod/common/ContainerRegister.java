package com.dani.blacksmithmod.common;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.containers.AnvilContainer;
import com.dani.blacksmithmod.containers.ForgeContainer;
import com.dani.blacksmithmod.tiles.AnvilTileEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class ContainerRegister {


    public static final ContainerType<AnvilContainer> ANVIL_CONTAINER = IForgeContainerType.create(AnvilContainer::new);
    public static final ContainerType<ForgeContainer> FORGE_CONTAINER = IForgeContainerType.create(ForgeContainer::new);



    /**
     * Add all blacksmith's container in game.
     * @param ev event where the containers will be registered
     */
    @SubscribeEvent
    public static void onRegister(final RegistryEvent.Register<ContainerType<?>> ev) {
        ANVIL_CONTAINER.setRegistryName("anvil_container");
        FORGE_CONTAINER.setRegistryName("forge_container");

        ev.getRegistry().register(ANVIL_CONTAINER);
        ev.getRegistry().register(FORGE_CONTAINER);
    }

}

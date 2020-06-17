package com.dani.blacksmithmod.setup;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.containers.AnvilContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerRegister {

    public static final ContainerType ANVIL_CONTAINER =  IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new AnvilContainer(windowId, BlacksmithMod.proxy.getClientWorld(), pos, inv, BlacksmithMod.proxy.getClientPlayer());
    }).setRegistryName("anvil");

    /**
     * Add all blacksmith's container in game.
     * @param ev event where the containers will be registered
     */
    @SubscribeEvent
    public static void onRegister(final RegistryEvent.Register<ContainerType<?>> ev) {
        final IForgeRegistry<ContainerType<?>> reg = ev.getRegistry();

        reg.register(ANVIL_CONTAINER);
    }

}

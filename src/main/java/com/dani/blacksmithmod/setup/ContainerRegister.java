package com.dani.blacksmithmod.setup;


import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.containers.anvilcontainer.AnvilContainer;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerRegister {

    public static final ContainerType ANVIL_CONTAINER =  IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new AnvilContainer(windowId, BlacksmithMod.proxy.getClientWorld(), pos, inv, BlacksmithMod.proxy.getClientPlayer());
    });



    @SubscribeEvent
    public static void onRegister(final RegistryEvent.Register<ContainerType<?>> ev) {
        final IForgeRegistry<ContainerType<?>> reg = ev.getRegistry();
        ANVIL_CONTAINER.setRegistryName("anvil");
        reg.register(ANVIL_CONTAINER);


    }
}

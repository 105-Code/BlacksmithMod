package com.dani.blacksmithmod.setup;

import com.dani.blacksmithmod.blocks.Anvil;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegister {

    public static final Block ANVIL = new Anvil();

    /**
     * Add all blacksmith's block in game.
     * @param ev event where the blocks will be registered
     */
    @SubscribeEvent
    public static void onRegister(final RegistryEvent.Register<Block> ev) {
        final IForgeRegistry<Block> reg = ev.getRegistry();
        reg.register(ANVIL);
    }

}

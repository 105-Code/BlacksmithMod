package com.dani.blacksmithmod.common;

import com.dani.blacksmithmod.blocks.AnvilBlock;
import com.dani.blacksmithmod.blocks.ForgeBlock;
import com.dani.blacksmithmod.blocks.RunicStoneBlock;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegister {

    public static final Block ANVIL = new AnvilBlock();
    public static final Block RUNIC_STONE = new RunicStoneBlock();
    public static final Block FORGE = new ForgeBlock();

    /**
     * Add all blacksmith's block in game.
     * @param ev event where the blocks will be registered
     */
    @SubscribeEvent
    public static void onRegister(final RegistryEvent.Register<Block> ev) {
        final IForgeRegistry<Block> reg = ev.getRegistry();
        reg.register(ANVIL);
        reg.register(RUNIC_STONE);
        reg.register(FORGE);
    }
}

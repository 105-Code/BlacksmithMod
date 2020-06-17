package com.dani.blacksmithmod.setup;

import com.dani.blacksmithmod.items.*;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegister {

    public static final Item HAMMER = new Hammer();

    //Block Items
    public static final BlockItem ANVIL_BLOCK_ITEM =  new AnvilBlockItem(BlockRegister.ANVIL);

    //Shield Items
    public static final Item GOLD_SHIELD = new ShieldBase("gold_shield", 500 );
    public static final Item IRON_SHIELD = new ShieldBase("iron_shield",400);
    public static final Item DIAMOND_SHIELD = new ShieldBase("diamond_shield",750);

    /**
     * Add all blacksmith's items in game.
     * @param ev event where the items will be registered
     */
    @SubscribeEvent
    public static void onRegister(final RegistryEvent.Register<Item> ev) {
        final IForgeRegistry<Item> reg = ev.getRegistry();
        reg.register(HAMMER);
        reg.register(ANVIL_BLOCK_ITEM);
        reg.registerAll(GOLD_SHIELD, IRON_SHIELD,DIAMOND_SHIELD);
    }

}

package com.dani.blacksmithmod.setup;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.items.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
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
    private static final Item.Properties SHIELD_PROPERTIES = new Item.Properties().group(BlacksmithMod.TAB);
    public static final ShieldBasic GOLD_SHIELD = new ShieldBasic("gold_shield", ItemTier.GOLD,192, SHIELD_PROPERTIES);
    public static final ShieldBasic IRON_SHIELD = new ShieldBasic("iron_shield", ItemTier.IRON, 538, SHIELD_PROPERTIES);
    public static final ShieldBasic DIAMOND_SHIELD = new ShieldBasic("diamond_shield", ItemTier.DIAMOND, 2560, SHIELD_PROPERTIES);




    @SubscribeEvent
    public static void onRegister(final RegistryEvent.Register<Item> ev) {
        final IForgeRegistry<Item> reg = ev.getRegistry();
        reg.register(HAMMER);
        reg.register(ANVIL_BLOCK_ITEM);
        reg.registerAll(GOLD_SHIELD, IRON_SHIELD,DIAMOND_SHIELD);

    }
}

package com.dani.blacksmithmod.setup;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.items.*;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegister {

    public static final Item HAMMER = new Hammer();

    //Block Items
    private static final Item.Properties builder = new Item.Properties().group(BlacksmithMod.TAB);
    public static final Item ANVIL_BLOCK_ITEM =  new BlockItem(BlockRegister.ANVIL,builder).setRegistryName("anvil");
    public static final Item RUNIC_STONE_BLOCK_ITEM =  new BlockItem(BlockRegister.RUNIC_STONE,builder).setRegistryName("runic_stone");

    //Shield Items
    public static final Item GOLD_SHIELD = new ShieldBase("gold_shield", 500 );
    public static final Item IRON_SHIELD = new ShieldBase("iron_shield",400);
    public static final Item DIAMOND_SHIELD = new ShieldBase("diamond_shield",750);

    //Runes
    public static final Rune FIRE_RUNE = new Rune("fire_rune");
    public static final Rune POISON_RUNE = new Rune("poison_rune");
    public static final Rune MUNDANE_RUNE = new Rune("mundane_rune");

    //Override vanilla items
    public static final Item DIAMOND_SWORD = new SwordBase("diamond_sword",ItemTier.DIAMOND, 3, -2.4F, (new Item.Properties()).group(ItemGroup.COMBAT));
    public static final Item GOLDEN_SWORD = new SwordBase("golden_sword",ItemTier.GOLD, 3, -2.4F, (new Item.Properties()).group(ItemGroup.COMBAT));
    public static final Item IRON_SWORD = new SwordBase("iron_sword",ItemTier.IRON, 3, -2.4F, (new Item.Properties()).group(BlacksmithMod.TAB));


    public static IForgeRegistry<Item> reg;
    /**
     * Add all blacksmith's items in game.
     * @param ev event where the items will be registered
     */
    @SubscribeEvent
    public static void onRegister(final RegistryEvent.Register<Item> ev) {
        reg = ev.getRegistry();
        reg.register(HAMMER);
        reg.register(ANVIL_BLOCK_ITEM);
        reg.register(RUNIC_STONE_BLOCK_ITEM);
        reg.registerAll(GOLD_SHIELD, IRON_SHIELD,DIAMOND_SHIELD);
        reg.registerAll(FIRE_RUNE,POISON_RUNE,MUNDANE_RUNE);

        //override items
        reg.registerAll(DIAMOND_SWORD,GOLDEN_SWORD,IRON_SWORD);

    }

    //get items
    public static Item getValues(ResourceLocation i){
        return reg.getValue(i);
    }

}

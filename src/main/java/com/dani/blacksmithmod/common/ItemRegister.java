package com.dani.blacksmithmod.common;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.items.*;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemRegister {

    public static final Item HAMMER = new Hammer();

    //Block Items
    private static final Item.Properties builder = new Item.Properties().group(BlacksmithMod.TAB);
    public static final Item ANVIL_BLOCK_ITEM =  new BlockItem(BlockRegister.ANVIL,builder).setRegistryName("anvil");
    public static final Item NETHER_FORGE_BLOCK_ITEM =  new BlockItem(BlockRegister.NETHER_FORGE,builder).setRegistryName("nether_forge");
    public static final Item BRICK_FORGE_BLOCK_ITEM =  new BlockItem(BlockRegister.BRICK_FORGE,builder).setRegistryName("brick_forge");
    public static final Item STONE_FORGE_BLOCK_ITEM =  new BlockItem(BlockRegister.STONE_FORGE,builder).setRegistryName("stone_forge");
    public static final Item RUNIC_STONE_BLOCK_ITEM =  new BlockItem(BlockRegister.RUNIC_STONE,builder).setRegistryName("runic_stone");

    public static final Item CHIMNEY =  new BlockItem(BlockRegister.CHIMNEY,builder).setRegistryName("chimney");

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
        reg.registerAll(NETHER_FORGE_BLOCK_ITEM,BRICK_FORGE_BLOCK_ITEM,STONE_FORGE_BLOCK_ITEM);
        reg.register(CHIMNEY);
        //override items
        reg.registerAll(DIAMOND_SWORD,GOLDEN_SWORD,IRON_SWORD);

    }

    //get items
    public static Item getValues(ResourceLocation i){
        return reg.getValue(i);
    }

}

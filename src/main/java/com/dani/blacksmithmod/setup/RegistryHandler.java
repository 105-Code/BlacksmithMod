package com.dani.blacksmithmod.setup;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.blocks.Anvil;
import com.dani.blacksmithmod.containers.anvilcontainer.AnvilContainer;
import com.dani.blacksmithmod.items.*;
import com.dani.blacksmithmod.tiles.anviltileentity.AnvilTileEntity;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    //Category
    public static final DeferredRegister<Item>  ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, BlacksmithMod.MODID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS,BlacksmithMod.MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, BlacksmithMod.MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, BlacksmithMod.MODID);

    public static void init(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //Items
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", Hammer::new);
    public static final RegistryObject<Item> IRON_SHIELD = ITEMS.register("iron_shield", IronShield::new);
    public static final RegistryObject<Item> SADDLE_PATTERN = ITEMS.register("saddle_pattern", SaddlePattern::new);

    //Shield Patterns
    public static final RegistryObject<Item> SHIELD_PATTERN = ITEMS.register("shield_pattern", ShieldPattern::new);
    public static final RegistryObject<Item> IORN_SHIELD_PATTERN = ITEMS.register("iron_shield_pattern", IronShieldPattern::new);
    public static final RegistryObject<Item> GOLD_SHIELD_PATTERN = ITEMS.register("gold_shield_pattern", GoldShieldPattern::new);
    public static final RegistryObject<Item> DIAMOND_SHIELD_PATTERN = ITEMS.register("diamond_shield_pattern", DiamondShieldPattern::new);


    //Blocks
    public static final RegistryObject<Block> ANVIL = BLOCKS.register("anvil", Anvil::new);

    //BlockItem
    public static final RegistryObject<BlockItem> ANVIL_BLOCK_ITEM = ITEMS.register("anvil", () -> new AnvilBlockItem(ANVIL.get()) );

    //TileEntity
    public static final RegistryObject<TileEntityType<AnvilTileEntity>> ANVIL_TILE_ENTITY = TILES.register("anvil", () -> TileEntityType.Builder.create(AnvilTileEntity::new, ANVIL.get()).build(null));

    //Containers
    public static final RegistryObject<ContainerType<AnvilContainer>> ANVIL_CONTAINER = CONTAINERS.register("anvil", () ->IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new AnvilContainer(windowId, BlacksmithMod.proxy.getClientWorld(), pos, inv, BlacksmithMod.proxy.getClientPlayer());
    }));


}

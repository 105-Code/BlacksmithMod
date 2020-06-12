package com.dani.blacksmithmod.setup;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.blocks.Anvil;
import com.dani.blacksmithmod.containers.AnvilContainer;
import com.dani.blacksmithmod.items.AnvilBlockItem;
import com.dani.blacksmithmod.items.Hammer;
import com.dani.blacksmithmod.items.IronShield;
import com.dani.blacksmithmod.tiles.TileEntityAnvil;
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

    //Blocks
    public static final RegistryObject<Block> ANVIL = BLOCKS.register("anvil", Anvil::new);

    //BlockItem
    public static final RegistryObject<BlockItem> ANVIL_BLOCK_ITEM = ITEMS.register("anvil", () -> new AnvilBlockItem(ANVIL.get()) );

    //TileEntity
    public static final RegistryObject<TileEntityType<TileEntityAnvil>> ANVIL_TILE_ENTITY = TILES.register("anvil", () -> TileEntityType.Builder.create(TileEntityAnvil::new, ANVIL.get()).build(null));

    //Containers
    public static final RegistryObject<ContainerType<AnvilContainer>> AVIL_CONTAINER = CONTAINERS.register("anvil", () ->IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new AnvilContainer(windowId, BlacksmithMod.proxy.getClientWorld(), pos, inv, BlacksmithMod.proxy.getClientPlayer());
    }));


}

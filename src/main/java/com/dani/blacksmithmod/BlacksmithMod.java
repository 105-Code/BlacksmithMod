package com.dani.blacksmithmod;

import com.dani.blacksmithmod.recipes.AnvilRecipe;
import com.dani.blacksmithmod.setup.ItemRegister;
import com.dani.blacksmithmod.setup.RecipeRegister;
import com.dani.blacksmithmod.util.ClientProxy;
import com.dani.blacksmithmod.util.ClientSetup;
import com.dani.blacksmithmod.util.IProxy;
import com.dani.blacksmithmod.util.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.items.ItemHandlerHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Mod("blacksmithmod")
public class BlacksmithMod {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "blacksmithmod";

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    /**
     * Create mod tap in creative mode.
     */
    public static final ItemGroup TAB = new ItemGroup(MODID){
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegister.HAMMER);
        }
    };

    public BlacksmithMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {}

    private void doClientStuff(final FMLClientSetupEvent event) {
        ClientSetup.init(event);
    }

}
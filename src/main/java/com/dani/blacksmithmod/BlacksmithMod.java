package com.dani.blacksmithmod;

import com.dani.blacksmithmod.setup.ItemRegister;
import com.dani.blacksmithmod.util.ClientProxy;
import com.dani.blacksmithmod.util.ClientSetup;
import com.dani.blacksmithmod.util.IProxy;
import com.dani.blacksmithmod.util.ServerProxy;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod("blacksmithmod")
public class BlacksmithMod {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "blacksmithmod";

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());


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

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ClientSetup.init(event);
    }


}

package com.dani.blacksmithmod;

import com.dani.blacksmithmod.client.ClientProxy;
import com.dani.blacksmithmod.client.ContainerScreenRegister;
import com.dani.blacksmithmod.client.IProxy;
import com.dani.blacksmithmod.common.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BlacksmithMod.MODID)
public class BlacksmithMod {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "blacksmithmod";
    public static IEventBus MOD_EVENT_BUS;

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
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        registerCommonEvents();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> BlacksmithMod::registerClientOnlyEvents);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void registerCommonEvents(){
        MOD_EVENT_BUS.register(BlockRegister.class);
        MOD_EVENT_BUS.register(ItemRegister.class);
        MOD_EVENT_BUS.register(RecipeRegister.class);
        MOD_EVENT_BUS.register(TileEntityRegister.class);
        MOD_EVENT_BUS.register(ContainerRegister.class);
    }

    public static void registerClientOnlyEvents(){
        MOD_EVENT_BUS.register(ContainerScreenRegister.class);
    }
}
package com.dani.blacksmithmod.util;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.containers.screens.AnvilScreen;
import com.dani.blacksmithmod.setup.RegistryHandler;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = BlacksmithMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(RegistryHandler.AVIL_CONTAINER.get(), AnvilScreen::new);
    }

}

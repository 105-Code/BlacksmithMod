package com.dani.blacksmithmod.client;

import com.dani.blacksmithmod.common.ContainerRegister;
import com.dani.blacksmithmod.screens.AnvilScreen;
import com.dani.blacksmithmod.screens.ForgeScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ContainerScreenRegister {

    // register the factory that is used on the client to generate a ContainerScreen corresponding to our Container
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ContainerRegister.ANVIL_CONTAINER, AnvilScreen::new);
        ScreenManager.registerFactory(ContainerRegister.FORGE_CONTAINER, ForgeScreen::new);
    }
}

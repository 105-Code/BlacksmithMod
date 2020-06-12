package com.dani.blacksmithmod.containers.anvilcontainer.screen;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.containers.anvilcontainer.AnvilContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AnvilScreen extends ContainerScreen<AnvilContainer> {

    private static final ResourceLocation GUI_BACKGROUND = new ResourceLocation(BlacksmithMod.MODID, "textures/gui/anvil_gui.png");;

    public AnvilScreen(AnvilContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI_BACKGROUND);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
    }

}

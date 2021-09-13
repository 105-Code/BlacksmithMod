package com.dani.blacksmithmod.screens;

import com.dani.blacksmithmod.BlacksmithMod;
import com.dani.blacksmithmod.containers.AnvilContainer;
import com.dani.blacksmithmod.containers.ForgeContainer;
import com.dani.blacksmithmod.tiles.ForgeTileEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.awt.*;

public class ForgeScreen extends ContainerScreen<ForgeContainer> {

    //Gui resource
    private static final ResourceLocation GUI_BACKGROUND = new ResourceLocation(BlacksmithMod.MODID, "textures/gui/forge_gui.png");

    public ForgeScreen(ForgeContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }


    /**
     * Draw gui background in screen
     * @param partialTicks Tick average
     * @param mouseX where is mouse in X axis
     * @param mouseY where is mouse in Y axis
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI_BACKGROUND);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);

        //font.drawString(this.container.forgeTileEntity().isBurning()? "Encendido":"Apagado",relX+10,relY+32, Color.BLACK.getRGB());
        //font.drawString(this.container.getTemperature()+"",relX+10,relY+41, Color.BLACK.getRGB());

    }


}
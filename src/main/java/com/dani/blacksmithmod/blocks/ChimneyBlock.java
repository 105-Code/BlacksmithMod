package com.dani.blacksmithmod.blocks;


import com.dani.blacksmithmod.BlacksmithMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class ChimneyBlock extends Block {

    public ChimneyBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(4.8f,5.0f)
                .sound(SoundType.STONE)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE));
        this.setRegistryName(BlacksmithMod.MODID,"chimney");
    }
}

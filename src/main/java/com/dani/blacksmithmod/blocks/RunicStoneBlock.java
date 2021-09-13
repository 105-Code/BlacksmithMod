package com.dani.blacksmithmod.blocks;

import com.dani.blacksmithmod.BlacksmithMod;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class RunicStoneBlock extends Block {

    public RunicStoneBlock() {
        super(net.minecraft.block.Block.Properties.create(Material.WOOD)
                .hardnessAndResistance(4.8f,5.0f)
                .sound(SoundType.STONE)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE));
        this.setRegistryName(BlacksmithMod.MODID,"runic_stone");
    }
}

package com.dani.blacksmithmod.tiles;

import com.dani.blacksmithmod.blocks.ChimneyBlock;
import com.dani.blacksmithmod.common.TileEntityRegister;
import com.dani.blacksmithmod.containers.ForgeContainer;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.INameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class ForgeTileEntity extends TileEntity implements INamedContainerProvider, INameable, ITickableTileEntity {

    protected int burnTime; // tiempo de quemado del combustible
    protected int heatTick; // temperatura que aumenta por tick
    protected int coldTick; // temperatura que disminuye si no esta encendido por tick
    protected int temperature; // temperatura de la forja
    protected int maxTemperature;

    private ItemStackHandler fuel = new ItemStackHandler(1);


    public ForgeTileEntity() {
        super(TileEntityRegister.FORGE_TILE_ENTITY);
    }

    public ForgeTileEntity(int maxTemperature,int heatTick, int coldTick){
        super(TileEntityRegister.FORGE_TILE_ENTITY);
        this.heatTick = heatTick;
        this.coldTick = coldTick;
        this.temperature = 0;
        this.maxTemperature = maxTemperature;
    }

    public ItemStackHandler getFuel(){
        return this.fuel;
    }

    @Override
    public ITextComponent getName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ForgeContainer(id, playerInventory, this);
    }


    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound); // The super call is required to save and load the tileEntity's location
        compound.putInt("BurnTime", this.burnTime);
        compound.putInt("HeatTick", this.heatTick);
        compound.putInt("ColdTick", this.coldTick);
        compound.putInt("MaxTemperature", this.maxTemperature);
        compound.putInt("Temperature", this.temperature);
        compound.put("Fuel",this.fuel.serializeNBT());
        return compound;
    }

    // This is where you load the data that you saved in write
    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound); // The super call is required to save and load the tiles location
        this.burnTime = compound.getInt("BurnTime");
        this.temperature = compound.getInt("Temperature");
        this.heatTick = compound.getInt("HeatTick");
        this.coldTick = compound.getInt("ColdTick");
        this.maxTemperature = compound.getInt("MaxTemperature");
        this.fuel.deserializeNBT(compound.getCompound("Fuel"));
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public int getTemperature(){
        return this.temperature;
    }

    private boolean hasChimney(){
        BlockState topState = this.world.getBlockState(this.pos.add(0,1,0));
        if(!topState.isAir()){
            Block block = topState.getBlock();
            if(block instanceof ChimneyBlock){
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick() {
        if(this.hasChimney()){
            boolean save = false;
            boolean isBurning = this.isBurning();

            if (this.isBurning()) {
                --this.burnTime;
                if(this.temperature <= this.maxTemperature)
                    this.temperature+=this.heatTick;
            }
            else{
                if(this.temperature >= 0)
                    this.temperature-=this.coldTick;
            }

            if (!this.world.isRemote) {
                ItemStack fuel = this.fuel.getStackInSlot(0);

                if (this.isBurning() || !fuel.isEmpty()) {

                    if (!this.isBurning()) { // no está quemando
                        this.burnTime  = ForgeHooks.getBurnTime(fuel);
                        if (this.isBurning()) {
                            fuel.shrink(1);
                            save = true;
                        }
                    }
                }
                if (isBurning != this.isBurning()) {
                    save = true;
                    this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, Boolean.valueOf(this.isBurning())), 3);
                }
            }

            if(save){
                this.markDirty();
            }
        }
    }
}

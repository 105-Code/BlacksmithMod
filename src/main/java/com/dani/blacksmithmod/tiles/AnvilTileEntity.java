package com.dani.blacksmithmod.tiles;

import com.dani.blacksmithmod.containers.AnvilContainer;
import com.dani.blacksmithmod.items.itemabstract.Recipe;
import com.dani.blacksmithmod.items.recipes.*;
import com.dani.blacksmithmod.setup.TileEntityRegister;
import com.dani.blacksmithmod.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class AnvilTileEntity extends TileEntity implements INamedContainerProvider{

    private int hits;
    private ItemStackHandler materials = new ItemStackHandler(9);

    //recipe permit in anvil block.
    private static final Recipe[] recipes = new Recipe[]{
            new IronShieldRecipe(),
            new GoldShieldRecipe(),
            new DiamondShieldRecipe(),
            new SaddleRecipe(),
            new DiamondHorseArmorRecipe(),
            new IronHorseArmorRecipe(),
            new GoldHorseArmorRecipe()
    };

    public AnvilTileEntity(final TileEntityType<?> tileEntityTypeIn){
        super(tileEntityTypeIn);
    }

    public AnvilTileEntity(){
        this(TileEntityRegister.ANVIL_TILE_ENTITY);
    }

    public ItemStackHandler getMaterials(){
        return this.materials;
    }

    public void setHits(int hit){
        this.hits=hit;
        this.markDirty();
    }

    public int getHits() {
        return hits;
    }

    /**
     * When the player hit the anvil block, this methods ys called. When the hits counter is 5, the method will
     * search what recipe is, and drop the crafting result item.
     */
    public void addHit(){
        System.out.println("Hit");
        if(this.getHits() > 4){
            for(Recipe recipe : recipes){
                if (recipe.matches(this.materials,world)){
                    Block.spawnAsEntity(world,this.pos.add(0,1,0),recipe.getCraftingResult(this.materials));
                    break;
                }
            }
            this.setHits(0);
        }else
            this.setHits(this.getHits()+1);
    }

    /**
     * This methos is important, this is used to stay sync whit client side and server side
     * @param compound data incoming
     * @return data to send.
     */
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("values", NBTHelper.ToNBT(this));
        compound.put("materials",this.materials.serializeNBT());
        return super.write(compound);
    }

    /**
     * This methos is important, this is used to stay sync whit client side and server side
     * @param compound data incoming
     */
    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        CompoundNBT values=compound.getCompound("values");
        if(values != null){
            this.hits = values.getShort("hits");
        }
        this.materials.deserializeNBT(compound.getCompound("materials"));
    }

    /**
     * get container name.
     * @return ITextComponent with container name.
     */
    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    /**
     * create a new container to show GUI.
     * @param i no idea
     * @param playerInventory player Inventory
     * @param playerEntity player enriry
     * @return new container to show
     */
    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new AnvilContainer(i, world, pos, playerInventory, playerEntity);
    }

}

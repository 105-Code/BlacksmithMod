package com.dani.blacksmithmod.tiles;

import com.dani.blacksmithmod.common.RecipeRegister;
import com.dani.blacksmithmod.containers.AnvilContainer;
import com.dani.blacksmithmod.common.TileEntityRegister;
import com.dani.blacksmithmod.inventory.AnvilInventory;
import com.dani.blacksmithmod.recipes.AnvilRecipe;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.INameable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

public class AnvilTileEntity extends TileEntity implements INamedContainerProvider, INameable {

    private int hits;
    private AnvilInventory inventory = new AnvilInventory();



    public AnvilTileEntity(){
        super(TileEntityRegister.ANVIL_TILE_ENTITY);
    }

    public void setHits(int hit){
        this.hits=hit;
        this.markDirty();
    }

    public AnvilInventory getInventory(){
        return this.inventory;
    }

    public int getHits() {
        return hits;
    }

    /**
     * When the player hit the anvil block, this methods ys called. When the hits counter is 5, the method will
     * search what recipe is, and drop the crafting result item.
     */
    public void addHit(World world){
        if(this.getHits() > 4){

            //aqui debería ver el bloque adyacente para ver la temperatura.

            this.setHits(0);
            Optional<AnvilRecipe> matchingRecipe = world.getServer().getRecipeManager().getRecipe(RecipeRegister.ANVIL_RECIPE,  this.inventory, world);
            if (matchingRecipe.isPresent()) {
                this.inventory.clear();
                Block.spawnAsEntity(world,this.pos.add(0,1,0),matchingRecipe.get().getCraftingResult(this.inventory));
            }

            this.setHits(1);
        }else
            this.setHits(this.getHits()+1);
    }

    @Override
    public ITextComponent getName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
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
     * @param id world id
     * @param playerInventory player Inventory
     * @param playerEntity player entity
     * @return new container to show
     */
    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new AnvilContainer(id, playerInventory, this.inventory);
    }

    // This is where you save any data that you don't want to lose when the tile entity unloads
    // In this case, it saves the chestContents, which contains the ItemStacks stored in the chest
    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound); // The super call is required to save and load the tileEntity's location
        compound.putInt("Hits", this.hits);
        this.inventory.serializeNBT(compound);
        return compound;
    }

    // This is where you load the data that you saved in write
    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound); // The super call is required to save and load the tiles location
        this.hits = compound.getInt("Hits");
        this.inventory.deserializeNBT(compound);
    }

}

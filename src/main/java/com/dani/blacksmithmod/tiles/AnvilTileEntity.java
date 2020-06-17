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

    public void setHits(int hit){
        this.hits=hit;
        this.markDirty();
    }

    public int getHits() {
        return hits;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("values", NBTHelper.ToNBT(this));
        compound.put("materials",this.materials.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        CompoundNBT values=compound.getCompound("values");
        if(values != null){
            this.hits = values.getShort("hits");
        }
        this.materials.deserializeNBT(compound.getCompound("materials"));
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new AnvilContainer(i, world, pos, playerInventory, playerEntity);
    }


    /*

    public MaterialStackHandler materials;

    private final IRecipe<CraftingInventory>[] recipes = new IRecipe[]{
            new IronShieldRecipe(),
            new GoldShieldRecipe(),
            new DiamondShieldRecipe(),
            new SaddleRecipe(),
            new DiamondHorseArmorRecipe(),
            new IronHorseArmorRecipe(),
            new GoldHorseArmorRecipe()
    };

    public final OutputStackHandler output;
    private short hit;

    private Container container;



    public AnvilTileEntity() {
        super(TileEntityRegister.ANVIL_TILE_ENTITY);
        this.materials= new MaterialStackHandler(9);
        this.output = new OutputStackHandler("output");
        this.hit=0;
    }


    public boolean addHit(World worldIn) {
        System.out.println("Hit");
        if (this.hit < 4){
            this.hit++;
            this.markDirty();
            return true;
        }
        this.hit = 0;
        System.out.println("hit = 0");
        System.out.println(this.materials.getSizeInventory());
        System.out.println(this.materials);
        for(IRecipe recipe : this.recipes){
            System.out.println("Searching Recipe");
            if (recipe.matches(this.materials,worldIn)){
                System.out.println("Recipe Found?");
                this.markDirty();
                return this.output.outputItem(recipe.getCraftingResult(this.materials));
            }
        }
        this.markDirty();
        return false;
    }




    //Test

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        System.out.println("En Write");
        if(nbt == null) nbt = new CompoundNBT();
        nbt.putShort("hits",this.hit);
        nbt.put("materials",this.materials.serializeNBT());
        return super.write(nbt);
    }



    @Override
    public void read(CompoundNBT nbt) {
        System.out.println("En Read");
        if(nbt != null ) {
            if (nbt.hasUniqueId("hits"))
                this.hit = nbt.getShort("hits");
            if (nbt.hasUniqueId("materials"))
                this.materials.deserializeNBT(nbt.getCompound("materials"));
        }
        super.read(nbt);
    }


*/

}

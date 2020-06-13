package com.dani.blacksmithmod.containers.anvilcontainer;

import com.dani.blacksmithmod.containers.anvilcontainer.slot.IngredientSlot;
import com.dani.blacksmithmod.containers.anvilcontainer.slot.OutputSlot;
import com.dani.blacksmithmod.containers.anvilcontainer.slot.PatternSlot;
import com.dani.blacksmithmod.setup.RegistryHandler;
import com.dani.blacksmithmod.tiles.anviltileentity.AnvilTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class AnvilContainer extends Container {

    private AnvilTileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;

    private ItemStackHandler pattern;
    private ItemStackHandler ingredients;
    private ItemStackHandler output;
    private String outputItemName;

    public AnvilContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(RegistryHandler.ANVIL_CONTAINER.get(), windowId);

        tileEntity = (AnvilTileEntity) world.getTileEntity(pos);

        this.pattern = tileEntity.pattern;
        this.ingredients = tileEntity.ingredients;
        this.output = tileEntity.output;


        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        this.addPlayerSlots(playerInventory);
        this.anvilSlots();

    }

    /**
     * Agrega los Slots del Inventario en la vista
     * @param playerInventory Jugador que abrio la GUI
     */
    private void addPlayerSlots(final PlayerInventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot((IInventory)playerInventory, col + row * 9 + 9, 8 + col * 18, 79 + row * 18));
            }
        }
        for (int hotbar = 0; hotbar < 9; ++hotbar) {
            this.addSlot(new Slot((IInventory)playerInventory, hotbar, 8 + hotbar * 18, 137));
        }
    }

    /**
     * Agrega los Slots de la interfaz
     */
    private void anvilSlots(){
        this.addSlot((Slot)new PatternSlot((IItemHandler)this.pattern, 0, 92, 50));
        this.addSlot((Slot)new OutputSlot((IItemHandler)this.output, 0, 144, 50));

        int index = 0;
        for (int row = 0; row < 2; ++row) {
            for (int col = 0; col < 3; ++col) {
                this.addSlot(new IngredientSlot((IItemHandler)this.ingredients, index, 9 + col  * 18, 40+ row * 18));
                ++index;
            }
        }

    }


    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, RegistryHandler.ANVIL.get());
    }

}

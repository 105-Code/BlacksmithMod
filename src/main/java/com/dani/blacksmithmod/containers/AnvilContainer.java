package com.dani.blacksmithmod.containers;

import com.dani.blacksmithmod.common.ContainerRegister;
import com.dani.blacksmithmod.inventory.AnvilInventory;
import com.dani.blacksmithmod.objects.IngredientSlot;
import com.dani.blacksmithmod.tiles.AnvilTileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Objects;

public class AnvilContainer extends Container {

    private final IInventory anvilInventoryIn;



    public AnvilContainer(int windowId, PlayerInventory playerInventory, IInventory anvilInventoryIn) {
        super(ContainerRegister.ANVIL_CONTAINER, windowId);
        this.addPlayerSlots(playerInventory);
        this.anvilInventoryIn = anvilInventoryIn;
        this.anvilSlots();
    }

    public AnvilContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }

    private static AnvilInventory getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
        Objects.requireNonNull(data, "Packet Buffer cannot be null.");
        final TileEntity te = playerInv.player.world.getTileEntity(data.readBlockPos());
        if (te instanceof AnvilTileEntity) {
            return ((AnvilTileEntity) te).getInventory();
        }
        throw new IllegalStateException("Tile Entity Is Not Correct");
    }



    /**
     *  when the player use shift + click to transfer items between AnvilBlock Gui and Player Inventory
     * @param playerIn player
     * @param fromSlot slot where execute de comand shift + click
     * @return item stack empty to clean slot or the stack what did player want to move.
     */
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < this.inventorySlots.size()) {
                // From the block breaker inventory to player's inventory
                if (!this.mergeItemStack(current, 0,  36, true))
                    return ItemStack.EMPTY;
            } else {
                // From the player's inventory to block breaker's inventory
                if (!this.mergeItemStack(current, 0, 9, false))
                    return ItemStack.EMPTY;
            }

            if (current.getCount() == 0) //Use func_190916_E() instead of stackSize 1.11 only 1.11.2 use getCount()
                slot.putStack(ItemStack.EMPTY); //Use ItemStack.field_190927_a instead of (ItemStack)null for a blank item stack. In 1.11.2 use ItemStack.EMPTY
            else
                slot.onSlotChanged();

            if (current.getCount() == previous.getCount())
                return null;
            slot.onTake(playerIn, current);
        }
        return previous;
    }

    /**
     * add inventory slot  and hotbar slot in screen.
     * @param playerInventory player who open the gui.
     */
    private void addPlayerSlots(final PlayerInventory playerInventory) {
        for(int col = 0; col < 9;col++ ){
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 137));
            for(int row = 0; row < 3;row ++){
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 79 + row * 18));
            }
        }
    }

    /**
     * add anvil slots in screen.
     */
    private void anvilSlots(){
        int index = 0;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                this.addSlot(new Slot(this.anvilInventoryIn, index, 62 + col  * 18, 14 + row * 18));
                ++index;
            }
        }
    }

    /**
     * specific if the entity could interact with the container.
     * @param playerIn entity who interact.
     * @return True if can interact or False if cannot.
     */
    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

}

package com.dani.blacksmithmod.containers;

import com.dani.blacksmithmod.objects.IngredientSlot;
import com.dani.blacksmithmod.setup.BlockRegister;
import com.dani.blacksmithmod.setup.ContainerRegister;
import com.dani.blacksmithmod.tiles.AnvilTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class AnvilContainer extends Container {

    private AnvilTileEntity tileEntity;
    private PlayerEntity playerEntity;
    private ItemStackHandler ingredients;

    public AnvilContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(ContainerRegister.ANVIL_CONTAINER, windowId);
        tileEntity = (AnvilTileEntity) world.getTileEntity(pos);
        this.ingredients = tileEntity.getMaterials();
        this.playerEntity = player;
        this.addPlayerSlots(playerInventory);
        this.anvilSlots();
    }

    /**
     *  when the player use shift + click to transfer items between Anvil Gui and Player Inventory
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
                this.addSlot(new IngredientSlot(this.ingredients, index, 62 + col  * 18, 14 + row * 18));
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
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, BlockRegister.ANVIL);
    }


}

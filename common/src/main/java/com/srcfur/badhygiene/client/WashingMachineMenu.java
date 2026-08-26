package com.srcfur.badhygiene.client;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class WashingMachineMenu extends AbstractContainerMenu {
    private final Container inventory;

    public WashingMachineMenu(int containerId, Inventory playerInventory){
        this(containerId, playerInventory, new SimpleContainer(9));
    }

    public WashingMachineMenu(int containerId, Inventory playerInventory, Container entity) {
        super(BadHygieneScreens.WashingMachineMenu, containerId);
        inventory = entity;
        addPlayerHotbar(playerInventory);
        addPlayerInventory(playerInventory);
        addWashingMachine();
    }

    @Override
    protected boolean moveItemStackTo(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        return super.moveItemStackTo(stack, startIndex, endIndex, reverseDirection);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    private void addWashingMachine(){
        this.addSlot(new Slot(inventory,0, 62, 35));
        for(int i = 0; i < 8; i++){
            this.addSlot(new Slot(inventory,i + 1, 8 + (18 * (i / 4)), 8 + 18 * (i % 4)){
                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });
        }
    }
    private void addPlayerHotbar(Inventory playerInventory){
        for(int i = 0; i < 9; i++){
            this.addSlot(new Slot(playerInventory, i, 8 + 18 * (i % 9), 141));
        }
    }
    private void addPlayerInventory(Inventory playerInventory){
        for(int i = 0; i < 27; i++){
            this.addSlot(new Slot(playerInventory, i + 9, 8 + 18 * (i % 9), 84 + 18 * (i / 9)));
        }
    }
}

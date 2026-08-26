package com.srcfur.badhygiene.block.entity;

import com.srcfur.badhygiene.client.WashingMachineMenu;
import com.srcfur.badhygiene.component.BadHygieneDataComponents;
import com.srcfur.badhygiene.item.BadHygieneItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;

public class WashingMachineEntity extends BlockEntity implements Container, MenuProvider {
    public WashingMachineEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }

    public WashingMachineEntity(BlockPos pos, BlockState state){
        this(BadHygieneBlockEntities.WashingMachine, pos, state);
    }

    public NonNullList<ItemStack> inventory = NonNullList.withSize(9, ItemStack.EMPTY);
    public int washing_ticks = 0;

    public static void Tick(Level level, BlockPos blockPos, BlockState blockState, WashingMachineEntity entity){
        if(!entity.getItem(0).is(BadHygieneItems.Detergent) && entity.getItem(0).getCount() == 0) return;
        ArrayList<ItemStack> valid = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            if(entity.getItem(i + 1).getOrDefault(BadHygieneDataComponents.Soiled, false)){
                valid.add(entity.getItem(i + 1));
            }
        }
        if(valid.isEmpty()) return;
        entity.washing_ticks++;
        if(entity.washing_ticks > 80){
            entity.washing_ticks = 0;
            entity.getItem(0).setCount(entity.getItem(0).getCount() - 1);
            valid.getFirst().set(BadHygieneDataComponents.Soiled, false);
        }
    }

    @Override
    public int getContainerSize() {
        return 9;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int i) {
        return inventory.get(i);
    }

    @Override
    public ItemStack removeItem(int i, int i1) {
        ItemStack item = getItem(i);
        ItemStack pickup = item.copy();
        pickup.setCount(i1);
        item.setCount(item.getCount() - i1);
        return pickup;
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        ItemStack stack = getItem(i);
        ItemStack ret = stack.copy();
        stack.setCount(0);
        return ret;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        inventory.set(i, itemStack);
    }

    @Override
    public boolean stillValid(Player player) {
        return player.blockPosition().distSqr(getBlockPos()) < 10;
    }

    @Override
    public void clearContent() {
        for(int i = 0; i < getContainerSize(); i++){
            setItem(i, ItemStack.EMPTY);
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.badhygiene.washing_machine");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new WashingMachineMenu(i, inventory, this);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, inventory);
    }
}

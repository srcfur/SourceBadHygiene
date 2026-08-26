package com.srcfur.badhygiene.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ToiletBlockEntity extends AbstractToiletBlockEntity {
    public ToiletBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }
    public ToiletBlockEntity(BlockPos pos, BlockState state){
        this(BadHygieneBlockEntities.Toilet, pos, state);
    }
}

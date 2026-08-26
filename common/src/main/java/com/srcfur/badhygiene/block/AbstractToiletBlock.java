package com.srcfur.badhygiene.block;

import com.srcfur.badhygiene.BadHygieneCommon;
import com.srcfur.badhygiene.api.AbstractHygienePlayer;
import com.srcfur.badhygiene.item.BadHygieneItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractToiletBlock extends BaseEntityBlock {
    private static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;

    protected AbstractToiletBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return null;
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite())
                .setValue(BadHygieneBlockProperties.TOILET_USAGE, 0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
        builder.add(BadHygieneBlockProperties.TOILET_USAGE);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        AbstractHygienePlayer hygienePlayer = BadHygieneCommon.API.getHygienePlayer(player);
        if(stack.getItem() == Items.BUCKET && state.getValue(BadHygieneBlockProperties.TOILET_USAGE) > 0){
            if(player.getInventory().add(new ItemStack(BadHygieneItems.Biowaste, 1))){
                stack.setCount(stack.getCount() - 1);
                level.setBlockAndUpdate(pos, state.setValue(BadHygieneBlockProperties.TOILET_USAGE, state.getValue(BadHygieneBlockProperties.TOILET_USAGE) - 1));
                return InteractionResult.CONSUME;
            }else{
                player.sendSystemMessage(Component.literal("No space for biowaste..."));
            }
        }
        if(( hygienePlayer.getBladder() > 0 || hygienePlayer.getBowels() > 30 || player.isCreative() ) && state.getValue(BadHygieneBlockProperties.TOILET_USAGE) < 4 && hand == InteractionHand.MAIN_HAND){
            if(hygienePlayer.getBowels() > 30){
                hygienePlayer.setBowels(0);
            }
            hygienePlayer.setBladder(0);
            //We funnily enough for once only do this on the client, partially so we can do some config stuff in the future. And I want this to be a standard!
            if(level.isClientSide()) return InteractionResult.SUCCESS;
            level.setBlockAndUpdate(pos, state.setValue(BadHygieneBlockProperties.TOILET_USAGE, state.getValue(BadHygieneBlockProperties.TOILET_USAGE) + 1));
            for(Player player1 : level.players()){
                if(player1.distanceToSqr(pos.getCenter()) < 8){
                    player1.sendSystemMessage(Component.empty().append(player.getDisplayName()).append(Component.literal(" uses the bathroom")));
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}

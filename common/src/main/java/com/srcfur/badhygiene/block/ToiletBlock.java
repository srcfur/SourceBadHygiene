package com.srcfur.badhygiene.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class ToiletBlock extends AbstractToiletBlock {
    public ToiletBlock(Properties properties) {
        super(properties);
    }
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(ToiletBlock::new);
    }
}

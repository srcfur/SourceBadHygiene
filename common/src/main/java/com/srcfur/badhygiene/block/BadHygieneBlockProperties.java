package com.srcfur.badhygiene.block;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class BadHygieneBlockProperties {
    public static final IntegerProperty TOILET_USAGE = IntegerProperty.create("toilet_usage", 0, 4);
}

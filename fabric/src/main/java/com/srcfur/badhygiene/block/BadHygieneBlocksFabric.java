package com.srcfur.badhygiene.block;

import com.srcfur.badhygiene.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class BadHygieneBlocksFabric {
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties){
        Identifier id = Identifier.fromNamespaceAndPath(Constants.MOD_ID, name);
        Block block = factory.apply(properties.setId(ResourceKey.create(BuiltInRegistries.BLOCK.key(), id)));
        Registry.register(BuiltInRegistries.BLOCK, id, block);
        return block;
    }
    public static void RegisterBlocks(){
        BadHygieneBlocks.Latrine = register("wooden_latrine", ToiletBlock::new, BlockBehaviour.Properties.of().noOcclusion());
        BadHygieneBlocks.Toilet = register("toilet", ToiletBlock::new, BlockBehaviour.Properties.of().noOcclusion());
        BadHygieneBlocks.WashingMachine = register("washing_machine", WashingMachineBlock::new, BlockBehaviour.Properties.of());
    }
}

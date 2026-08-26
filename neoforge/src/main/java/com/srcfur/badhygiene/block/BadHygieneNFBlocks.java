package com.srcfur.badhygiene.block;

import com.srcfur.badhygiene.BadHygieneConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.Function;

@Mod(BadHygieneConstants.MOD_ID)
public class BadHygieneNFBlocks {
    public BadHygieneNFBlocks(IEventBus bus){
        bus.register(BadHygieneNFBlocks.class);
    }

    private static Block register(RegisterEvent.RegisterHelper<Block> registry, String name, Function<BlockBehaviour.Properties, Block> supplier, BlockBehaviour.Properties properties){
        Block block = supplier.apply(properties.setId(ResourceKey.create(BuiltInRegistries.BLOCK.key(), Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, name))));
        registry.register(Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, name), block);
        return block;
    }

    @SubscribeEvent
    public static void RegisterBlocks(RegisterEvent event){
        event.register(
                BuiltInRegistries.BLOCK.key(),
                registry -> {
                    BadHygieneBlocks.Latrine = register(registry, "wooden_latrine", ToiletBlock::new, BlockBehaviour.Properties.of().noOcclusion());
                    BadHygieneBlocks.Toilet = register(registry, "toilet", ToiletBlock::new, BlockBehaviour.Properties.of().noOcclusion());
                    BadHygieneBlocks.UrinePuddle = register(registry, "urine_puddle", UrinePuddleBlock::new, BlockBehaviour.Properties.of().noOcclusion());
                    BadHygieneBlocks.WashingMachine = register(registry, "washing_machine", WashingMachineBlock::new, BlockBehaviour.Properties.of());
                }
        );
    }
}

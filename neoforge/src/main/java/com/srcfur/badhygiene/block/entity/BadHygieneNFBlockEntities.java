package com.srcfur.badhygiene.block.entity;

import com.srcfur.badhygiene.Constants;
import com.srcfur.badhygiene.block.BadHygieneBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Set;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
@Mod(Constants.MOD_ID)
public class BadHygieneNFBlockEntities {
    public BadHygieneNFBlockEntities(IEventBus bus){
        bus.register(BadHygieneNFBlockEntities.class);
    }

    private static BlockEntityType<?> register(RegisterEvent.RegisterHelper<BlockEntityType<?>> registry, String name, BlockEntityType<?> ent){
        registry.register(Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), ent);
        return ent;
    }

    @SubscribeEvent
    public static void RegisterBlockEntities(RegisterEvent event){
        event.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE.key(),
                registry -> {
                    BadHygieneBlockEntities.Toilet = (BlockEntityType<AbstractToiletBlockEntity>) register(registry, "toilet", new BlockEntityType<>(
                            ToiletBlockEntity::new,
                            Set.of(BadHygieneBlocks.Toilet, BadHygieneBlocks.Latrine)
                    ));
                    BadHygieneBlockEntities.WashingMachine = (BlockEntityType<WashingMachineEntity>) register(registry, "washing_machine", new BlockEntityType<>(
                            WashingMachineEntity::new,
                            Set.of(BadHygieneBlocks.WashingMachine)
                    ));
                }
        );
    }
}

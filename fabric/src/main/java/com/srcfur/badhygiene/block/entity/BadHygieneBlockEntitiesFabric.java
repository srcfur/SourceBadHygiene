package com.srcfur.badhygiene.block.entity;

import com.srcfur.badhygiene.BadHygiene;
import com.srcfur.badhygiene.Constants;
import com.srcfur.badhygiene.block.BadHygieneBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BadHygieneBlockEntitiesFabric {
    private static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory, Block... blocks){
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name),
                FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }
    public static void RegisterBlockEntities(){
        BadHygieneBlockEntities.Toilet = register("toilet", ToiletBlockEntity::new, BadHygieneBlocks.Latrine, BadHygieneBlocks.Toilet);
        BadHygieneBlockEntities.WashingMachine = register("washing_machine", WashingMachineEntity::new, BadHygieneBlocks.WashingMachine);
    }
}

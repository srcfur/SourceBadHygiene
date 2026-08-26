package com.srcfur.badhygiene.component;

import com.mojang.serialization.Codec;
import com.srcfur.badhygiene.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

import java.util.function.UnaryOperator;

public class BadHygieneDataComponentsFabric {
    @SuppressWarnings("unchecked")
    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder){
        return (DataComponentType<T>) Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name),
                ((DataComponentType.Builder)builder.apply(DataComponentType.builder())).build());
    }
    public static void RegisterDataComponents(){
        BadHygieneDataComponents.Soiled = register("soiled", builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));
    }
}

package com.srcfur.badhygiene.component;

import com.mojang.serialization.Codec;
import com.srcfur.badhygiene.Constants;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.UnaryOperator;

@Mod(Constants.MOD_ID)
public class BadHygieneNFDataComponents {
    public BadHygieneNFDataComponents(IEventBus bus){
        bus.register(BadHygieneNFDataComponents.class);
    }

    private static <T> DataComponentType<T> register(RegisterEvent.RegisterHelper<DataComponentType<?>> registry, String name, UnaryOperator<DataComponentType.Builder<T>> builder){
        DataComponentType<T> component = ((DataComponentType.Builder)builder.apply(DataComponentType.builder())).build();
        registry.register(Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), component);
        return component;
    }

    @SubscribeEvent
    public static void RegisterDataComponents(RegisterEvent event){
        event.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE.key(),
                registry -> {
                    BadHygieneDataComponents.Soiled = register(registry, "soiled", b -> b.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));
                }
        );
    }
}

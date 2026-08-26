package com.srcfur.badhygiene.item;

import com.srcfur.badhygiene.Constants;
import com.srcfur.badhygiene.block.BadHygieneBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.BiFunction;
import java.util.function.Function;

@Mod(Constants.MOD_ID)
public class BadHygieneNFItems {
    public BadHygieneNFItems(IEventBus bus){
        ITEMS.register(bus);
        bus.register(BadHygieneNFItems.class);
    }
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(Constants.MOD_ID);

    private static Item register(String name, RegisterEvent.RegisterHelper<Item> registry, Function<Item.Properties, Item> supplier, Item.Properties baseproperties){
        Item item = supplier.apply(baseproperties.setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name))));
        registry.register(Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), item);
        return item;
    }
    private static BlockItem registerBlockItem(String name, RegisterEvent.RegisterHelper<Item> registry, Block block, Item.Properties baseProperties){
        return (BlockItem) register(name, registry, properties -> new BlockItem(block, properties), baseProperties);
    }

    @SubscribeEvent
    public static void RegisterItems(RegisterEvent event){
        event.register(
                BuiltInRegistries.ITEM.key(),
                registry -> {
                    BadHygieneItems.Biowaste = register("biowaste", registry, Item::new, new Item.Properties());
                    BadHygieneItems.Detergent = register("detergent", registry, Item::new, new Item.Properties());
                    BadHygieneItems.Latrine = registerBlockItem("wooden_latrine", registry, BadHygieneBlocks.Latrine, new Item.Properties());
                    BadHygieneItems.Toilet = registerBlockItem("toilet", registry, BadHygieneBlocks.Toilet, new Item.Properties());
                    BadHygieneItems.WashingMachine = registerBlockItem("washing_machine", registry, BadHygieneBlocks.WashingMachine, new Item.Properties());
                }
        );
    }
}

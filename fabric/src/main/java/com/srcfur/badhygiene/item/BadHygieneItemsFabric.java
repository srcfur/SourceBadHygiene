package com.srcfur.badhygiene.item;

import com.srcfur.badhygiene.BadHygieneConstants;
import com.srcfur.badhygiene.block.BadHygieneBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class BadHygieneItemsFabric {
    private static Item register(String name, Function<Item.Properties, Item> factory, Item.Properties properties){
        Item item = factory.apply(properties.setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, name))));
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, name), item);
        return item;
    }
    private static BlockItem registerBlockItem(String name, Block block, Item.Properties properties){
        return (BlockItem) register(name, prop->new BlockItem(block, prop), properties);
    }
    public static void RegisterItems(){
        BadHygieneItems.Biowaste = register("biowaste", Item::new, new Item.Properties());
        BadHygieneItems.Detergent = register("detergent", Item::new, new Item.Properties());
        BadHygieneItems.Latrine = registerBlockItem("wooden_latrine", BadHygieneBlocks.Latrine, new Item.Properties());
        BadHygieneItems.Toilet = registerBlockItem("toilet", BadHygieneBlocks.Toilet, new Item.Properties());
        BadHygieneItems.WashingMachine = registerBlockItem("washing_machine", BadHygieneBlocks.WashingMachine, new Item.Properties());
    }
}

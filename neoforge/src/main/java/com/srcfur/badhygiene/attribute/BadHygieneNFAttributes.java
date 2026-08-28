package com.srcfur.badhygiene.attribute;

import com.srcfur.badhygiene.BadHygieneConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public class BadHygieneNFAttributes {
    private static Holder<Attribute> register(RegisterEvent.RegisterHelper<Attribute> registry, String name, Attribute attribute){
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, name), attribute);
    }
    @SubscribeEvent
    public static void RegisterAttributes(RegisterEvent event){
        event.register(
                BuiltInRegistries.ATTRIBUTE.key(),
                registry -> {
                    BadHygieneAttributes.Continence = register(registry, "continence", new RangedAttribute("", 1, 0, 10));
                }
        );
    }
    @SubscribeEvent // on the mod event bus
    public static void modifyDefaultAttributes(EntityAttributeModificationEvent event) {
        event.add(
                // The EntityType to add the attribute for.
                EntityType.PLAYER,
                // The Holder<Attribute> to add to the EntityType. Can also be a custom attribute.
                BadHygieneAttributes.Continence,
                // The attribute value to add.
                // Can be omitted, if so, the attribute's default value will be used instead.
                1
        );
    }
}

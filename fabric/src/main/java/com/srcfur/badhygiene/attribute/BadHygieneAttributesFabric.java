package com.srcfur.badhygiene.attribute;

import com.srcfur.badhygiene.BadHygieneConstants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class BadHygieneAttributesFabric {
    private static Attribute register(String name, Attribute attribute){
        return Registry.register(BuiltInRegistries.ATTRIBUTE, Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, name), attribute);
    }
    public static void RegisterAttributes() {
        BadHygieneAttributes.Continence = register("continence", new RangedAttribute("", 1, 0, 4));
    }
}

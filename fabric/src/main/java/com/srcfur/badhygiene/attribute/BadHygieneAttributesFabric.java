package com.srcfur.badhygiene.attribute;

import com.srcfur.badhygiene.BadHygieneConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class BadHygieneAttributesFabric {
    private static Holder<Attribute> register(String name, Attribute attribute){
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, name), attribute);
    }
    public static void RegisterAttributes() {
        BadHygieneAttributes.Continence = register("continence", new RangedAttribute("", 1, 0, 4));
    }
}

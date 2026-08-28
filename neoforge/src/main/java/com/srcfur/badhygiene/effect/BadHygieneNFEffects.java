package com.srcfur.badhygiene.effect;

import com.srcfur.badhygiene.BadHygieneConstants;
import com.srcfur.badhygiene.attribute.BadHygieneAttributes;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.awt.*;

public class BadHygieneNFEffects {
    private static Holder<MobEffect> register(RegisterEvent.RegisterHelper<MobEffect> registry, String name, MobEffect effect){
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, name), effect);
    }
    @SubscribeEvent
    public static void RegisterEffects(RegisterEvent event){
        event.register(
                BuiltInRegistries.MOB_EFFECT.key(),
                registry -> {
                    BadHygieneEffects.Incontinence = register(registry, "incontinence",
                            new IncontinenceEffect(MobEffectCategory.HARMFUL, Color.black.getRGB())
                            .addAttributeModifier(BadHygieneAttributes.Continence, Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, "incontinence"), -0.3, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                }
        );
    }
}

package com.srcfur.badhygiene.mixin;

import com.srcfur.badhygiene.BadHygiene;
import com.srcfur.badhygiene.BadHygieneConstants;
import com.srcfur.badhygiene.attribute.BadHygieneAttributes;
import com.srcfur.badhygiene.attribute.BadHygieneAttributesFabric;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(at = @At("RETURN"), method = "createAttributes")
    private static void createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir){
        BadHygieneAttributesFabric.RegisterAttributes();
        AttributeSupplier.Builder builder = cir.getReturnValue();
        builder.add(BadHygieneAttributes.Continence, 1);
    }
}

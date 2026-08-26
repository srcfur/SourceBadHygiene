package com.srcfur.badhygiene.mixin;

import com.srcfur.badhygiene.BadHygieneCommon;
import com.srcfur.badhygiene.api.AbstractHygienePlayer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.logging.Logger;

@Mixin(Consumable.class)
public class ConsumeableMixin {
    @Inject(at = @At("HEAD"), method = "onConsume")
    public void onConsume(Level level, LivingEntity user, ItemStack stack, CallbackInfoReturnable callbackInfo) {
        if (user instanceof ServerPlayer serverPlayer) {
            AbstractHygienePlayer hygienePlayer = BadHygieneCommon.API.getHygienePlayer(serverPlayer);
            FoodProperties properties = stack.getOrDefault(DataComponents.FOOD, new FoodProperties(0, 0, false));
            hygienePlayer.setBowels(hygienePlayer.getBowels() + (properties.nutrition() / 3));
            Logger.getAnonymousLogger().info("New player bowels: " + hygienePlayer.getBowels());
        }
    }
}

package com.srcfur.badhygiene.mixin;

import com.srcfur.badhygiene.CommonClass;
import com.srcfur.badhygiene.api.AbstractHygienePlayer;
import com.srcfur.badhygiene.api.HygieneAPI;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.logging.Level;
import java.util.logging.Logger;

@Mixin(FoodData.class)
public class HungerMixin {
    @Shadow
    public float exhaustionLevel;
    @Shadow
    public float saturationLevel;

    @Inject(method = "tick", at = @At("Head"))
    public void badhygigne$tick(ServerPlayer player, CallbackInfo info){
        ServerLevel level = player.level();
        Difficulty difficulty = level.getDifficulty();
        if (this.exhaustionLevel > 4.0F) {
            AbstractHygienePlayer hygiene = CommonClass.API.getHygienePlayer(player);
            hygiene.setBladder(hygiene.getBladder() + 1);
            Logger.getAnonymousLogger().log(Level.INFO, "New Bladder Level :3 " + Integer.toString(hygiene.getBladder()));
        }
    }
}

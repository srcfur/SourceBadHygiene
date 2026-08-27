package com.srcfur.badhygiene.mixin;

import com.mojang.authlib.GameProfile;
import com.srcfur.badhygiene.BadHygieneCommon;
import com.srcfur.badhygiene.api.AbstractHygienePlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(Player.class)
public abstract class DirtyDamageMixin extends Entity {


    public DirtyDamageMixin(EntityType<?> type, Level level) {
        super(type, level);
    }
    @Inject(at = @At("RETURN"), method = "hurtServer")
    public void hurtServer(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir){
        if (source.scalesWithDifficulty()) {
            if (level.getDifficulty() == Difficulty.PEACEFUL) {
                damage = 0.0F;
            }

            if (level.getDifficulty() == Difficulty.EASY) {
                damage = Math.min(damage / 2.0F + 1.0F, damage);
            }

            if (level.getDifficulty() == Difficulty.HARD) {
                damage = damage * 3.0F / 2.0F;
            }
        }
        AbstractHygienePlayer hygienePlayer = BadHygieneCommon.API.getHygienePlayer((Player) level.getEntity(uuid));
        hygienePlayer.impactHygiene(-(int)damage);
    }
}

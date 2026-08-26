package com.srcfur.badhygiene.mixin;

import com.srcfur.badhygiene.component.BadHygieneDataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(at = @At("HEAD"), method = "inventoryTick")
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot, CallbackInfo info){
        if(level.getGameTime() % 20 == 0 && itemStack.getOrDefault(BadHygieneDataComponents.Soiled, false)){
            itemStack.setDamageValue(itemStack.getDamageValue() + 1);
        }
    }
}

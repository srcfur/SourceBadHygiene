package com.srcfur.badhygiene.mixin;

import com.srcfur.badhygiene.client.HygieneSurvivalUI;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @Inject(method = "extractHotbar", at = @At("Head"))
    private void badhygiene$hotbar(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci){
        HygieneSurvivalUI.extractHygiene(extractor, tracker);
    }
}

package com.srcfur.badhygiene.client;

import com.srcfur.badhygiene.Constants;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

import java.awt.*;
import java.lang.module.Configuration;

public class HygieneSurvivalUI {
    public static final Identifier Bladder_Bar_Sprite = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "bladder_bar_frame");
    public static void extractHygiene(GuiGraphicsExtractor graphics, DeltaTracker tracker){
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, Bladder_Bar_Sprite, graphics.guiWidth() - 50, graphics.guiHeight() - 48, 32, 48);
    }
}

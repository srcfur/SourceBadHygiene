package com.srcfur.badhygiene.client;

import com.srcfur.badhygiene.BadHygieneCommon;
import com.srcfur.badhygiene.BadHygieneConstants;
import com.srcfur.badhygiene.api.AbstractHygienePlayer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public class HygieneSurvivalUI {
    public static final Identifier BladderSpriteSheet = Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, "bladder");
    public static void extractHygiene(GuiGraphicsExtractor graphics, DeltaTracker tracker){
        int frames = 1;
        int offset = 0;

        AbstractHygienePlayer hygienePlayer = BadHygieneCommon.API.getHygienePlayer(Minecraft.getInstance().player);

        if(hygienePlayer.getBladderFullness() > 0.1) { frames = 3; offset = 32; }
        if(hygienePlayer.getBladderFullness() > 0.5) { frames = 4; offset = 128; }
        if(hygienePlayer.getBladderFullness() > 0.9) { frames = 2; offset = 256; }

        int ticks = (int)(Minecraft.getInstance().level.getGameTime() % 20);
        offset = offset + (ticks / Math.ceilDiv(20, frames)) * 32;

        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BladderSpriteSheet, 320, 32, offset, 0, graphics.guiWidth() - 50, graphics.guiHeight() - 48, 32, 32);
    }
}

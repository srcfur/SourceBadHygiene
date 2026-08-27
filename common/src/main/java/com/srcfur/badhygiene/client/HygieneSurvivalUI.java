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
    public static final Identifier HygieneSpriteSheet = Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, "hygiene");
    public static final Identifier BowelWarning = Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, "bowel_warning");
    public static void extractHygiene(GuiGraphicsExtractor graphics, DeltaTracker tracker){
        int frames = 1;
        int offset = 0;

        //Bladder Indicator
        AbstractHygienePlayer hygienePlayer = BadHygieneCommon.API.getHygienePlayer(Minecraft.getInstance().player);
        if(hygienePlayer.getBladderFullness() > 0.1) { frames = 3; offset = 32; }
        if(hygienePlayer.getBladderFullness() > 0.5) { frames = 4; offset = 128; }
        if(hygienePlayer.getBladderFullness() > 0.9) { frames = 2; offset = 256; }
        int ticks = (int)(Minecraft.getInstance().level.getGameTime() % 20);
        offset = offset + (ticks / Math.ceilDiv(20, frames)) * 32;
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BladderSpriteSheet, 320, 32, offset, 0, graphics.guiWidth() - 32, graphics.guiHeight() - 32, 32, 32);

        //Bowel Indicator
        int bowel_frequency = (50 - hygienePlayer.getBowels());
        if(hygienePlayer.getBowels() > 20 && Minecraft.getInstance().level.getGameTime() % bowel_frequency >= bowel_frequency / 2)
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BowelWarning, graphics.guiWidth() - 20, graphics.guiHeight() - 50, 9,18);

        //Hygiene Indicator
        int hygieneLevel = Math.min((100 - hygienePlayer.getCleanliness()) / 23, 3);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, HygieneSpriteSheet, 128, 32, hygieneLevel * 32, 0, graphics.guiWidth() - 64, graphics.guiHeight() - 32, 32, 32);
    }
}

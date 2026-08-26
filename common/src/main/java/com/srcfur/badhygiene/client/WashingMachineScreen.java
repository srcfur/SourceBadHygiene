package com.srcfur.badhygiene.client;

import com.srcfur.badhygiene.Constants;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class WashingMachineScreen extends AbstractContainerScreen<WashingMachineMenu> {
    private static final Identifier BACKGROUND = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "textures/gui/container/washing_machine_screen.png");


    public WashingMachineScreen(WashingMachineMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        titleLabelX = 44;
        titleLabelY = 6;
        inventoryLabelX = 44;
        inventoryLabelY = 70;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float a) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
    }
}

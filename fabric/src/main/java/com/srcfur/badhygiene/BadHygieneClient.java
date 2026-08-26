package com.srcfur.badhygiene;

import com.srcfur.badhygiene.client.BadHygieneScreens;
import com.srcfur.badhygiene.client.WashingMachineScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class BadHygieneClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(BadHygieneScreens.WashingMachineMenu, WashingMachineScreen::new);
    }
}

package com.srcfur.badhygiene.client;

import com.srcfur.badhygiene.BadHygieneConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(BadHygieneConstants.MOD_ID)
public class BadHygieneNFScreens {
    public BadHygieneNFScreens(IEventBus bus){
        bus.register(BadHygieneNFScreens.class);
    }

    private static <T extends AbstractContainerMenu> MenuType<T> registerMenu(RegisterEvent.RegisterHelper<MenuType<?>> registry, String name, MenuType.MenuSupplier<T> supplier){
        MenuType<T> menuType = new MenuType<>(supplier, FeatureFlags.VANILLA_SET);
        registry.register(Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, name), menuType);
        return menuType;
    }

    @SubscribeEvent
    public static void RegisterScreens(RegisterEvent event){
        event.register(
                BuiltInRegistries.MENU.key(),
                registry -> {
                    BadHygieneScreens.WashingMachineMenu = registerMenu(registry, "washingmachine", WashingMachineMenu::new);
                }
        );
    }

    @SubscribeEvent
    public static void RegisterScreens(RegisterMenuScreensEvent event){
        event.register(BadHygieneScreens.WashingMachineMenu, WashingMachineScreen::new);
    }
}

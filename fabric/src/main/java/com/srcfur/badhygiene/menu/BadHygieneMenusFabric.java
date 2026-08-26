package com.srcfur.badhygiene.menu;

import com.srcfur.badhygiene.Constants;
import com.srcfur.badhygiene.client.BadHygieneScreens;
import com.srcfur.badhygiene.client.WashingMachineMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class BadHygieneMenusFabric {
    private static <T extends AbstractContainerMenu> MenuType<T> register(String name, MenuType.MenuSupplier<T> supplier){
        return Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), new MenuType<>(supplier, FeatureFlags.VANILLA_SET));
    }
    public static void RegisterMenus(){
        BadHygieneScreens.WashingMachineMenu = register("washing_machine", WashingMachineMenu::new);
    }
}

package com.srcfur.badhygiene;


import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Constants.MOD_ID)
public class BadHygieneMod {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(Constants.MOD_ID);
    public BadHygieneMod(IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        Constants.LOG.info("Hello NeoForge world!");

        CommonClass.init();
        eventBus.register(this);
    }

    @SubscribeEvent
    public void RegistrationBridge(RegisterEvent event){

    }
}
package com.srcfur.badhygiene;


import com.srcfur.badhygiene.attribute.BadHygieneNFAttributes;
import com.srcfur.badhygiene.effect.BadHygieneNFEffects;
import net.minecraft.server.players.PlayerList;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(BadHygieneConstants.MOD_ID)
public class BadHygieneMod {
    public BadHygieneMod(IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        BadHygieneConstants.LOG.info("Hello NeoForge world!");

        BadHygieneCommon.init();
        eventBus.register(this);

        eventBus.register(BadHygieneNFAttributes.class);
        eventBus.register(BadHygieneNFEffects.class);

        NeoForge.EVENT_BUS.addListener(BadHygieneMod::ServerTick);
    }

    @SubscribeEvent
    public void RegistrationBridge(RegisterEvent event){

    }
    public static void ServerTick(ServerTickEvent.Pre event){
        PlayerList players = event.getServer().getPlayerList();
        for(int i = 0; i < players.getPlayerCount(); i++){
            BadHygieneCommon.API.serverTick(players.getPlayers().get(i));
        }
    }
}
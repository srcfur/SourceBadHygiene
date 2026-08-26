package com.srcfur.badhygiene.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.InteractionResult;

public interface PlayerMakePuddle extends SimplePlayerEventInterface {
    public Event<PlayerMakePuddle> EVENT = EventFactory.createArrayBacked(PlayerMakePuddle.class,
            listeners -> plr -> {
               for (PlayerMakePuddle listener : listeners){
                    InteractionResult result = listener.process(plr);
                    if(result != InteractionResult.PASS){
                        return result;
                    }
               }
               return InteractionResult.PASS;
            });
}

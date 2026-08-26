package com.srcfur.badhygiene.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

public interface PlayerPoopSelf extends SimplePlayerEventInterface {
    Event<PlayerPoopSelf> EVENT = EventFactory.createArrayBacked(PlayerPoopSelf.class,
            listeners -> player -> {
                for(PlayerPoopSelf listener : listeners){
                    InteractionResult result = listener.process(player);
                    if(result != InteractionResult.PASS){
                        return result;
                    }
                }
                return InteractionResult.PASS;
            });
}

package com.srcfur.badhygiene.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

public interface PlayerPeeSelf extends SimplePlayerEventInterface {
    Event<PlayerPeeSelf> EVENT = EventFactory.createArrayBacked(PlayerPeeSelf.class,
            listeners -> (player) -> {
                for (PlayerPeeSelf listener : listeners){
                    InteractionResult result = listener.process(player);
                    if(result != InteractionResult.PASS){
                        return result;
                    }
                }
                return InteractionResult.PASS;
            });
}

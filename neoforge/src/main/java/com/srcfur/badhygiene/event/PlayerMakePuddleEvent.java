package com.srcfur.badhygiene.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;

public class PlayerMakePuddleEvent extends AbstractHygieneEvent implements ICancellableEvent {
    public PlayerMakePuddleEvent(Player player) {
        super(player);
    }
}

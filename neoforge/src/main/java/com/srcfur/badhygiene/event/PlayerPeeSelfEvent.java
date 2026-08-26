package com.srcfur.badhygiene.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/// Cancel to not spawn urine puddle!
public class PlayerPeeSelfEvent extends AbstractHygieneEvent implements ICancellableEvent {
    public PlayerPeeSelfEvent(Player player) {
        super(player);
    }
}

package com.srcfur.badhygiene.event;

import com.srcfur.badhygiene.api.NeoForgeHygienePlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/// Cancel to prevent other / default self soiling logic!
public class PlayerSoilSelfEvent extends AbstractHygieneEvent implements ICancellableEvent {
    public PlayerSoilSelfEvent(Player player) {
        super(player);
    }
}

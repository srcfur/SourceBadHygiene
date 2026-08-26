package com.srcfur.badhygiene.event;

import com.srcfur.badhygiene.api.NeoForgeHygienePlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public abstract class AbstractHygieneEvent extends PlayerEvent {
    public AbstractHygieneEvent(Player player) {
        super(player);
    }
    public NeoForgeHygienePlayer getHygiene(){
        return new NeoForgeHygienePlayer(getEntity());
    }
}

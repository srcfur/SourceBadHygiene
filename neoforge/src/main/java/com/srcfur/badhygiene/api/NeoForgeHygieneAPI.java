package com.srcfur.badhygiene.api;

import com.srcfur.badhygiene.event.PlayerMakePuddleEvent;
import com.srcfur.badhygiene.event.PlayerPeeSelfEvent;
import com.srcfur.badhygiene.event.PlayerSoilSelfEvent;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.NeoForge;

public class NeoForgeHygieneAPI extends HygieneAPI{
    @Override
    public AbstractHygienePlayer getHygienePlayer(Player plr) { return new NeoForgeHygienePlayer(plr); }

    @Override
    public boolean onPlayerPeedSelf(Player plr) {
        return NeoForge.EVENT_BUS.post(new PlayerPeeSelfEvent(plr)).isCanceled();
    }

    @Override
    public boolean onPlayerPoopedSelf(Player plr) {
        return NeoForge.EVENT_BUS.post(new PlayerSoilSelfEvent(plr)).isCanceled();

    }

    @Override
    public boolean onPlayerMakePuddle(Player plr) {
        return NeoForge.EVENT_BUS.post(new PlayerMakePuddleEvent(plr)).isCanceled();
    }
}

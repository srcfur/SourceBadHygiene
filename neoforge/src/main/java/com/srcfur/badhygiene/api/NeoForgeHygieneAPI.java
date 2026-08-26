package com.srcfur.badhygiene.api;

import com.srcfur.badhygiene.event.PlayerPeeSelfEvent;
import com.srcfur.badhygiene.event.PlayerSoilSelfEvent;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.NeoForge;

public class NeoForgeHygieneAPI extends HygieneAPI{
    @Override
    public AbstractHygienePlayer getHygienePlayer(Player plr) { return new NeoForgeHygienePlayer(plr); }

    @Override
    public void onPlayerPeedSelf(Player plr) {
        if(NeoForge.EVENT_BUS.post(new PlayerPeeSelfEvent(plr)).isCanceled()) return;

    }

    @Override
    public void onPlayerPoopedSelf(Player plr) {
        if(NeoForge.EVENT_BUS.post(new PlayerSoilSelfEvent(plr)).isCanceled()) return;

    }
}

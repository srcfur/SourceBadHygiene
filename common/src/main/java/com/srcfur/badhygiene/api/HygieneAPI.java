package com.srcfur.badhygiene.api;

import com.srcfur.badhygiene.platform.Services;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.NotImplementedException;

public abstract class HygieneAPI {
    /// Used to very abstractly reference the hygiene player!
    public abstract AbstractHygienePlayer getHygienePlayer(Player plr);
    public abstract void onPlayerPeedSelf(Player plr);
    public abstract void onPlayerPoopedSelf(Player plr);

    public void ServerTick(ServerPlayer plr) {
        AbstractHygienePlayer hygienePlayer = getHygienePlayer(plr);
        if(hygienePlayer.getBladder() >= hygienePlayer.getCalculatedContinence()){
            onPlayerPeedSelf(plr);
            hygienePlayer.setBladder(0);
        }
        if(hygienePlayer.getBowels() >= 40){
            onPlayerPoopedSelf(plr);
            hygienePlayer.setBowels(0);
        }
    }
}

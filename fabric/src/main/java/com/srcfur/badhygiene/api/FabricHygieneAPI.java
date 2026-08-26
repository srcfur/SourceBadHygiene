package com.srcfur.badhygiene.api;

import net.minecraft.world.entity.player.Player;

public class FabricHygieneAPI extends HygieneAPI {
    @Override
    public AbstractHygienePlayer getHygienePlayer(Player plr) {
        return new FabricHygienePlayer(plr);
    }

    @Override
    public void onPlayerPeedSelf(Player plr) {

    }

    @Override
    public void onPlayerPoopedSelf(Player plr) {

    }
}

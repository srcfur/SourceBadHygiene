package com.srcfur.badhygiene.api;

import com.srcfur.badhygiene.event.PlayerMakePuddle;
import com.srcfur.badhygiene.event.PlayerPeeSelf;
import com.srcfur.badhygiene.event.PlayerPoopSelf;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

public class FabricHygieneAPI extends HygieneAPI {
    @Override
    public AbstractHygienePlayer getHygienePlayer(Player plr) {
        return new FabricHygienePlayer(plr);
    }

    @Override
    public boolean onPlayerPeedSelf(Player plr) {
        return PlayerPeeSelf.EVENT.invoker().process(plr) != InteractionResult.PASS;
    }

    @Override
    public boolean onPlayerPoopedSelf(Player plr) {
        return PlayerPoopSelf.EVENT.invoker().process(plr) != InteractionResult.PASS;
    }

    @Override
    public boolean onPlayerMakePuddle(Player plr) {
        return PlayerMakePuddle.EVENT.invoker().process(plr) != InteractionResult.PASS;
    }
}

package com.srcfur.badhygiene.event;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

public interface SimplePlayerEventInterface {
    InteractionResult process(Player plr);
}

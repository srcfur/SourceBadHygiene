package com.srcfur.badhygiene.api;

import com.srcfur.badhygiene.attachment.HygieneAttachments;
import net.minecraft.world.entity.player.Player;

public class FabricHygienePlayer extends AbstractHygienePlayer {
    public FabricHygienePlayer(Player player) {
        super(player);
    }

    @Override
    public int getBladder() {
        return getPlayer().getAttachedOrGet(HygieneAttachments.Bladder, ()->0);
    }

    @Override
    public int getBowels() {
        return 0;
    }

    @Override
    public int getContinence() {
        return 0;
    }

    @Override
    public int getCleanliness() {
        return 0;
    }

    @Override
    public void setBladder(int bladder) {
        getPlayer().setAttached(HygieneAttachments.Bladder, bladder);
    }

    @Override
    public void setBowels(int bowels) {

    }

    @Override
    public void setContinence(int continence) {

    }

    @Override
    public void setCleanliness(int cleanliness) {

    }

    @Override
    public int getCalculatedContinence() {
        return 0;
    }
}

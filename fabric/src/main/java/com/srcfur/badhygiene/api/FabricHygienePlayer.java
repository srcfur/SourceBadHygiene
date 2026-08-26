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
        return getPlayer().getAttachedOrGet(HygieneAttachments.Bowels, ()->0);
    }

    @Override
    public int getContinence() {
        return getPlayer().getAttachedOrGet(HygieneAttachments.Continence, ()->100);
    }

    @Override
    public int getCleanliness() {
        return getPlayer().getAttachedOrGet(HygieneAttachments.Cleanliness, ()->100);
    }

    @Override
    public void setBladder(int bladder) {
        getPlayer().setAttached(HygieneAttachments.Bladder, bladder);
    }

    @Override
    public void setBowels(int bowels) {
        getPlayer().setAttached(HygieneAttachments.Bowels, bowels);
    }

    @Override
    public void setContinence(int continence) {
        getPlayer().setAttached(HygieneAttachments.Continence, continence);
    }

    @Override
    public void setCleanliness(int cleanliness) {
        getPlayer().setAttached(HygieneAttachments.Cleanliness, cleanliness);
    }

    @Override
    public int getCalculatedContinence() {
        return 0;
    }
}

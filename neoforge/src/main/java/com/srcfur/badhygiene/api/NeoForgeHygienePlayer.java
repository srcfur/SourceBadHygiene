package com.srcfur.badhygiene.api;

import com.srcfur.badhygiene.attachment.HygieneAttachments;
import net.minecraft.world.entity.player.Player;

public class NeoForgeHygienePlayer extends AbstractHygienePlayer{
    public NeoForgeHygienePlayer(Player player) {
        super(player);
    }

    @Override
    public int getBladder() { return getPlayer().getData(HygieneAttachments.Bladder.get()); }

    @Override
    public int getBowels() { return getPlayer().getData(HygieneAttachments.Bowels.get()); }

    @Override
    public int getContinence() { return getPlayer().getData(HygieneAttachments.Continence.get()); }

    @Override
    public int getCleanliness() { return getPlayer().getData(HygieneAttachments.Cleanliness.get()); }

    @Override
    public void setBladder(int bladder) { getPlayer().setData(HygieneAttachments.Bladder.get(), bladder); }

    @Override
    public void setBowels(int bowels) { getPlayer().setData(HygieneAttachments.Bowels.get(), bowels); }

    @Override
    public void setContinence(int continence) { getPlayer().setData(HygieneAttachments.Continence.get(), continence); }

    @Override
    public void setCleanliness(int cleanliness) { getPlayer().setData(HygieneAttachments.Cleanliness.get(), cleanliness); }
}

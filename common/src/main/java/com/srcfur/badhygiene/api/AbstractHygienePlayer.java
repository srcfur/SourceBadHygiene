package com.srcfur.badhygiene.api;

import net.minecraft.world.entity.player.Player;

public abstract class AbstractHygienePlayer {
    private Player plr;
    public AbstractHygienePlayer(Player player) {plr = player;}

    public Player getPlayer() {return plr;}

    public abstract int getBladder();
    public abstract int getBowels();
    public abstract int getContinence();
    public abstract int getCleanliness();

    public abstract void setBladder(int bladder);
    public abstract void setBowels(int bowels);
    public abstract void setContinence(int continence);
    public abstract void setCleanliness(int cleanliness);

    public abstract int getCalculatedContinence();
    public float getBladderFullness(){
        return (float)getBladder() / (float)getCalculatedContinence();
    }
}

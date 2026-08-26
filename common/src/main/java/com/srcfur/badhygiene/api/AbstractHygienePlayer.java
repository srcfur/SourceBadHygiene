package com.srcfur.badhygiene.api;

import com.srcfur.badhygiene.BadHygieneConstants;
import com.srcfur.badhygiene.attribute.BadHygieneAttributes;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public abstract class AbstractHygienePlayer {
    private static final Identifier DIRTY_VULNERABILITY_ID = Identifier.fromNamespaceAndPath(BadHygieneConstants.MOD_ID, "dirty");
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

    public int getCalculatedContinence() {
        return (int)Math.floor(getContinence() * getPlayer().getAttribute(Holder.direct(BadHygieneAttributes.Continence)).getValue());
    }

    public float getBladderFullness(){
        return (float)getBladder() / (float)getCalculatedContinence();
    }
    public void impactHygiene(int score){
        setCleanliness(Math.clamp(getCleanliness() + score, 0, 100));
        double percentage = 1 - (getCleanliness() / 30.0);
        AttributeInstance vuln = getPlayer().getAttribute(Attributes.MAX_HEALTH);
        if(vuln == null) return;
        if(getCleanliness() < 30)
            vuln.addOrReplacePermanentModifier(
                    new AttributeModifier(DIRTY_VULNERABILITY_ID, -0.6 * percentage, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            );
        else
            if(vuln.hasModifier(DIRTY_VULNERABILITY_ID))
                vuln.removeModifier(DIRTY_VULNERABILITY_ID);
    }
}

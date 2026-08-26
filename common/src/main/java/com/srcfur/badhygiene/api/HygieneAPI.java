package com.srcfur.badhygiene.api;

import com.srcfur.badhygiene.block.BadHygieneBlocks;
import com.srcfur.badhygiene.component.BadHygieneDataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public abstract class HygieneAPI {
    /// Used to very abstractly reference the hygiene player!
    public abstract AbstractHygienePlayer getHygienePlayer(Player plr);
    /// Return true to override default behaviour
    public abstract boolean onPlayerPeedSelf(Player plr);
    /// Return true to override default behaviour
    public abstract boolean onPlayerPoopedSelf(Player plr);
    /// Return true to override default behaviour
    public abstract boolean onPlayerMakePuddle(Player plr);


    public void spawnUrinePuddleOnPlayer(ServerPlayer plr){
        if(onPlayerMakePuddle(plr)) return;
        if(plr.level().getBlockState(plr.blockPosition()).getBlock() != Blocks.AIR) return;
        plr.level().setBlockAndUpdate(plr.blockPosition(), BadHygieneBlocks.UrinePuddle.defaultBlockState());
    }
    public void playerSoilArmor(ServerPlayer plr){
        Inventory inv = plr.getInventory();
        List<ItemStack> armor = List.of(inv.getItem(100),inv.getItem(101),inv.getItem(102),inv.getItem(103));
        ArrayList<ItemStack> validSoilable = new ArrayList<>();
        for(ItemStack stack : armor){
            if(stack.isEmpty()) continue;
            if(stack.getOrDefault(BadHygieneDataComponents.Soiled, false)) continue;
            validSoilable.add(stack);
        }
        while(!validSoilable.isEmpty()){
            ItemStack stack = validSoilable.get(plr.level().getRandom().nextInt(validSoilable.size()));
            validSoilable.remove(stack);
            stack.set(BadHygieneDataComponents.Soiled, true);
            if(plr.level().getDifficulty() != Difficulty.HARD) break;
        }
    }
    public void serverTick(ServerPlayer plr) {
        AbstractHygienePlayer hygienePlayer = getHygienePlayer(plr);
        if(hygienePlayer.getBladder() >= hygienePlayer.getCalculatedContinence()){
            if(!onPlayerPeedSelf(plr))
                spawnUrinePuddleOnPlayer(plr);
            hygienePlayer.setBladder(0);
        }
        if(hygienePlayer.getBowels() >= 40){
            if(!onPlayerPoopedSelf(plr))
                playerSoilArmor(plr);
            hygienePlayer.setBowels(0);
        }
        if(plr.level().getBlockState(plr.blockPosition()).getBlock() == BadHygieneBlocks.UrinePuddle && plr.level().getGameTime() % 10 == 0)
            hygienePlayer.impactHygiene(-1);
        if(plr.isInWaterOrRain())
            hygienePlayer.impactHygiene(1);
    }
}

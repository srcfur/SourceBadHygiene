package com.srcfur.badhygiene;

import com.srcfur.badhygiene.attachment.HygieneAttachments;
import com.srcfur.badhygiene.attribute.BadHygieneAttributes;
import com.srcfur.badhygiene.attribute.BadHygieneAttributesFabric;
import com.srcfur.badhygiene.block.BadHygieneBlocksFabric;
import com.srcfur.badhygiene.block.entity.BadHygieneBlockEntitiesFabric;
import com.srcfur.badhygiene.component.BadHygieneDataComponentsFabric;
import com.srcfur.badhygiene.effect.BadHygieneMobEffectsFabric;
import com.srcfur.badhygiene.item.BadHygieneItemsFabric;
import com.srcfur.badhygiene.menu.BadHygieneMenusFabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.world.entity.player.Player;

public class BadHygiene implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        BadHygieneConstants.LOG.info("Hello Fabric world!");
        BadHygieneCommon.init();
        HygieneAttachments.init();

        //Fabric is objectively bad, and registration can happen just when the hell ever... so yeah...
        BadHygieneBlocksFabric.RegisterBlocks();
        BadHygieneBlockEntitiesFabric.RegisterBlockEntities();
        BadHygieneItemsFabric.RegisterItems();
        BadHygieneMenusFabric.RegisterMenus();
        BadHygieneDataComponentsFabric.RegisterDataComponents();

        //BadHygieneAttributesFabric.RegisterAttributes(); Is actually called in the PLAYER MIXIN WOOHOO (attributes need to exist for the mixin, and fabric sucks >:()
        BadHygieneMobEffectsFabric.RegisterMobEffects();
        ServerTickEvents.START_SERVER_TICK.register(BadHygiene::ServerTick);
    }
    public static void ServerTick(MinecraftServer server){
        for(ServerPlayer plr : server.getPlayerList().getPlayers()){
            BadHygieneCommon.API.serverTick(plr);
        }
    }
}

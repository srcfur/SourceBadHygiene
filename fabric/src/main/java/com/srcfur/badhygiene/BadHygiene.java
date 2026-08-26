package com.srcfur.badhygiene;

import com.srcfur.badhygiene.attachment.HygieneAttachments;
import com.srcfur.badhygiene.block.BadHygieneBlocksFabric;
import com.srcfur.badhygiene.block.entity.BadHygieneBlockEntitiesFabric;
import com.srcfur.badhygiene.component.BadHygieneDataComponentsFabric;
import com.srcfur.badhygiene.item.BadHygieneItemsFabric;
import com.srcfur.badhygiene.menu.BadHygieneMenusFabric;
import net.fabricmc.api.ModInitializer;

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
    }
}

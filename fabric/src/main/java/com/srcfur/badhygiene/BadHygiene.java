package com.srcfur.badhygiene;

import com.srcfur.badhygiene.attachment.HygieneAttachments;
import com.srcfur.badhygiene.item.BadHygieneItems;
import net.fabricmc.api.ModInitializer;

public class BadHygiene implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();
        HygieneAttachments.init();
        //Fabric is objectively bad, and registration can happen just when the hell ever... so yeah...
        BadHygieneItems.Register();
    }
}

package com.srcfur.badhygiene.attachment;

import com.mojang.serialization.Codec;
import com.srcfur.badhygiene.BadHygieneConstants;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@Mod(BadHygieneConstants.MOD_ID)
public class HygieneAttachments {
    public HygieneAttachments(IEventBus modBus){
        ATTACHMENT_TYPES.register(modBus);
    }
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, BadHygieneConstants.MOD_ID);
    public static final Supplier<AttachmentType<Integer>> Bladder = ATTACHMENT_TYPES.register("bladder",
            ()-> AttachmentType.<Integer>builder(()->0).sync(ByteBufCodecs.INT).serialize(Codec.INT.fieldOf("bladder")).build());
    public static final Supplier<AttachmentType<Integer>> Bowels = ATTACHMENT_TYPES.register("bowels",
            ()-> AttachmentType.<Integer>builder(()->0).sync(ByteBufCodecs.INT).serialize(Codec.INT.fieldOf("bowels")).build());
    public static final Supplier<AttachmentType<Integer>> Cleanliness = ATTACHMENT_TYPES.register("cleanliness",
            ()-> AttachmentType.<Integer>builder(()->100).sync(ByteBufCodecs.INT).serialize(Codec.INT.fieldOf("cleanliness")).build());
    public static final Supplier<AttachmentType<Integer>> Continence = ATTACHMENT_TYPES.register("continence",
            ()-> AttachmentType.<Integer>builder(()->100).sync(ByteBufCodecs.INT).serialize(Codec.INT.fieldOf("continence")).build());
}

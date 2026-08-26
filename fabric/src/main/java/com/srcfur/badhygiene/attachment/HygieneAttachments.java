package com.srcfur.badhygiene.attachment;

import com.mojang.serialization.Codec;
import com.srcfur.badhygiene.Constants;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.core.Registry;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class HygieneAttachments {
    public static final AttachmentType<Integer> Bladder = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(Constants.MOD_ID, "bladder"),
            builder -> builder.initializer(()->0).persistent(Codec.INT).syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all()));
    public static final AttachmentType<Integer> Bowels = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(Constants.MOD_ID, "bowels"),
            builder -> builder.initializer(()->0).persistent(Codec.INT).syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all()));
    public static final AttachmentType<Integer> Continence = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(Constants.MOD_ID, "continence"),
            builder->builder.initializer(()->100).persistent(Codec.INT).syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all()));
    public static final AttachmentType<Integer> Cleanliness = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(Constants.MOD_ID, "cleanliness"),
            builder -> builder.initializer(()->100).persistent(Codec.INT).syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all()));

    public static void init(){

    }
}

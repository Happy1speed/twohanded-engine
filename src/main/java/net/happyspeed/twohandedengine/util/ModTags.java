package net.happyspeed.twohandedengine.util;

import net.happyspeed.twohandedengine.Twohandedengine;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> TWOHANDED = TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Twohandedengine.MODID, "twohanded")
        );

        public static final TagKey<Item> TWOHANDEDALLOWED = TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Twohandedengine.MODID, "twohanded_allowed")
        );

        public static final TagKey<Item> CRITICAL_DISABLED = TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Twohandedengine.MODID, "critical_disabled")
        );

        public static final TagKey<Item> CRITICAL_ULTRA_LIGHT = TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Twohandedengine.MODID, "critical_ultra_light")
        );

        public static final TagKey<Item> CRITICAL_LIGHT = TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Twohandedengine.MODID, "critical_light")
        );

        public static final TagKey<Item> CRITICAL_HEAVY = TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Twohandedengine.MODID, "critical_heavy")
        );

        public static final TagKey<Item> CRITICAL_ULTRA_HEAVY = TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Twohandedengine.MODID, "critical_ultra_heavy")
        );
    }
}



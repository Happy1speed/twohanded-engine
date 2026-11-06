package net.happyspeed.twohandedengine.util;

import net.happyspeed.twohandedengine.Twohandedengine;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public final class TwohandedEngineEnchantments {

    public static final ResourceKey<Enchantment> LIGHTWEIGHT = key("lightweight");

    private TwohandedEngineEnchantments() {}

    private static ResourceKey<Enchantment> key(String path) {
        return ResourceKey.create(Registries.ENCHANTMENT,
                ResourceLocation.fromNamespaceAndPath(Twohandedengine.MODID, path));
    }
}

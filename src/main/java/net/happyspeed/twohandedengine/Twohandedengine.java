package net.happyspeed.twohandedengine;

import net.happyspeed.twohandedengine.util.ModTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.entity.living.LivingSwapItemsEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Twohandedengine.MODID)
public class Twohandedengine {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "twohandedengine";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Twohandedengine(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading

        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.addListener(Twohandedengine::critAmbush);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("Two-handed weapons are being initialized");

    }


    private static void critAmbush(CriticalHitEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getMainHandItem().is(ModTags.Items.CRITICAL_ULTRA_LIGHT)) {
                event.setDamageMultiplier(1.2f);
            }
            else if (player.getMainHandItem().is(ModTags.Items.CRITICAL_LIGHT)) {
                event.setDamageMultiplier(1.35f);
            }
            else if (player.getMainHandItem().is(ModTags.Items.CRITICAL_HEAVY)) {
                event.setDamageMultiplier(1.65f);
            }
            else if (player.getMainHandItem().is(ModTags.Items.CRITICAL_ULTRA_HEAVY)) {
                event.setDamageMultiplier(1.8f);
            }
            else if (player.getMainHandItem().is(ModTags.Items.CRITICAL_DISABLED)) {
                event.setCriticalHit(false);
            }
        }
    }

}

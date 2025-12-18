package net.happyspeed.twohandedengine.client;

import com.google.common.collect.Multimap;
import net.happyspeed.twohandedengine.Twohandedengine;
import net.happyspeed.twohandedengine.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Twohandedengine.MODID, value = Dist.CLIENT)
public class TooltipHandler {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        if (stack.is(ModTags.Items.TWOHANDED)) {
            event.getToolTip().add(Component.translatable("tooltips.twohanded").withStyle(ChatFormatting.DARK_GRAY));
        }
        if (stack.is(ModTags.Items.TWOHANDEDALLOWED)) {
            event.getToolTip().add(Component.translatable("tooltips.twohanded_allowed").withStyle(ChatFormatting.DARK_GRAY));
        }
        if (stack.is(ModTags.Items.CRITICAL_DISABLED)) {
            event.getToolTip().add(Component.translatable("tooltips.crit_disabled").withStyle(ChatFormatting.DARK_RED));
        }
        else if (stack.is(ModTags.Items.CRITICAL_ULTRA_LIGHT)) {
            event.getToolTip().add(Component.translatable("tooltips.crit_ultra_light").withStyle(ChatFormatting.RED));
        }
        else if (stack.is(ModTags.Items.CRITICAL_LIGHT)) {
            event.getToolTip().add(Component.translatable("tooltips.crit_light").withStyle(ChatFormatting.GOLD));
        }
        else if (stack.is(ModTags.Items.CRITICAL_HEAVY)) {
            event.getToolTip().add(Component.translatable("tooltips.crit_heavy").withStyle(ChatFormatting.GREEN));
        }
        else if (stack.is(ModTags.Items.CRITICAL_ULTRA_HEAVY)) {
            event.getToolTip().add(Component.translatable("tooltips.crit_ultra_heavy").withStyle(ChatFormatting.DARK_GREEN));
        }
        else {
            ItemAttributeModifiers modifiers = stack.getAttributeModifiers();
            for (ItemAttributeModifiers.Entry modifiers1 : modifiers.modifiers()) {
                if (modifiers1.attribute() == Attributes.ATTACK_DAMAGE) {
                    event.getToolTip().add(Component.translatable("tooltips.crit_standard").withStyle(ChatFormatting.YELLOW));
                }
            }
        }


    }
}

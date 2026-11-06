package net.happyspeed.twohandedengine.mixin;


import net.happyspeed.twohandedengine.Twohandedengine;
import net.happyspeed.twohandedengine.util.ModTags;
import net.happyspeed.twohandedengine.util.TwohandedEngineEnchantments;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.extensions.IPlayerExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Supplier;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private static int getLevel(Level level, ItemStack stack, ResourceKey<Enchantment> key) {
        if (stack.isEmpty()) return 0;
        Holder<Enchantment> holder = level.registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolder(key)
                .orElse(null);
        if (holder == null) return 0;
        ItemEnchantments enchantments = stack.getEnchantments();
        return enchantments.getLevel(holder);
    }

    @Shadow
    Inventory inventory;

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slot1) {

        if (slot1 == EquipmentSlot.MAINHAND) {
            return this.inventory.getSelected();
        } else if (slot1 == EquipmentSlot.OFFHAND) {
            if ((this.getMainHandItem().is(ModTags.Items.TWOHANDED) && getLevel(this.level(), this.getMainHandItem(), TwohandedEngineEnchantments.LIGHTWEIGHT) < 1)
                    && !this.inventory.offhand.get(0).is(ModTags.Items.TWOHANDEDALLOWED)) {
                return new ItemStack(Items.AIR);
            }
            return (ItemStack)this.inventory.offhand.get(0);
        } else {
            return slot1.getType() == EquipmentSlot.Type.HUMANOID_ARMOR ? (ItemStack)this.inventory.armor.get(slot1.getIndex()) : ItemStack.EMPTY;
        }
    }
}

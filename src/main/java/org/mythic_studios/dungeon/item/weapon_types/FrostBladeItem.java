package org.mythic_studios.dungeon.item.weapon_types;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class FrostBladeItem extends SwordItem {
    public final String Rarity;
    public final int StatusEffectLength;

    public FrostBladeItem(ToolMaterial toolMaterial, Settings settings, String rarity, int statusEffectLength) {
        super(toolMaterial, settings);
        Rarity = rarity;
        StatusEffectLength = statusEffectLength;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            // Apply freezing effect to the target
            applyFreezingEffect(target);
        }
        return super.postHit(stack, target, attacker);
    }

    private void applyFreezingEffect(LivingEntity target) {
        // Set the frozen ticks to simulate powdered snow freezing
        target.setFrozenTicks(Math.max(target.getFrozenTicks(), StatusEffectLength * 2));

        // Optional: Add visual/audio effects
        if (!target.getWorld().isClient()) {
            // Spawn snowflake particles around the target
            target.getWorld().addParticle(
                    net.minecraft.particle.ParticleTypes.SNOWFLAKE,
                    target.getX() + (target.getRandom().nextDouble() - 0.5) * target.getWidth(),
                    target.getY() + target.getRandom().nextDouble() * target.getHeight(),
                    target.getZ() + (target.getRandom().nextDouble() - 0.5) * target.getWidth(),
                    0.0, 0.1, 0.0
            );

            // Spawn multiple particles for better effect
            for (int i = 0; i < 5; i++) {
                target.getWorld().addParticle(
                        net.minecraft.particle.ParticleTypes.SNOWFLAKE,
                        target.getX() + (target.getRandom().nextDouble() - 0.5) * target.getWidth() * 2,
                        target.getY() + target.getRandom().nextDouble() * target.getHeight(),
                        target.getZ() + (target.getRandom().nextDouble() - 0.5) * target.getWidth() * 2,
                        (target.getRandom().nextDouble() - 0.5) * 0.1,
                        target.getRandom().nextDouble() * 0.1,
                        (target.getRandom().nextDouble() - 0.5) * 0.1
                );
            }
        }
    }



    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        tooltip.add(Text.literal(" "));

        if (Screen.hasShiftDown()) {
            if (StatusEffectLength == 20) {
                tooltip.add(Text.translatable("tooltip.duration.20"));
            } else if (StatusEffectLength == 40) {
                tooltip.add(Text.translatable("tooltip.duration.40"));
            } else if (StatusEffectLength == 60) {
                tooltip.add(Text.translatable("tooltip.duration.60"));
            } else if (StatusEffectLength == 80) {
                tooltip.add(Text.translatable("tooltip.duration.80"));
            } else if (StatusEffectLength == 100) {
                tooltip.add(Text.translatable("tooltip.duration.100"));
            } else if (StatusEffectLength == 120) {
                tooltip.add(Text.translatable("tooltip.duration.120"));
            } else if (StatusEffectLength == 140) {
                tooltip.add(Text.translatable("tooltip.duration.140"));
            }
        }
        else {
            tooltip.add(Text.translatable("tooltip.shift.ability"));
        }

        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.frost_ability.1"));
        tooltip.add(Text.translatable("tooltip.frost_ability.2"));


        super.appendTooltip(stack, context, tooltip, type);
    }
}
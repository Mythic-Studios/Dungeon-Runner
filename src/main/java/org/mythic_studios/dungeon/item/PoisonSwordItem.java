package org.mythic_studios.dungeon.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class PoisonSwordItem extends SwordItem {
    public final String Rarity;
    public final int StatusEffectLength;
    public final int COOLDOWN_TICKS;

    public PoisonSwordItem(ToolMaterial toolMaterial, Settings settings, String rarity, int statusEffectLength, int cooldownTicks) {
        super(toolMaterial, settings);
        Rarity = rarity;
        StatusEffectLength = statusEffectLength;
        COOLDOWN_TICKS = cooldownTicks;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {


        target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, StatusEffectLength, 5, false, false), attacker);

        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            // Area around player to apply poison
            double radius = 10.0;
            Box area = new Box(user.getX() - radius, user.getY() - radius, user.getZ() - radius,
                    user.getX() + radius, user.getY() + radius, user.getZ() + radius);

            List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, area,
                    entity -> entity != user && entity.isAlive() && user.canSee(entity));

            for (LivingEntity entity : entities) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, StatusEffectLength, 2));

                // Spawn particles around each poisoned entity
                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.HAPPY_VILLAGER,
                        entity.getX(), entity.getBodyY(0.5), entity.getZ(),
                        100, // count
                        0.5, 0.5, 0.5, // offset
                        1 // speed

                );
            }

            // Set cooldown
            user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
        }

        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        tooltip.add(Text.literal(" "));

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
        } else if (StatusEffectLength == 140) {
            tooltip.add(Text.translatable("tooltip.duration.140"));
        }

        if (COOLDOWN_TICKS == 20) {
            tooltip.add(Text.translatable("tooltip.cooldown.20"));
        } else if (COOLDOWN_TICKS == 40) {
            tooltip.add(Text.translatable("tooltip.cooldown.40"));
        } else if (COOLDOWN_TICKS == 60) {
            tooltip.add(Text.translatable("tooltip.cooldown.60"));
        } else if (COOLDOWN_TICKS == 100) {
            tooltip.add(Text.translatable("tooltip.cooldown.100"));
        } else if (COOLDOWN_TICKS == 80) {
            tooltip.add(Text.translatable("tooltip.cooldown.80"));
        } else if (COOLDOWN_TICKS == 160) {
            tooltip.add(Text.translatable("tooltip.cooldown.160"));
        }

        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.poison_ability.1"));
        tooltip.add(Text.translatable("tooltip.poison_ability.2"));

        super.appendTooltip(stack, context, tooltip, type);
    }
}

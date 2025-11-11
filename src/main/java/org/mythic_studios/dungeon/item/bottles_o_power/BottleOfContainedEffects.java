package org.mythic_studios.dungeon.item.bottles_o_power;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class BottleOfContainedEffects extends Item {
    private final String effect;

    public BottleOfContainedEffects(Settings settings, String effect) {
        super(settings);
        this.effect = effect;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (Objects.equals(effect, "Speed_1")) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2000, 0, false, false, false));
        } else if (Objects.equals(effect, "Speed_2")) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2000, 3, false, false, false));
        } else if (Objects.equals(effect, "Speed_3")) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2000, 7, false, false, false));
        }

        // A cooldown of 2400 ticks (120 seconds)
        user.getItemCooldownManager().set(this, 2400);

        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        if (Screen.hasShiftDown()) {
            if (Objects.equals(effect, "Speed_1")) {
                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.bottle.speed.1"));
            } else if (Objects.equals(effect, "Speed_2")) {
                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.bottle.speed.2"));
            } else if (Objects.equals(effect, "Speed_3")) {
                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.bottle.speed.3"));
            }
        } else {
            tooltip.add(Text.literal(" "));
            tooltip.add(Text.translatable("tooltip.shift"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }


}
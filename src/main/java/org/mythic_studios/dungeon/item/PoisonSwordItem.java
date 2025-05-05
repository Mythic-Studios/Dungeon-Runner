package org.mythic_studios.dungeon.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class PoisonSwordItem extends SwordItem {
    public final String Rarity;

    public PoisonSwordItem(ToolMaterial toolMaterial, Settings settings, String rarity) {
        super(toolMaterial, settings);
        Rarity = rarity;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20, 5, true, false), attacker);

        return super.postHit(stack, target, attacker);
    }
}

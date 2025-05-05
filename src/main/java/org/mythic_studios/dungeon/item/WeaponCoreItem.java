package org.mythic_studios.dungeon.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class WeaponCoreItem extends Item {
    public final String rarity;

    public WeaponCoreItem(Settings settings, String rarity) {
        super(settings);
        this.rarity = rarity;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        switch (rarity) {
            case "Common" -> {
                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.rarity.common"));
            }
            case "Uncommon" -> {
                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.rarity.uncommon"));
            }
            case "Rare" -> {
                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.rarity.rare"));
            }
            case "Epic" -> {
                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.rarity.epic"));
            }
            case "Legendary" -> {
                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.rarity.legendary"));
            }
            case "Mythical" -> {
                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.rarity.mythical"));
            }
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}

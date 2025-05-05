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

        if (rarity.equals("Common")){
            tooltip.add(Text.literal(" "));
            tooltip.add(Text.translatable("tooltip.rarity.common"));
        } else if (rarity.equals("Uncommon")){
            tooltip.add(Text.literal(" "));
            tooltip.add(Text.translatable("tooltip.rarity.uncommon"));
        } else if (rarity.equals("Rare")){
            tooltip.add(Text.literal(" "));
            tooltip.add(Text.translatable("tooltip.rarity.rare"));
        } else if (rarity.equals("Epic")){
            tooltip.add(Text.literal(" "));
            tooltip.add(Text.translatable("tooltip.rarity.epic"));
        } else if (rarity.equals("Legendary")){
            tooltip.add(Text.literal(" "));
            tooltip.add(Text.translatable("tooltip.rarity.legendary"));
        } else if (rarity.equals("Mythical")){
            tooltip.add(Text.literal(" "));
            tooltip.add(Text.translatable("tooltip.rarity.mythical"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}

package org.mythic_studios.dungeon.item;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Objects;

public class SketchedPaperItem extends Item {
    private final String sketchImage;

    public SketchedPaperItem(Settings settings, String sketchImage) {
        super(settings);
        this.sketchImage = sketchImage;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal(" "));

        if (Screen.hasShiftDown()) {
            switch (sketchImage) {
                case "Blade" -> tooltip.add(Text.translatable("tooltip.sketch.blade"));
                case "Bow" -> tooltip.add(Text.translatable("tooltip.sketch.bow"));
                case "Hammer" -> tooltip.add(Text.translatable("tooltip.sketch.hammer"));
                case null, default -> tooltip.add(Text.translatable("tooltip.weapon_exist.false"));
            }
        }
        else {
            tooltip.add(Text.translatable("tooltip.shift"));
        }

        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.no_sketch"));

        super.appendTooltip(stack, context, tooltip, type);
    }
}

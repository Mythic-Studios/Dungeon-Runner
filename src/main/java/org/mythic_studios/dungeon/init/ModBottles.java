package org.mythic_studios.dungeon.init;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.item.bottles_o_power.BottleOfContainedEffects;

public class ModBottles {

    public static Item BOTTLE_WATER;
    public static Item BOTTLE_SPEED_SIMPLE;
    public static Item BOTTLE_SPEED_ADVANCED;
    public static Item BOTTLE_SPEED_POWERFUL;


    public static void init() {

        BOTTLE_WATER = createItem("effect_bottles/water_bottle",
                new Item(new Item.Settings().maxCount(16)));

        BOTTLE_SPEED_SIMPLE = createItem("effect_bottles/speed_simple",
                new BottleOfContainedEffects(new Item.Settings().maxCount(1), "Speed_1"));

        BOTTLE_SPEED_ADVANCED = createItem("effect_bottles/speed_advanced",
                new BottleOfContainedEffects(new Item.Settings().maxCount(1), "Speed_2"));

        BOTTLE_SPEED_POWERFUL = createItem("effect_bottles/speed_powerful",
                new BottleOfContainedEffects(new Item.Settings().maxCount(1), "Speed_3"));
    }

    private static Item createItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonRunner.MOD_ID, name), item);
    }
}

package org.mythic_studios.dungeon.init.hammer_types;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.item.ModToolMaterials;
import org.mythic_studios.dungeon.item.hammer_types.HammerOfPoison;

public class ModPoisonHammerItems {



    public static Item POISON_HAMMER_COMMON;
    public static Item POISON_HAMMER_UNCOMMON;
    public static Item POISON_HAMMER_RARE;
    public static Item POISON_HAMMER_EPIC;
    public static Item POISON_HAMMER_LEGENDARY;
    public static Item POISON_HAMMER_MYTHICAL;

    public static void init() {

        POISON_HAMMER_COMMON = createItem("poison_hammer/poison_hammer_common",
                new HammerOfPoison(new Item.Settings().maxCount(1), 20));

        POISON_HAMMER_UNCOMMON = createItem("poison_hammer/poison_hammer_uncommon",
                new HammerOfPoison(new Item.Settings().maxCount(1), 40));

        POISON_HAMMER_RARE = createItem("poison_hammer/poison_hammer_rare",
                new HammerOfPoison(new Item.Settings().maxCount(1), 60));

        POISON_HAMMER_EPIC = createItem("poison_hammer/poison_hammer_epic",
                new HammerOfPoison(new Item.Settings().maxCount(1), 80));

        POISON_HAMMER_LEGENDARY = createItem("poison_hammer/poison_hammer_legendary",
                new HammerOfPoison(new Item.Settings().maxCount(1), 100));

        POISON_HAMMER_MYTHICAL = createItem("poison_hammer/poison_hammer_mythical",
                new HammerOfPoison(new Item.Settings().maxCount(1), 140));


    }


    private static Item createItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonRunner.MOD_ID, name), item);
    }
}

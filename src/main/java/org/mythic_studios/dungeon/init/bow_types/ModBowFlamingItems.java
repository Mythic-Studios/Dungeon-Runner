package org.mythic_studios.dungeon.init.bow_types;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.item.bow_types.FlamingBow;

public class ModBowFlamingItems {


    public static Item BOW_FLAMING_COMMON;
    public static Item BOW_FLAMING_UNCOMMON;
    public static Item BOW_FLAMING_RARE;
    public static Item BOW_FLAMING_EPIC;
    public static Item BOW_FLAMING_LEGENDARY;
    public static Item BOW_FLAMING_MYTHICAL;

    public static void init() {

        BOW_FLAMING_COMMON = createItem("bow_flaming/bow_flaming_common",
                new FlamingBow(new Item.Settings().maxCount(1).maxDamage(5),"Common", 20));

        BOW_FLAMING_UNCOMMON = createItem("bow_flaming/bow_flaming_uncommon",
                new FlamingBow(new Item.Settings().maxCount(1).maxDamage(10),"Uncommon", 40));

        BOW_FLAMING_RARE = createItem("bow_flaming/bow_flaming_rare",
                new FlamingBow(new Item.Settings().maxCount(1).maxDamage(15),"Rare", 60));

        BOW_FLAMING_EPIC = createItem("bow_flaming/bow_flaming_epic",
                new FlamingBow(new Item.Settings().maxCount(1).maxDamage(20),"Epic", 80));

        BOW_FLAMING_LEGENDARY = createItem("bow_flaming/bow_flaming_legendary",
                new FlamingBow(new Item.Settings().maxCount(1).maxDamage(25),"Legendary", 100));

        BOW_FLAMING_MYTHICAL = createItem("bow_flaming/bow_flaming_mythical",
                new FlamingBow(new Item.Settings().maxCount(1).maxDamage(45),"Mythical", 140));

    }


    private static Item createItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonRunner.MOD_ID, name), item);
    }
}

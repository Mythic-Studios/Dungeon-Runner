package org.mythic_studios.dungeon.init.weapon_types;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.item.ModToolMaterials;
import org.mythic_studios.dungeon.item.weapon_types.FrostBladeItem;

public class ModFrostSwordItems {



    public static Item FROST_BLADE_COMMON;
    public static Item FROST_BLADE_UNCOMMON;
    public static Item FROST_BLADE_RARE;
    public static Item FROST_BLADE_EPIC;
    public static Item FROST_BLADE_LEGENDARY;
    public static Item FROST_BLADE_MYTHICAL;

    public static void init() {

        FROST_BLADE_COMMON = createItem("frost/frost_blade_common",
                new FrostBladeItem(ModToolMaterials.BLADE,
                        new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLADE, 1,
                                -3.4f)),"Common", 20));

        FROST_BLADE_UNCOMMON = createItem("frost/frost_blade_uncommon",
                new FrostBladeItem(ModToolMaterials.BLADE,
                        new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLADE, 4,
                                -3.4f)),"Uncommon", 40));

        FROST_BLADE_RARE = createItem("frost/frost_blade_rare",
                new FrostBladeItem(ModToolMaterials.BLADE,
                        new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLADE, 5,
                                -2.4f)),"Rare", 60));

        FROST_BLADE_EPIC = createItem("frost/frost_blade_epic",
                new FrostBladeItem(ModToolMaterials.BLADE,
                        new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLADE, 7,
                                -2.4f)),"Epic", 80));

        FROST_BLADE_LEGENDARY = createItem("frost/frost_blade_legendary",
                new FrostBladeItem(ModToolMaterials.BLADE,
                        new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLADE, 8,
                                -1.4f)),"Legendary", 100));

        FROST_BLADE_MYTHICAL = createItem("frost/frost_blade_mythical",
                new FrostBladeItem(ModToolMaterials.BLADE,
                        new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLADE, 10,
                                -1.0f)),"Mythical", 140));


    }


    private static Item createItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonRunner.MOD_ID, name), item);
    }
}

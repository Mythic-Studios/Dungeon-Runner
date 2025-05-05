package org.mythic_studios.dungeon.init;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.item.ModToolMaterials;
import org.mythic_studios.dungeon.item.PoisonSwordItem;

public class ModPoisonSwordItems {

    public static Item POISON_TIPPED_BLADE_MOLD;

    public static Item POISON_TIPPED_BLADE_COMMON;
    public static Item POISON_TIPPED_BLADE_UNCOMMON;
    public static Item POISON_TIPPED_BLADE_RARE;
    public static Item POISON_TIPPED_BLADE_EPIC;
    public static Item POISON_TIPPED_BLADE_LEGENDARY;
    public static Item POISON_TIPPED_BLADE_MYTHICAL;

    public static void init() {

        POISON_TIPPED_BLADE_COMMON = createItem("poison_tipped_blade_common",
                new PoisonSwordItem(ModToolMaterials.BLADE,
                        new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLADE, 2,
                                -3.4f)),"Common"));

        POISON_TIPPED_BLADE_UNCOMMON = createItem("poison_tipped_blade_uncommon",
                new PoisonSwordItem(ModToolMaterials.BLADE,
                        new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLADE, 3,
                                -3.4f)),"Uncommon"));

        POISON_TIPPED_BLADE_RARE = createItem("poison_tipped_blade_rare",
                new PoisonSwordItem(ModToolMaterials.BLADE,
                        new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLADE, 4,
                                -2.4f)),"Rare"));

        POISON_TIPPED_BLADE_EPIC = createItem("poison_tipped_blade_epic",
                new PoisonSwordItem(ModToolMaterials.BLADE,
                        new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLADE, 5,
                                -2.4f)),"Epic"));

        POISON_TIPPED_BLADE_LEGENDARY = createItem("poison_tipped_blade_legendary",
                new PoisonSwordItem(ModToolMaterials.BLADE,
                        new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLADE, 5,
                                -1.4f)),"Legendary"));

        POISON_TIPPED_BLADE_MYTHICAL = createItem("poison_tipped_blade_mythical",
                new PoisonSwordItem(ModToolMaterials.BLADE,
                        new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLADE, 7,
                                -1.0f)),"Mythical"));
    }


    private static Item createItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonRunner.MOD_ID, name), item);
    }
}

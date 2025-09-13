package org.mythic_studios.dungeon.init;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.item.DungeonKeyItem;
import org.mythic_studios.dungeon.item.UnpoweredWeaponCoreItem;
import org.mythic_studios.dungeon.item.WeaponCoreItem;

public class ModItems {

    public static Item WEAPON_CORE_UNKNOWN;
    public static Item WEAPON_CORE_COMMON;
    public static Item WEAPON_CORE_UNCOMMON;
    public static Item WEAPON_CORE_RARE;
    public static Item WEAPON_CORE_EPIC;
    public static Item WEAPON_CORE_LEGENDARY;
    public static Item WEAPON_CORE_MYTHICAL;

    public static Item DUNGEON_KEY;
    public static Item SECRET_DUNGEON_KEY;

    public static Item BLADE_MOLD;

    public static Item CRYSTAL_POISON;
    public static Item CRYSTAL_FREEZING;

    public static void init() {

        WEAPON_CORE_UNKNOWN = createItem("weapon_core/weapon_core_unknown", new UnpoweredWeaponCoreItem(new Item.Settings()));

        WEAPON_CORE_COMMON = createItem("weapon_core/weapon_core_common", new WeaponCoreItem(new Item.Settings(), "Common"));
        WEAPON_CORE_UNCOMMON = createItem("weapon_core/weapon_core_uncommon", new WeaponCoreItem(new Item.Settings(), "Uncommon"));
        WEAPON_CORE_RARE = createItem("weapon_core/weapon_core_rare", new WeaponCoreItem(new Item.Settings(), "Rare"));
        WEAPON_CORE_EPIC = createItem("weapon_core/weapon_core_epic", new WeaponCoreItem(new Item.Settings(), "Epic"));
        WEAPON_CORE_LEGENDARY = createItem("weapon_core/weapon_core_legendary", new WeaponCoreItem(new Item.Settings(), "Legendary"));
        WEAPON_CORE_MYTHICAL = createItem("weapon_core/weapon_core_mythical", new WeaponCoreItem(new Item.Settings(), "Mythical"));

        DUNGEON_KEY = createItem("dungeon_key", new DungeonKeyItem(new Item.Settings(), ModBlocks.DUNGEON_KEY_BRICKS, ModBlocks.DUNGEON_BRICKS));
        SECRET_DUNGEON_KEY = createItem("secret_dungeon_key", new DungeonKeyItem(new Item.Settings(), ModBlocks.SECRET_DUNGEON_KEY_BRICKS, ModBlocks.DUNGEON_BRICKS));

        BLADE_MOLD = createItem("blade_mold", new Item(new Item.Settings()));

        CRYSTAL_POISON = createItem("crystals/poisoned_crystal", new Item(new Item.Settings()));
        CRYSTAL_FREEZING = createItem("crystals/frozen_crystal", new Item(new Item.Settings()));
    }


    private static Item createItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonRunner.MOD_ID, name), item);
    }
}

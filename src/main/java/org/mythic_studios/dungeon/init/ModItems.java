package org.mythic_studios.dungeon.init;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
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

    public static void init() {

        WEAPON_CORE_UNKNOWN = createItem("weapon_core_unknown", new UnpoweredWeaponCoreItem(new Item.Settings()));

        WEAPON_CORE_COMMON = createItem("weapon_core_common", new WeaponCoreItem(new Item.Settings(), "Common"));
        WEAPON_CORE_UNCOMMON = createItem("weapon_core_uncommon", new WeaponCoreItem(new Item.Settings(), "Uncommon"));
        WEAPON_CORE_RARE = createItem("weapon_core_rare", new WeaponCoreItem(new Item.Settings(), "Rare"));
        WEAPON_CORE_EPIC = createItem("weapon_core_epic", new WeaponCoreItem(new Item.Settings(), "Epic"));
        WEAPON_CORE_LEGENDARY = createItem("weapon_core_legendary", new WeaponCoreItem(new Item.Settings(), "Legendary"));
        WEAPON_CORE_MYTHICAL = createItem("weapon_core_mythical", new WeaponCoreItem(new Item.Settings(), "Mythical"));

    }


    private static Item createItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonRunner.MOD_ID, name), item);
    }
}

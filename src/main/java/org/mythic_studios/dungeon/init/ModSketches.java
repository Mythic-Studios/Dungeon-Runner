package org.mythic_studios.dungeon.init;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.item.SketchedPaperItem;

public class ModSketches {

    public static Item SWORD_SKETCH;
    public static Item BOW_SKETCH;
    public static Item HAMMER_SKETCH;

    public static void init() {
        SWORD_SKETCH = createItem("blade_sketch", new SketchedPaperItem(new Item.Settings(), "Blade"));
        BOW_SKETCH = createItem("bow_sketch", new SketchedPaperItem(new Item.Settings(), "Bow"));
        HAMMER_SKETCH = createItem("hammer_sketch", new SketchedPaperItem(new Item.Settings(), "Hammer"));

    }

    private static Item createItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonRunner.MOD_ID, name), item);
    }
}

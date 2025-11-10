package org.mythic_studios.dungeon.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.block.BatteryBlock;
import org.mythic_studios.dungeon.block.DungeonLampBlock;
import org.mythic_studios.dungeon.block.UpgradeStationBlock;
import org.mythic_studios.dungeon.block.WeaponForgeBlock;

public class ModBlocks {

    public static final Block DUNGEON_KEY_BRICKS = createBlock("dungeon_key_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).hardness(32f)));

    public static final Block DUNGEON_BRICKS = createBlock("dungeon_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).hardness(32f)));

    public static final Block SECRET_DUNGEON_KEY_BRICKS = createBlock("secret_dungeon_key_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).hardness(32f)));

    public static final Block WEAPON_FORGE = createBlock("weapon_forge",
            new WeaponForgeBlock(AbstractBlock.Settings.copy(Blocks.FURNACE).hardness(32f)));

    public static final Block DUNGEON_LAMP = createBlock("dungeon_lamp",
            new DungeonLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP).hardness(100f).luminance(state -> state.get(DungeonLampBlock.CLICKED) ? 15 : 0)));

    public static final Block UPGRADE_STATION = createBlock("upgrade_station",
            new UpgradeStationBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)));

    public static final Block CHARGED_BATTERY = createBlock("battery/charged_battery",
            new BatteryBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK), true));

    public static final Block UNPOWERED_BATTERY = createBlock("battery/unpowered_battery",
            new BatteryBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK), false));

    private static Block createBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(DungeonRunner.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(DungeonRunner.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void register() {
        DungeonRunner.LOGGER.info("Loading Blocks");
    }
}

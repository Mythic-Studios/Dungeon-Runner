package org.mythic_studios.dungeon.init;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.block.entity.ChemistStationBlockEntity;
import org.mythic_studios.dungeon.block.entity.WeaponForgeBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<WeaponForgeBlockEntity> WEAPON_FORGE_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(DungeonRunner.MOD_ID, "weapon_forge_be"),
                    BlockEntityType.Builder.create(WeaponForgeBlockEntity::new, ModBlocks.WEAPON_FORGE).build(null));

    public static final BlockEntityType<ChemistStationBlockEntity> CHEMIST_STATION_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(DungeonRunner.MOD_ID, "chemist_station_be"),
                    BlockEntityType.Builder.create(ChemistStationBlockEntity::new, ModBlocks.CHEMIST_STATION).build(null));

    public static void registerBlockEntities() {
        DungeonRunner.LOGGER.info("Registering Block Entities for " + DungeonRunner.MOD_ID);
    }
}

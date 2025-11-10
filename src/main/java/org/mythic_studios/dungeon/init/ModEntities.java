package org.mythic_studios.dungeon.init;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.entity.MantisEntity;

public class ModEntities {
    public static final EntityType<MantisEntity> MANTIS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(DungeonRunner.MOD_ID, "mantis"),
            EntityType.Builder.create(MantisEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 2.5f).build());


    public static void registerModEntities() {
        DungeonRunner.LOGGER.info("Registering Mod Entities for " + DungeonRunner.MOD_ID);
    }
}

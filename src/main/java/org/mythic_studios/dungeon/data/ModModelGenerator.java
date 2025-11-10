package org.mythic_studios.dungeon.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.block.DungeonLampBlock;
import org.mythic_studios.dungeon.init.ModBlocks;
import org.mythic_studios.dungeon.init.ModItems;

import java.util.Optional;

public class ModModelGenerator extends FabricModelProvider {
    public ModModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DUNGEON_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DUNGEON_KEY_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SECRET_DUNGEON_KEY_BRICKS);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHARGED_BATTERY);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.UNPOWERED_BATTERY);

        Identifier lampOffIdentifier = TexturedModel.CUBE_ALL.upload(ModBlocks.DUNGEON_LAMP, blockStateModelGenerator.modelCollector);
        Identifier lampOnIdentifier = blockStateModelGenerator.createSubModel(ModBlocks.DUNGEON_LAMP, "_on", Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.DUNGEON_LAMP)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(DungeonLampBlock.CLICKED, lampOnIdentifier, lampOffIdentifier)));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.DUNGEON_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.SECRET_DUNGEON_KEY, Models.GENERATED);

        itemModelGenerator.register(ModItems.BLADE_MOLD, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPTY_MOLD, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOW_MOLD, Models.GENERATED);
        itemModelGenerator.register(ModItems.HAMMER_MOLD, Models.GENERATED);

        itemModelGenerator.register(ModItems.CRYSTAL_POISON, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRYSTAL_FREEZING, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRYSTAL_SUN, Models.GENERATED);

        itemModelGenerator.register(ModItems.WEAPON_CORE_UNKNOWN, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAPON_CORE_COMMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAPON_CORE_UNCOMMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAPON_CORE_RARE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAPON_CORE_EPIC, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAPON_CORE_LEGENDARY, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAPON_CORE_MYTHICAL, Models.GENERATED);

        itemModelGenerator.register(ModItems.MANTIS_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}

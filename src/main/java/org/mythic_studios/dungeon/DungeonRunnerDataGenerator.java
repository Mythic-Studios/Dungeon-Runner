package org.mythic_studios.dungeon;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.mythic_studios.dungeon.data.ModModelGenerator;
import org.mythic_studios.dungeon.data.ModRecipeGenerator;

public class DungeonRunnerDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelGenerator::new);
		pack.addProvider(ModRecipeGenerator::new);

	}
}

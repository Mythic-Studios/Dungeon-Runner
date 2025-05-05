package org.mythic_studios.dungeon;

import net.fabricmc.api.ModInitializer;

import org.mythic_studios.dungeon.init.ModItems;
import org.mythic_studios.dungeon.init.ModPoisonSwordItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonRunner implements ModInitializer {
	public static final String MOD_ID = "dungeon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.init();
		ModPoisonSwordItems.init();

		LOGGER.info("Hello Fabric world!");


	}
}
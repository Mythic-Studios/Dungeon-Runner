package org.mythic_studios.dungeon;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import org.mythic_studios.dungeon.gui.lives.LivesTracker;
import org.mythic_studios.dungeon.init.ConfigRegistry;
import org.mythic_studios.dungeon.init.ModBlocks;
import org.mythic_studios.dungeon.init.ModItems;
import org.mythic_studios.dungeon.init.weapon_types.ModPoisonSwordItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonRunner implements ModInitializer {
	public static final String MOD_ID = "dungeon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.init();
		ModBlocks.register();
		ConfigRegistry.register();
		ModPoisonSwordItems.init();

		LOGGER.info("Hello Fabric world!");

		ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
			if (entity instanceof PlayerEntity player && !player.getWorld().isClient()) {
				LivesTracker.decreaseLives(player);
			}
		});


	}
}
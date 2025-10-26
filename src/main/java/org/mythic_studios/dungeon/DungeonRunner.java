package org.mythic_studios.dungeon;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import org.mythic_studios.dungeon.gui.lives.LivesTracker;
import org.mythic_studios.dungeon.init.*;
import org.mythic_studios.dungeon.init.weapon_types.ModFrostSwordItems;
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
		ModFrostSwordItems.init();
		ModRecipes.registerRecipes();
		ModItemGroups.init();
		ModScreenHandlers.registerScreenHandlers();
		ModBlockEntities.registerBlockEntities();

		ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
			if (entity instanceof PlayerEntity player && !player.getWorld().isClient()) {
				LivesTracker.decreaseLives(player);
			}
		});


	}
}
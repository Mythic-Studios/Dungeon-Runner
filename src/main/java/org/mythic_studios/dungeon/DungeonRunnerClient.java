package org.mythic_studios.dungeon;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.mythic_studios.dungeon.entity.client.MantisModel;
import org.mythic_studios.dungeon.entity.client.MantisRenderer;
import org.mythic_studios.dungeon.gui.health.DungeonHealthRenderer;
import org.mythic_studios.dungeon.gui.lives.LivesHudRenderer;
import org.mythic_studios.dungeon.gui.xp.XpHudRenderer;
import org.mythic_studios.dungeon.init.ModEntities;
import org.mythic_studios.dungeon.init.ModScreenHandlers;
import org.mythic_studios.dungeon.screen.chemistry.ChemistryTableScreen;
import org.mythic_studios.dungeon.screen.forging.WeaponForgingScreen;
import org.mythic_studios.dungeon.util.ModModelPredicates;

public class DungeonRunnerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register(new LivesHudRenderer());
        HudRenderCallback.EVENT.register(new DungeonHealthRenderer());
        HudRenderCallback.EVENT.register(new XpHudRenderer());

        HandledScreens.register(ModScreenHandlers.WEAPON_FORGING_SCREEN_HANDLER, WeaponForgingScreen::new);
        HandledScreens.register(ModScreenHandlers.CHEMISTRY_TABLE_SCREEN_HANDLER, ChemistryTableScreen::new);

        ModModelPredicates.registerModelPredicates();

        EntityModelLayerRegistry.registerModelLayer(MantisModel.MANTIS, MantisModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MANTIS, MantisRenderer::new);
    }
}

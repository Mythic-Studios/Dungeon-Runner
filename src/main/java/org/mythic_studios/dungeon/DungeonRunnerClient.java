package org.mythic_studios.dungeon;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.mythic_studios.dungeon.gui.lives.LivesHudRenderer;
import org.mythic_studios.dungeon.init.ModScreenHandlers;
import org.mythic_studios.dungeon.screen.WeaponForgingScreen;

public class DungeonRunnerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register(new LivesHudRenderer());

        HandledScreens.register(ModScreenHandlers.WEAPON_FORGING_SCREEN_HANDLER, WeaponForgingScreen::new);
    }
}

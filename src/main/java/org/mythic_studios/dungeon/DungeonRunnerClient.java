package org.mythic_studios.dungeon;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.mythic_studios.dungeon.gui.lives.LivesHudRenderer;

public class DungeonRunnerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register(new LivesHudRenderer());

    }
}

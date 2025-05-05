package org.mythic_studios.dungeon.gui.lives;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;

public class LivesHudRenderer implements HudRenderCallback {

    private static final Identifier HEART_ICON = Identifier.of(DungeonRunner.MOD_ID, "textures/gui/heart.png");
    private static final int ICON_SIZE = 20;

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        var client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) return;

        int lives = LivesTracker.getLives(client.player);
        int x = 10, y = 10;

        drawContext.drawTexture(HEART_ICON, x - 2, y - 2, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
        drawContext.drawText(client.textRenderer, "Lives: " + lives, x + 16 + 4, y + 4, 0xFFFFFF, true);
    }

}
package org.mythic_studios.dungeon.gui.health;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.Window;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.config.DungeonConfig;

public class DungeonHealthRenderer implements HudRenderCallback {
    private static final Identifier HEALTH_BAR = Identifier.of(DungeonRunner.MOD_ID, "textures/gui/health_bar/bar_bg-10.png");
    private static final Identifier HEALTH_BAR_OVERLAY = Identifier.of(DungeonRunner.MOD_ID, "textures/gui/health_bar/health_bar_overlay.png");

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        if (player == null || client.options.hudHidden || client.player.isSpectator()) {
            return;
        }
        if (player.isCreative() && !DungeonConfig.showBarsInCreative) return;

        Window window = client.getWindow();
        int screenWidth = window.getScaledWidth();
        int screenHeight = window.getScaledHeight();

        // Texture dimensions - adjust these to match your actual PNG sizes
        int barWidth = 87;
        int barHeight = 14;
        int overlayWidth = 81;
        int overlayHeight = 8;

        // Option 2: Replace vanilla hearts position (ACTIVE)
        int x = screenWidth / 2 - 91;
        int y = screenHeight - 39;

        float maxHealth = player.getMaxHealth();
        float currentHealth = player.getHealth();
        float healthPercent = Math.max(0.0f, Math.min(1.0f, currentHealth / maxHealth)); // Clamp between 0-1

        // Draw background bar
        drawContext.drawTexture(HEALTH_BAR, x, y, 0, 0, barWidth, barHeight, barWidth, barHeight);

        // Calculate overlay position - center it on the background if needed
        int overlayX = x + (barWidth - overlayWidth) / 2; // Center overlay horizontally
        int overlayY = y + (barHeight - overlayHeight) / 2; // Center overlay vertically

        // Draw filled portion
        int filledWidth = (int)(overlayWidth * healthPercent);
        if (filledWidth > 0) {
            // This draws the overlay clipped to show only the filled portion
            drawContext.drawTexture(
                    HEALTH_BAR_OVERLAY,
                    overlayX, overlayY,                    // Position on screen
                    0, 0,                                  // UV start (source texture coordinates)
                    filledWidth, overlayHeight,            // Size to draw (clipped width)
                    overlayWidth, overlayHeight            // Full texture size
            );
        }
    }
}
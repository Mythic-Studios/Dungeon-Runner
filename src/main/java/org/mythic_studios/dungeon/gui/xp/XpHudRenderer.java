package org.mythic_studios.dungeon.gui.xp;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.config.DungeonConfig;

public class XpHudRenderer implements HudRenderCallback {

    // Replace DungeonRunner.MOD_ID with your actual mod ID
    private static final Identifier XP_BAR_BACKGROUND = Identifier.of(DungeonRunner.MOD_ID, "textures/gui/xp_bar/xp_bar.png");
    private static final Identifier XP_BAR_OVERLAY = Identifier.of(DungeonRunner.MOD_ID, "textures/gui/xp_bar/xp_bar_overlay.png");

    // Dimensions based on your texture
    private static final int BAR_WIDTH = 87;
    private static final int BAR_HEIGHT = 28;

    private static final int OVERLAY_WIDTH = 81;
    private static final int OVERLAY_HEIGHT = 8;

    // Position offsets based on your texture layout
    private static final int PROGRESS_BAR_X_OFFSET = 3; // Distance from left edge to where progress bar starts
    private static final int PROGRESS_BAR_Y_OFFSET = 17; // Distance from top to where progress bar starts
    private static final int TAB_CENTER_X = 44; // Center X position of the gray tab at the top

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        // Don't render if no player, in spectator mode, or HUD is hidden
        if (client.player == null || client.options.hudHidden || client.player.isSpectator()) {
            return;
        }
        if (client.player.isCreative() && !DungeonConfig.showBarsInCreative) {return;}

        PlayerEntity player = client.player;

        // Get screen dimensions
        int screenWidth = drawContext.getScaledWindowWidth();
        int screenHeight = drawContext.getScaledWindowHeight();

        // Position where hunger bar would be (right side of screen, above hotbar)
        int x = screenWidth / 2 + 91 - BAR_WIDTH; // Right side, aligned with hunger bar position
        int y = screenHeight - 53; // Same Y position as hunger bar

        // Calculate XP progress (0.0 to 1.0)
        float xpProgress = player.experienceProgress;
        int filledWidth = (int) (xpProgress * OVERLAY_WIDTH);

        // Render background (full texture with green segments and progress bar background)
        drawContext.drawTexture(XP_BAR_BACKGROUND, x, y, 0, 0, BAR_WIDTH, BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);

        // Render filled portion of the progress bar (positioned correctly within the gray area)
        if (filledWidth > 0) {
            drawContext.drawTexture(XP_BAR_OVERLAY,
                    x + PROGRESS_BAR_X_OFFSET,        // X position: base X + offset to progress bar area
                    y + PROGRESS_BAR_Y_OFFSET,        // Y position: base Y + offset to progress bar area
                    0, 0,                             // Source texture coordinates
                    filledWidth, OVERLAY_HEIGHT,      // Width and height to render
                    OVERLAY_WIDTH, OVERLAY_HEIGHT);   // Full source texture dimensions
        }

        // Render XP level text centered on the gray tab at the top
        int xpLevel = Math.min(player.experienceLevel, 50); // Cap level at 50
        if (xpLevel >= 0) {
            String levelText = String.valueOf(xpLevel);
            int textWidth = client.textRenderer.getWidth(levelText);
            int textX = x + TAB_CENTER_X - (textWidth / 2); // Center on the gray tab
            int textY = y + 4; // Position within the tab area (adjust as needed)

            // Use smaller text size for levels 10 and above
            if (xpLevel >= 10) {
                // Draw smaller text by scaling down
                drawContext.getMatrices().push();
                drawContext.getMatrices().translate(textX - 1 + textWidth / 4f, textY + 1, 0);
                drawContext.getMatrices().scale(0.75f, 0.75f, 1.0f);
                drawContext.drawText(client.textRenderer, Text.literal(levelText), 0, 0, 0x55FF55, true);
                drawContext.getMatrices().pop();
            } else {
                // Draw normal size text for levels 1-9
                drawContext.drawText(client.textRenderer, Text.literal(levelText), textX, textY, 0x55FF55, true);
            }
        }
    }
}
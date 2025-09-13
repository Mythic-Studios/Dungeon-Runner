package org.mythic_studios.dungeon.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;

import java.util.List;

public class WeaponForgingScreen extends HandledScreen<WeaponForgingScreenHandler> {

    private static final Identifier GUI_TEXTURE =
            Identifier.of(DungeonRunner.MOD_ID, "textures/gui/weapon_forge/weapon_forge.png");
    private static final Identifier ARROW_TEXTURE =
            Identifier.of(DungeonRunner.MOD_ID, "textures/gui/weapon_forge/arrow_progress.png");
    private static final Identifier CORE_OUTLINE =
            Identifier.of(DungeonRunner.MOD_ID, "textures/gui/weapon_forge/core_outlines/weapon_core_outline.png");

    // List of crystal outlines to cycle through
    private static final List<Identifier> CRYSTAL_OUTLINES = List.of(
            Identifier.of(DungeonRunner.MOD_ID, "textures/gui/weapon_forge/outlines/poisoned_crystal_outline.png"),
            Identifier.of(DungeonRunner.MOD_ID, "textures/gui/weapon_forge/outlines/frozen_crystal_outline.png")
            // Add more later
    );

    private int currentOutlineIndex = 0;
    private long lastSwitchTime = 0;
    private static final long SWITCH_INTERVAL_MS = 700;

    public WeaponForgingScreen(WeaponForgingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        titleY = 6;
        playerInventoryTitleY = 72;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);

        // Get the current crystal outline texture
        if (!handler.getSlot(2).hasStack()) {
            Identifier currentOutline = CRYSTAL_OUTLINES.get(currentOutlineIndex);
            context.drawTexture(currentOutline, x + 97, y + 34, 0, 0, 16, 16, 16, 16);
        }

        context.drawTexture(CORE_OUTLINE, x + 60, y + 34, 0, 0, 16, 16, 16, 16);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(ARROW_TEXTURE, x + 73, y + 35, 0, 0,
                    handler.getScaledArrowProgress(), 17, 24, 17);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Update timer
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSwitchTime >= SWITCH_INTERVAL_MS) {
            currentOutlineIndex = (currentOutlineIndex + 1) % CRYSTAL_OUTLINES.size();
            lastSwitchTime = currentTime;
        }

        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
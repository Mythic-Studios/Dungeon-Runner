package org.mythic_studios.dungeon.screen.chemistry;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;

import java.util.List;

public class ChemistryTableScreen extends HandledScreen<ChemistryTableScreenHandler> {

    private static final Identifier GUI_TEXTURE =
            Identifier.of(DungeonRunner.MOD_ID, "textures/gui/chemist_table/chemist_table.png");
    private static final Identifier ARROW_TEXTURE =
            Identifier.of(DungeonRunner.MOD_ID, "textures/gui/chemist_table/arrow_progress.png");

    public ChemistryTableScreen(ChemistryTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        titleY = 3;
        playerInventoryTitleY = 75;
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

    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(ARROW_TEXTURE, x + 96, y + 34, 0, 0,
                    handler.getScaledArrowProgress(), 17, 22, 17);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
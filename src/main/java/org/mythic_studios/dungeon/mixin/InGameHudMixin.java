package org.mythic_studios.dungeon.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    // Cancel rendering of health
    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    private void hideHealthBar(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        ci.cancel();
    }

    // Cancel rendering of hunger
    @Inject(method = "renderFood", at = @At("HEAD"), cancellable = true)
    private void hideHungerBar(DrawContext context, PlayerEntity player, int top, int right, CallbackInfo ci) {
        ci.cancel();
    }

    // Cancel rendering of experience bar
    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    private void hideXPBar(DrawContext context, int x, CallbackInfo ci) {
        ci.cancel();
    }

    // Cancel rendering of experience level number
    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private void hideXPNumber(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ci.cancel();
    }

    // Cancel rendering of experience bar
    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private static void hideArmorBar(DrawContext context, PlayerEntity player, int i, int j, int k, int x, CallbackInfo ci) {
        ci.cancel();
    }
}

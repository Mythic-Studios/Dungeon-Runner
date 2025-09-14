package org.mythic_studios.dungeon.mixin;


import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Hide item tooltips only when hovering over hotbar slots
@Mixin(InGameHud.class)
public class HideHotbarTooltipMixin {

    // Hide the item name that appears above the hotbar when you hover over items
    @Inject(method = "renderHeldItemTooltip",
            at = @At("HEAD"),
            cancellable = true)
    private void hideHeldItemTooltip(DrawContext context, CallbackInfo ci) {
        // Prevents the held item name from showing above hotbar
        ci.cancel();
    }
}
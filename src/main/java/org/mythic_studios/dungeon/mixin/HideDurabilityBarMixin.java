package org.mythic_studios.dungeon.mixin;

import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class HideDurabilityBarMixin {

    // Hide the durability bar by making non-armor items report no damage bar
    @Inject(method = "isItemBarVisible", at = @At("HEAD"), cancellable = true)
    private void hideNonArmorDurabilityBar(CallbackInfoReturnable<Boolean> cir) {
        ItemStack self = (ItemStack)(Object)this;

        if (!isArmorItem(self)) {
            cir.setReturnValue(false); // Don't show durability bar for non-armor
        }
        // For armor items, let the original method handle it
    }

    // Helper method to check if an item is armor
    @Unique
    private boolean isArmorItem(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }

        Item item = stack.getItem();

        // Check if it's any type of armor
        return item instanceof ArmorItem ||
                item instanceof ElytraItem ||
                item == Items.TURTLE_HELMET;
    }
}
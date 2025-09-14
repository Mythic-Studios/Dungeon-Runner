package org.mythic_studios.dungeon.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Shadow public int experienceLevel;
    @Shadow public float experienceProgress;
    @Shadow public int totalExperience;

    @Unique
    private static final int MAX_LEVEL = 50;

    /**
     * Prevents XP level from going above 50
     */
    @Inject(method = "addExperience", at = @At("TAIL"))
    private void capExperienceLevel(int experience, CallbackInfo ci) {
        if (this.experienceLevel > MAX_LEVEL) {
            this.experienceLevel = MAX_LEVEL;
            this.experienceProgress = 1.0f; // Set progress to full at max level
        } else if (this.experienceLevel == MAX_LEVEL) {
            this.experienceProgress = 1.0f; // Keep bar full at max level
        }
    }

    /**
     * Cap when adding experience levels directly
     */
    @Inject(method = "addExperienceLevels", at = @At("TAIL"))
    private void capAddExperienceLevels(int levels, CallbackInfo ci) {
        if (this.experienceLevel > MAX_LEVEL) {
            this.experienceLevel = MAX_LEVEL;
            this.experienceProgress = 1.0f; // Set progress to full at max level
        } else if (this.experienceLevel == MAX_LEVEL) {
            this.experienceProgress = 1.0f; // Keep bar full at max level
        }
    }
}
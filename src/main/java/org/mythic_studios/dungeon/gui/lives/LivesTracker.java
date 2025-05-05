package org.mythic_studios.dungeon.gui.lives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

import java.util.WeakHashMap;

public class LivesTracker {
    private static final WeakHashMap<PlayerEntity, Integer> livesMap = new WeakHashMap<>();
    private static final int MAX_LIVES = 3;

    public static int getLives(PlayerEntity player) {
        return livesMap.getOrDefault(player, MAX_LIVES);
    }

    public static void decreaseLives(PlayerEntity player) {
        int currentLives = getLives(player) - 1;
        livesMap.put(player, currentLives);

        if (currentLives <= 0 && player instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.changeGameMode(GameMode.SPECTATOR);
        }
    }
}

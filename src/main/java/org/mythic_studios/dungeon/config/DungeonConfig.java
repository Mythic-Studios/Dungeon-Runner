package org.mythic_studios.dungeon.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "dungeon")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class DungeonConfig implements ConfigData {

    @ConfigEntry.Category("general_settings")
    @ConfigEntry.Gui.Tooltip(count = 2)
    public static boolean showDebug = false;
}

package org.mythic_studios.dungeon.init;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import org.mythic_studios.dungeon.config.DungeonConfig;


public class ConfigRegistry {
    public static DungeonConfig CONFIG = new DungeonConfig();

    public static void register() {
        AutoConfig.register(DungeonConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(DungeonConfig.class).getConfig();
    }
}

package org.mythic_studios.dungeon.init;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.screen.chemistry.ChemistryTableScreenHandler;
import org.mythic_studios.dungeon.screen.forging.WeaponForgingScreenHandler;

public class ModScreenHandlers {

    public static final ScreenHandlerType<WeaponForgingScreenHandler> WEAPON_FORGING_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(DungeonRunner.MOD_ID, "weapon_forging_screen_handler"),
                    new ExtendedScreenHandlerType<>(WeaponForgingScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<ChemistryTableScreenHandler> CHEMISTRY_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(DungeonRunner.MOD_ID, "chemistry_table_screen_handler"),
                    new ExtendedScreenHandlerType<>(ChemistryTableScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        DungeonRunner.LOGGER.info("Registering Screen Handlers for " + DungeonRunner.MOD_ID);
    }
}

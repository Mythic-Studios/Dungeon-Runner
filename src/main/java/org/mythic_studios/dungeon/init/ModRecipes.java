package org.mythic_studios.dungeon.init;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.recipe.Chemistry;
import org.mythic_studios.dungeon.recipe.WeaponForging;
import org.mythic_studios.dungeon.recipe.serializer.ChemistTableSerializer;
import org.mythic_studios.dungeon.recipe.serializer.WeaponForgingSerializer;

public class ModRecipes {

    public static final RecipeSerializer<WeaponForging> WEAPON_FORGING_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of("dungeon", "weapon_forging"), new WeaponForgingSerializer());

    public static final RecipeType<WeaponForging> WEAPON_FORGING_TYPE =
            Registry.register(Registries.RECIPE_TYPE, Identifier.of("dungeon", "weapon_forging"), new RecipeType<>() {
                public String toString() {
                    return "dungeon:weapon_forging";
                }
            });

    public static final RecipeSerializer<Chemistry> CHEMISTRY_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of("dungeon", "chemistry"), new ChemistTableSerializer());

    public static final RecipeType<Chemistry> CHEMISTRY_TYPE =
            Registry.register(Registries.RECIPE_TYPE, Identifier.of("dungeon", "chemistry"), new RecipeType<>() {
                public String toString() {
                    return "dungeon:chemistry";
                }
            });

    public static void registerRecipes() {
        DungeonRunner.LOGGER.info("Registering Custom Recipes for " + DungeonRunner.MOD_ID);
    }
}

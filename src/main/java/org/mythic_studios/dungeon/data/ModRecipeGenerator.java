package org.mythic_studios.dungeon.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import org.mythic_studios.dungeon.init.ModItems;
import org.mythic_studios.dungeon.init.weapon_types.ModPoisonSwordItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        poisonBlades(exporter);


    }

    private void poisonBlades(RecipeExporter exporter) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModPoisonSwordItems.POISON_TIPPED_BLADE_COMMON, 1)
                .pattern("mc")
                .input('m', ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD)
                .input('c', ModItems.WEAPON_CORE_COMMON)
                .criterion(hasItem(ModItems.WEAPON_CORE_COMMON), conditionsFromItem(ModItems.WEAPON_CORE_COMMON))
                .criterion(hasItem(ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD), conditionsFromItem(ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModPoisonSwordItems.POISON_TIPPED_BLADE_UNCOMMON, 1)
                .pattern("mc")
                .input('m', ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD)
                .input('c', ModItems.WEAPON_CORE_UNCOMMON)
                .criterion(hasItem(ModItems.WEAPON_CORE_UNCOMMON), conditionsFromItem(ModItems.WEAPON_CORE_UNCOMMON))
                .criterion(hasItem(ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD), conditionsFromItem(ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModPoisonSwordItems.POISON_TIPPED_BLADE_RARE, 1)
                .pattern("mc")
                .input('m', ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD)
                .input('c', ModItems.WEAPON_CORE_RARE)
                .criterion(hasItem(ModItems.WEAPON_CORE_RARE), conditionsFromItem(ModItems.WEAPON_CORE_RARE))
                .criterion(hasItem(ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD), conditionsFromItem(ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModPoisonSwordItems.POISON_TIPPED_BLADE_EPIC, 1)
                .pattern("mc")
                .input('m', ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD)
                .input('c', ModItems.WEAPON_CORE_EPIC)
                .criterion(hasItem(ModItems.WEAPON_CORE_EPIC), conditionsFromItem(ModItems.WEAPON_CORE_EPIC))
                .criterion(hasItem(ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD), conditionsFromItem(ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModPoisonSwordItems.POISON_TIPPED_BLADE_LEGENDARY, 1)
                .pattern("mc")
                .input('m', ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD)
                .input('c', ModItems.WEAPON_CORE_LEGENDARY)
                .criterion(hasItem(ModItems.WEAPON_CORE_LEGENDARY), conditionsFromItem(ModItems.WEAPON_CORE_LEGENDARY))
                .criterion(hasItem(ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD), conditionsFromItem(ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModPoisonSwordItems.POISON_TIPPED_BLADE_MYTHICAL, 1)
                .pattern("mc")
                .input('m', ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD)
                .input('c', ModItems.WEAPON_CORE_MYTHICAL)
                .criterion(hasItem(ModItems.WEAPON_CORE_MYTHICAL), conditionsFromItem(ModItems.WEAPON_CORE_MYTHICAL))
                .criterion(hasItem(ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD), conditionsFromItem(ModPoisonSwordItems.POISON_TIPPED_BLADE_MOLD))
                .offerTo(exporter);

    }
}

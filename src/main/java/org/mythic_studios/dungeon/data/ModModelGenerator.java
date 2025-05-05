package org.mythic_studios.dungeon.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import org.mythic_studios.dungeon.init.ModItems;

public class ModModelGenerator extends FabricModelProvider {
    public ModModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.WEAPON_CORE_UNKNOWN, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAPON_CORE_COMMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAPON_CORE_UNCOMMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAPON_CORE_RARE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAPON_CORE_EPIC, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAPON_CORE_LEGENDARY, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEAPON_CORE_MYTHICAL, Models.GENERATED);

    }
}

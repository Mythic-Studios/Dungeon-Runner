package org.mythic_studios.dungeon.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.init.weapon_types.ModFrostSwordItems;
import org.mythic_studios.dungeon.init.weapon_types.ModPoisonSwordItems;

public class ModItemGroups {

    public static ItemGroup WEAPONS;
    public static ItemGroup MATERIALS_EQUIPMENT;
    public static ItemGroup DUNGEON_BLOCKS;


    public static void init() {

        WEAPONS = Registry.register(Registries.ITEM_GROUP,
                Identifier.of(DungeonRunner.MOD_ID, "weapons"),
                FabricItemGroup.builder().displayName(Text.translatable("itemgroup.weapons"))
                        .icon(() -> new ItemStack(ModPoisonSwordItems.POISON_TIPPED_BLADE_MYTHICAL)).entries((displayContext, entries) -> {

                            entries.add(ModPoisonSwordItems.POISON_TIPPED_BLADE_COMMON);
                            entries.add(ModPoisonSwordItems.POISON_TIPPED_BLADE_UNCOMMON);
                            entries.add(ModPoisonSwordItems.POISON_TIPPED_BLADE_RARE);
                            entries.add(ModPoisonSwordItems.POISON_TIPPED_BLADE_EPIC);
                            entries.add(ModPoisonSwordItems.POISON_TIPPED_BLADE_LEGENDARY);
                            entries.add(ModPoisonSwordItems.POISON_TIPPED_BLADE_MYTHICAL);

                            entries.add(ModFrostSwordItems.FROST_BLADE_COMMON);
                            entries.add(ModFrostSwordItems.FROST_BLADE_UNCOMMON);
                            entries.add(ModFrostSwordItems.FROST_BLADE_RARE);
                            entries.add(ModFrostSwordItems.FROST_BLADE_EPIC);
                            entries.add(ModFrostSwordItems.FROST_BLADE_LEGENDARY);
                            entries.add(ModFrostSwordItems.FROST_BLADE_MYTHICAL);

                        }).build());

        MATERIALS_EQUIPMENT = Registry.register(Registries.ITEM_GROUP,
                Identifier.of(DungeonRunner.MOD_ID, "materials_equipment"),
                FabricItemGroup.builder().displayName(Text.translatable("itemgroup.materials_equipment"))
                        .icon(() -> new ItemStack(ModItems.BLADE_MOLD)).entries((displayContext, entries) -> {

                            entries.add(ModItems.BLADE_MOLD);
                            entries.add(ModItems.CRYSTAL_POISON);
                            entries.add(ModItems.CRYSTAL_FREEZING);
                            entries.add(ModItems.CRYSTAL_SUN);

                            entries.add(ModItems.DUNGEON_KEY);
                            entries.add(ModItems.SECRET_DUNGEON_KEY);

                            entries.add(ModItems.WEAPON_CORE_UNKNOWN);

                        }).build());

        DUNGEON_BLOCKS = Registry.register(Registries.ITEM_GROUP,
                Identifier.of(DungeonRunner.MOD_ID, "dungeon_blocks"),
                FabricItemGroup.builder().displayName(Text.translatable("itemgroup.dungeon_blocks"))
                        .icon(() -> new ItemStack(ModBlocks.WEAPON_FORGE)).entries((displayContext, entries) -> {

                            entries.add(ModBlocks.WEAPON_FORGE);

                            entries.add(ModBlocks.DUNGEON_BRICKS);
                            entries.add(ModBlocks.DUNGEON_KEY_BRICKS);
                            entries.add(ModBlocks.SECRET_DUNGEON_KEY_BRICKS);

                        }).build());
    }
}

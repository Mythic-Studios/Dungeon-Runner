package org.mythic_studios.dungeon.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.init.bow_types.ModBowFlamingItems;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        registerCustomBow(ModBowFlamingItems.BOW_FLAMING_COMMON);
        registerCustomBow(ModBowFlamingItems.BOW_FLAMING_UNCOMMON);
        registerCustomBow(ModBowFlamingItems.BOW_FLAMING_RARE);
        registerCustomBow(ModBowFlamingItems.BOW_FLAMING_EPIC);
        registerCustomBow(ModBowFlamingItems.BOW_FLAMING_LEGENDARY);
        registerCustomBow(ModBowFlamingItems.BOW_FLAMING_MYTHICAL);
    }


    private static void registerCustomBow(Item item) {
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0F;
            }
        });
        ModelPredicateProviderRegistry.register(
                item,
                Identifier.ofVanilla("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );
    }
}

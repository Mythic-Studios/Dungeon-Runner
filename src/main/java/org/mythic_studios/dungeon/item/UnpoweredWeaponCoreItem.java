package org.mythic_studios.dungeon.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.mythic_studios.dungeon.init.ModItems;

import java.util.List;

public class UnpoweredWeaponCoreItem extends Item {
    public UnpoweredWeaponCoreItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient()) {
            // Generate a random number from 0 to 99
            int roll = world.random.nextInt(100);
            Item result = null;
            String rarity = "";

            // Weighted selection
            if (roll < 50) {
                result = ModItems.WEAPON_CORE_COMMON; // 50% chance
                rarity = "Common";
            } else if (roll < 80) {
                result = ModItems.WEAPON_CORE_UNCOMMON; // 30% chance
                rarity = "Uncommon";
            } else if (roll < 90){
                result = ModItems.WEAPON_CORE_RARE; // 10% chance
                rarity = "Rare";
            } else if (roll < 97) {
                result = ModItems.WEAPON_CORE_EPIC; // 7% Chance
                rarity = "Epic";
            } else if (roll < 99) {
                result = ModItems.WEAPON_CORE_LEGENDARY; // 2%
                rarity = "Legendary";
            } else if (roll == 100) {
                result = ModItems.WEAPON_CORE_MYTHICAL; // 1%
                rarity = "Mythical";
            }

            // Remove the item if not in creative
            if (!user.isCreative()) {
                stack.decrement(1);
            }

            // Give the new item
            ItemStack newStack = new ItemStack(result, 1); // DO NOT Assert Null
            if (!user.giveItemStack(newStack)) {
                user.dropItem(newStack, false);
            }

            // Play sound
            world.playSound(
                    null,
                    user.getBlockPos(),
                    SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                    SoundCategory.PLAYERS,
                    1.0f,
                    1.0f
            );

            // Show particles
            ((ServerWorld) world).spawnParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    user.getX(), user.getY() + 1, user.getZ(),
                    10, 0.5, 0.5, 0.5, 0.1
            );

            // Show result in chat
            switch (rarity) {
                case "Common" -> user.sendMessage(Text.translatable("reward.common", false));
                case "Uncommon" -> user.sendMessage(Text.translatable("reward.uncommon", false));
                case "Rare" -> user.sendMessage(Text.translatable("reward.rare", false));
                case "Epic" -> user.sendMessage(Text.translatable("reward.epic", false));
                case "Legendary" -> user.sendMessage(Text.translatable("reward.legendary", false));
                default -> user.sendMessage(Text.translatable("reward.mythical", false));
            }

        }

        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.power_item"));

        super.appendTooltip(stack, context, tooltip, type);
    }
}


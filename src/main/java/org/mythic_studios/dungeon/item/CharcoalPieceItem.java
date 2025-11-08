package org.mythic_studios.dungeon.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.mythic_studios.dungeon.init.ModSketches;

import java.util.List;

public class CharcoalPieceItem extends Item {
    public CharcoalPieceItem(Settings settings) {
        super(settings);
    }

    // Helper method to get the list of possible items (initialized when first called)
    private List<Item> getPossibleItems() {
        return List.of(
                ModSketches.SWORD_SKETCH,
                ModSketches.BOW_SKETCH,
                ModSketches.HAMMER_SKETCH
        );
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        Hand offhand = hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;
        ItemStack offhandStack = user.getStackInHand(offhand);

        // Check if the offhand has paper
        if (!offhandStack.isOf(Items.PAPER)) {
            return TypedActionResult.pass(stack);
        }

        if (!world.isClient()) {
            // Get the list and pick a random item
            List<Item> possibleItems = getPossibleItems();
            Item result = possibleItems.get(world.random.nextInt(possibleItems.size()));

            // Damage the charcoal piece instead of removing it
            if (!user.isCreative()) {
                EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                stack.damage(1, user, slot);
            }

            // Remove the paper from offhand if not in creative
            if (!user.isCreative()) {
                offhandStack.decrement(1);
            }

            // Give the new item
            ItemStack newStack = new ItemStack(result, 1);
            if (!user.giveItemStack(newStack)) {
                user.dropItem(newStack, false);
            }

            // Play sound
            world.playSound(
                    null,
                    user.getBlockPos(),
                    SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT,
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
        }

        return TypedActionResult.success(stack, world.isClient());
    }
}
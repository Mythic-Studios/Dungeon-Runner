package org.mythic_studios.dungeon.item.bow_types;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class FlamingBow extends BowItem {
    public final String Rarity;
    public final int StatusEffectLength;
    
    public FlamingBow(Settings settings, String rarity, int StatusEffectLength) {
        super(settings);
        Rarity = rarity;
        this.StatusEffectLength = StatusEffectLength;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 72000; // Standard bow draw time
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            boolean hasArrows = playerEntity.getAbilities().creativeMode ||
                    !playerEntity.getProjectileType(stack).isEmpty();

            ItemStack arrowStack = playerEntity.getProjectileType(stack);
            if (arrowStack.isEmpty()) {
                arrowStack = new ItemStack(Items.ARROW);
            }

            if (hasArrows || playerEntity.getAbilities().creativeMode) {
                if (arrowStack.isEmpty()) {
                    arrowStack = new ItemStack(Items.ARROW);
                }

                int useTicks = this.getMaxUseTime(stack) - remainingUseTicks;
                float pullProgress = getPullProgress(useTicks);

                if (pullProgress >= 0.1) {
                    if (!world.isClient) {
                        // Create arrow entity
                        net.minecraft.entity.projectile.ArrowEntity arrowEntity =
                                new net.minecraft.entity.projectile.ArrowEntity(world, playerEntity, arrowStack.copyWithCount(1), null);
                        arrowEntity.setVelocity(playerEntity, playerEntity.getPitch(),
                                playerEntity.getYaw(), 0.0f,
                                pullProgress * 3.0f, 1.0f);

                        if (pullProgress == 1.0f) {
                            arrowEntity.setCritical(true);
                        }


                        // Set arrow on fire (this is the key part!)
                        arrowEntity.setOnFireFor(100); // Keeps the arrow visually on fire
                        arrowEntity.setFireTicks(StatusEffectLength * 2); // Sets how long entities burn when hit


                        // Set arrow to not be pickup-able if in creative
                        if (playerEntity.getAbilities().creativeMode) {
                            arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                        }

                        // Spawn the arrow
                        world.spawnEntity(arrowEntity);

                        // Play bow shoot sound
                        world.playSound(null, playerEntity.getX(), playerEntity.getY(),
                                playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT,
                                SoundCategory.PLAYERS, 1.0f,
                                1.0f / (world.getRandom().nextFloat() * 0.4f + 1.2f) + pullProgress * 0.5f);
                    }
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        tooltip.add(Text.literal(" "));

        if (Screen.hasShiftDown()) {
            if (StatusEffectLength == 20) {
                tooltip.add(Text.translatable("tooltip.duration.20.2"));
            } else if (StatusEffectLength == 40) {
                tooltip.add(Text.translatable("tooltip.duration.40.2"));
            } else if (StatusEffectLength == 60) {
                tooltip.add(Text.translatable("tooltip.duration.60.2"));
            } else if (StatusEffectLength == 80) {
                tooltip.add(Text.translatable("tooltip.duration.80.2"));
            } else if (StatusEffectLength == 100) {
                tooltip.add(Text.translatable("tooltip.duration.100.2"));
            } else if (StatusEffectLength == 120) {
                tooltip.add(Text.translatable("tooltip.duration.120.2"));
            } else if (StatusEffectLength == 140) {
                tooltip.add(Text.translatable("tooltip.duration.140.2"));
            }
        }
        else {
            tooltip.add(Text.translatable("tooltip.shift.ability"));
        }


        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.burning_bow.1"));
        tooltip.add(Text.translatable("tooltip.burning_bow.2"));


        super.appendTooltip(stack, context, tooltip, type);
    }
}

package org.mythic_studios.dungeon.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.mythic_studios.dungeon.init.ModBlocks;
import org.mythic_studios.dungeon.init.bow_types.ModBowFlamingItems;
import org.mythic_studios.dungeon.init.hammer_types.ModPoisonHammerItems;
import org.mythic_studios.dungeon.init.sword_types.ModFrostSwordItems;
import org.mythic_studios.dungeon.init.sword_types.ModPoisonSwordItems;

public class UpgradeStationBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public UpgradeStationBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing());
        // makes the front face the player
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (!world.isClient) {
            // Only process main hand to avoid duplicate calls
            if (hand != Hand.MAIN_HAND) {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }

            // Get the block position in front of the upgrade station (where it faces)
            BlockPos frontPos = pos.offset(state.get(FACING));
            BlockState frontState = world.getBlockState(frontPos);
            Block frontBlock = frontState.getBlock();

            // Check if the required block is in front of the upgrade station
            if (frontBlock != ModBlocks.CHARGED_BATTERY) {
                player.sendMessage(Text.literal("The station is unpowered"), false);
                return ItemActionResult.FAIL;
            }

            ItemStack heldItem = player.getStackInHand(hand);
            ItemStack upgradedItem = null;

            // Check for upgradeable items
            if (heldItem.getItem() == ModPoisonHammerItems.POISON_HAMMER_COMMON) {
                upgradedItem = new ItemStack(ModPoisonHammerItems.POISON_HAMMER_UNCOMMON);

            } else if (heldItem.getItem() == ModPoisonHammerItems.POISON_HAMMER_UNCOMMON) {
                upgradedItem = new ItemStack(ModPoisonHammerItems.POISON_HAMMER_RARE);

            } else if (heldItem.getItem() == ModPoisonHammerItems.POISON_HAMMER_RARE) {
                upgradedItem = new ItemStack(ModPoisonHammerItems.POISON_HAMMER_EPIC);

            } else if (heldItem.getItem() == ModPoisonHammerItems.POISON_HAMMER_EPIC) {
                upgradedItem = new ItemStack(ModPoisonHammerItems.POISON_HAMMER_LEGENDARY);

            } else if (heldItem.getItem() == ModPoisonHammerItems.POISON_HAMMER_LEGENDARY) {
                upgradedItem = new ItemStack(ModPoisonHammerItems.POISON_HAMMER_MYTHICAL);

            }

            else if (heldItem.getItem() == ModBowFlamingItems.BOW_FLAMING_COMMON) {
                upgradedItem = new ItemStack(ModBowFlamingItems.BOW_FLAMING_UNCOMMON);

            }else if (heldItem.getItem() == ModBowFlamingItems.BOW_FLAMING_UNCOMMON) {
                upgradedItem = new ItemStack(ModBowFlamingItems.BOW_FLAMING_RARE);

            }else if (heldItem.getItem() == ModBowFlamingItems.BOW_FLAMING_RARE) {
                upgradedItem = new ItemStack(ModBowFlamingItems.BOW_FLAMING_EPIC);

            }else if (heldItem.getItem() == ModBowFlamingItems.BOW_FLAMING_EPIC) {
                upgradedItem = new ItemStack(ModBowFlamingItems.BOW_FLAMING_LEGENDARY);

            }else if (heldItem.getItem() == ModBowFlamingItems.BOW_FLAMING_LEGENDARY) {
                upgradedItem = new ItemStack(ModBowFlamingItems.BOW_FLAMING_MYTHICAL);

            }

            else if (heldItem.getItem() == ModPoisonSwordItems.POISON_TIPPED_BLADE_COMMON) {
                upgradedItem = new ItemStack(ModPoisonSwordItems.POISON_TIPPED_BLADE_UNCOMMON);

            }else if (heldItem.getItem() == ModPoisonSwordItems.POISON_TIPPED_BLADE_UNCOMMON) {
                upgradedItem = new ItemStack(ModPoisonSwordItems.POISON_TIPPED_BLADE_RARE);

            }else if (heldItem.getItem() == ModPoisonSwordItems.POISON_TIPPED_BLADE_RARE) {
                upgradedItem = new ItemStack(ModPoisonSwordItems.POISON_TIPPED_BLADE_EPIC);

            }else if (heldItem.getItem() == ModPoisonSwordItems.POISON_TIPPED_BLADE_EPIC) {
                upgradedItem = new ItemStack(ModPoisonSwordItems.POISON_TIPPED_BLADE_LEGENDARY);

            }else if (heldItem.getItem() == ModPoisonSwordItems.POISON_TIPPED_BLADE_LEGENDARY) {
                upgradedItem = new ItemStack(ModPoisonSwordItems.POISON_TIPPED_BLADE_MYTHICAL);

            }

            else if (heldItem.getItem() == ModFrostSwordItems.FROST_BLADE_COMMON) {
                upgradedItem = new ItemStack(ModFrostSwordItems.FROST_BLADE_UNCOMMON);

            }else if (heldItem.getItem() == ModFrostSwordItems.FROST_BLADE_UNCOMMON) {
                upgradedItem = new ItemStack(ModFrostSwordItems.FROST_BLADE_RARE);

            }else if (heldItem.getItem() == ModFrostSwordItems.FROST_BLADE_RARE) {
                upgradedItem = new ItemStack(ModFrostSwordItems.FROST_BLADE_EPIC);

            }else if (heldItem.getItem() == ModFrostSwordItems.FROST_BLADE_EPIC) {
                upgradedItem = new ItemStack(ModFrostSwordItems.FROST_BLADE_LEGENDARY);

            }else if (heldItem.getItem() == ModFrostSwordItems.FROST_BLADE_LEGENDARY) {
                upgradedItem = new ItemStack(ModFrostSwordItems.FROST_BLADE_MYTHICAL);

            }

            // Perform the upgrade if a valid upgrade was found
            if (upgradedItem != null) {
                heldItem.decrement(1);

                if (heldItem.isEmpty()) {
                    player.setStackInHand(hand, upgradedItem);
                } else {
                    if (!player.getInventory().insertStack(upgradedItem)) {
                        player.dropItem(upgradedItem, false);
                    }
                }

                // Change the block in front of the upgrade station
                world.setBlockState(frontPos, ModBlocks.UNPOWERED_BATTERY.getDefaultState());

                return ItemActionResult.SUCCESS;
            }
        }

        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }
}
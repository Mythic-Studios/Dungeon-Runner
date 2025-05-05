package org.mythic_studios.dungeon.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.mythic_studios.dungeon.config.DungeonConfig;

import java.util.List;

public class DungeonKeyItem extends Item {


    private final Block triggerBlock; // The block to right-click on
    private final Block targetBlock;  // The block to clear in the area


    public DungeonKeyItem(Settings settings, Block triggerBlock, Block targetBlock) {
        super(settings);
        this.triggerBlock = triggerBlock;
        this.targetBlock = targetBlock;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos clickedPos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();

        if (!world.isClient() && context.getHand() == Hand.MAIN_HAND) {
            BlockState clickedState = world.getBlockState(clickedPos);

            if (clickedState.getBlock() == triggerBlock) {
                if (player != null && DungeonConfig.showDebug) {
                    player.sendMessage(Text.literal("✔ Trigger matched: " + triggerBlock), false);
                }

                BlockPos center = clickedPos;

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            BlockPos targetPos = center.add(dx, dy, dz);
                            BlockState state = world.getBlockState(targetPos);

                            if (state.getBlock() == targetBlock) {
                                world.breakBlock(targetPos, false);
                                world.breakBlock(clickedPos, false);
                                if (player != null && DungeonConfig.showDebug) {
                                    player.sendMessage(Text.literal("✔ Broke target at " + targetPos), false);
                                }
                            } else {
                                if (player != null && DungeonConfig.showDebug) {
                                    player.sendMessage(Text.literal("✘ Not target: " + state.getBlock()), false);
                                }
                            }
                        }
                    }
                }

                return ActionResult.SUCCESS;
            } else {
                if (player != null && DungeonConfig.showDebug) {
                    player.sendMessage(Text.literal("✘ Wrong trigger: " + clickedState.getBlock()), false);
                }
            }
        }

        return ActionResult.PASS;
    }



    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {


        tooltip.add(Text.literal(" "));


        super.appendTooltip(stack, context, tooltip, type);
    }
}


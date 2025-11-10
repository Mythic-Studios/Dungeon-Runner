package org.mythic_studios.dungeon.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BatteryBlock extends Block {
    public final boolean charged;

    public BatteryBlock(Settings settings, boolean charged) {
        super(settings);
        this.charged = charged;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        if (!world.isClient) {
            if (charged) {
                player.sendMessage(Text.translatable("message.battery.charged"));
            } else {
                player.sendMessage(Text.translatable("message.battery.depleted"));
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.SUCCESS;
    }
}

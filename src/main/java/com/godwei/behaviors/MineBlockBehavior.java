package com.godwei.behaviors;

import com.godwei.config.ConfigReader;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;

public class MineBlockBehavior extends ItemDispenserBehavior {
    private static void MineBlock(ServerWorld world, BlockPos target, ItemStack tool) {
        if (!(tool.getItem() instanceof MiningToolItem)) {
            return;
        }
        BlockState TgBlock = world.getBlockState(target);

        if ((tool.getItem().isSuitableFor(TgBlock))) {
            world.breakBlock(target, false);
            Block.dropStacks(TgBlock, world, target, null, null, tool);
            tool.damage(1, world.getRandom(), null);
        }
    }

    @Override
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        if (!ConfigReader.canMineBlocks()) {
            return super.dispenseSilently(pointer, stack);
        }
        BlockPos blockPos;
        ServerWorld worldAccess = pointer.getWorld();
        BlockState blockState = worldAccess.getBlockState(blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING)));
        if (blockState.getBlock() instanceof AirBlock) {
            return super.dispenseSilently(pointer, stack);
        }
        MineBlock(worldAccess, blockPos, stack);
        worldAccess.emitGameEvent(GameEvent.BLOCK_DESTROY, blockPos, GameEvent.Emitter.of(blockState));
        return stack;
    }
}

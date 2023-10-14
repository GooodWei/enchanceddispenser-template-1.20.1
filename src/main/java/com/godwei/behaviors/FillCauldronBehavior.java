package com.godwei.behaviors;

import com.godwei.config.Deserialization;
import com.godwei.mixin.IBucketFluidAccessor;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;

public class FillCauldronBehavior extends ItemDispenserBehavior {
    private final ItemDispenserBehavior fallbackBehavior = new ItemDispenserBehavior();


    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        if (!Deserialization.canBucketFill()){
            defaultDispense(pointer, stack);
        }

        ItemStack itemStack;
        Item item = stack.getItem();
        pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
        BlockPos blockPos;
        ServerWorld world = pointer.getWorld();
        BlockState blockState = world.getBlockState(blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING)));
        Block block = blockState.getBlock();
        if (!block.getClass().equals(CauldronBlock.class)) {
            return defaultDispense(pointer, stack);
        }
        if (((CauldronBlock) block).isFull(blockState)) {
            return defaultDispense(pointer, stack);
        }

        {
            if (item instanceof PowderSnowBucketItem) {

                world.setBlockState(blockPos, Blocks.POWDER_SNOW_CAULDRON.getDefaultState().with(PowderSnowCauldronBlock.LEVEL, 3));
                itemStack = new ItemStack(Items.BUCKET);
            } else {
                BucketItem item1 = (BucketItem) item;
                IBucketFluidAccessor fluidAccessor = (IBucketFluidAccessor) item1;
                if (fluidAccessor.getFluid() instanceof LavaFluid) {
                    world.setBlockState(blockPos, Blocks.LAVA_CAULDRON.getDefaultState());
                    itemStack = new ItemStack(Items.BUCKET);
                } else if (fluidAccessor.getFluid() instanceof WaterFluid) {
                    world.setBlockState(blockPos, Blocks.WATER_CAULDRON.getDefaultState().with(LeveledCauldronBlock.LEVEL, 3));
                    itemStack = new ItemStack(Items.BUCKET);
                } else {
                    return defaultDispense(pointer, stack);
                }
            }
        }
        world.emitGameEvent(null, GameEvent.BLOCK_CHANGE, blockPos);
        Item item2 = itemStack.getItem();
        stack.decrement(1);
        if (stack.isEmpty()) {
            return new ItemStack(item2);
        }
        if (((DispenserBlockEntity) pointer.getBlockEntity()).addToFirstFreeSlot(new ItemStack(item)) < 0) {
            this.fallbackBehavior.dispense(pointer, new ItemStack(item));
        }
        return stack;
    }

    protected ItemStack defaultDispense(BlockPointer pointer, ItemStack stack) {
        return super.dispenseSilently(pointer, stack);
    }

}

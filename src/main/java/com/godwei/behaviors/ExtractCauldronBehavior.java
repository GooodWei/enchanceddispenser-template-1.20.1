package com.godwei.behaviors;

import com.godwei.config.ConfigReader;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;

public class ExtractCauldronBehavior extends ItemDispenserBehavior {
    private final ItemDispenserBehavior fallbackBehavior = new ItemDispenserBehavior();
    @Override
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        ItemStack itemStack;
        BlockPos blockPos;
        ServerWorld worldAccess = pointer.getWorld();
        BlockState blockState = worldAccess.getBlockState(blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING)));
        Block block = blockState.getBlock();

        if (block instanceof FluidDrainable){
            itemStack = ((FluidDrainable) block).tryDrainFluid(worldAccess, blockPos, blockState);
            if (itemStack.isEmpty()) {
                return super.dispenseSilently(pointer, stack);
            }
            worldAccess.emitGameEvent(null, GameEvent.FLUID_PICKUP, blockPos);
            Item item = itemStack.getItem();
            stack.decrement(1);
            if (stack.isEmpty()) {
                return new ItemStack(item);
            }
            if (((DispenserBlockEntity)pointer.getBlockEntity()).addToFirstFreeSlot(new ItemStack(item)) < 0) {
                this.fallbackBehavior.dispense(pointer, new ItemStack(item));
            }
            return stack;
        }
        if (!ConfigReader.canBucketExtract()){
            return super.dispenseSilently(pointer, stack);
        }
        if (block instanceof AbstractCauldronBlock) {
            if (block instanceof LavaCauldronBlock){
                worldAccess.setBlockState(blockPos, Blocks.CAULDRON.getDefaultState());
                itemStack = new ItemStack(Items.LAVA_BUCKET);
            }else if(block instanceof PowderSnowCauldronBlock){
                worldAccess.setBlockState(blockPos, Blocks.CAULDRON.getDefaultState());
                itemStack = new ItemStack(Items.POWDER_SNOW_BUCKET);
            }else if (block instanceof LeveledCauldronBlock && ((LeveledCauldronBlock) block).isFull(blockState)){
                worldAccess.setBlockState(blockPos, Blocks.CAULDRON.getDefaultState());
                itemStack = new ItemStack(Items.WATER_BUCKET);
            }else{
                return super.dispenseSilently(pointer, stack);
            }
            worldAccess.emitGameEvent(null, GameEvent.BLOCK_CHANGE, blockPos);
            Item item = itemStack.getItem();
            stack.decrement(1);
            if (stack.isEmpty()) {
                return new ItemStack(item);
            }
            if (((DispenserBlockEntity)pointer.getBlockEntity()).addToFirstFreeSlot(new ItemStack(item)) < 0) {
                this.fallbackBehavior.dispense(pointer, new ItemStack(item));
            }
            return stack;
        } else {
            return super.dispenseSilently(pointer, stack);
        }
    }
}

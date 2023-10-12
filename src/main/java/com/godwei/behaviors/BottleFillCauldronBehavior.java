package com.godwei.behaviors;

import net.minecraft.block.*;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;

public class BottleFillCauldronBehavior extends ItemDispenserBehavior {
    private final ItemDispenserBehavior fallbackBehavior = new ItemDispenserBehavior();

    @Override
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        ItemStack itemStack;
        Item item = stack.getItem();
        pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
        BlockPos blockPos;
        ServerWorld world = pointer.getWorld();
        BlockState blockState = world.getBlockState(blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING)));
        Block block = blockState.getBlock();
        if (!(block instanceof CauldronBlock)
                && !(block instanceof LeveledCauldronBlock)
        ) {
            return defaultDispense(pointer, stack);
        }
        if (block instanceof PowderSnowCauldronBlock) {
            return defaultDispense(pointer, stack);
        }

        if (PotionUtil.getPotion(stack) != Potions.WATER) {
            return defaultDispense(pointer, stack);
        }

        if (block instanceof CauldronBlock) {
            world.setBlockState(blockPos, Blocks.WATER_CAULDRON.getDefaultState());
            world.playSound(null, blockPos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, blockPos);
            itemStack = new ItemStack(Items.GLASS_BOTTLE);
        } else if (!((LeveledCauldronBlock) block).isFull(blockState)) {
            blockState.cycle(LeveledCauldronBlock.LEVEL);
            itemStack = new ItemStack(Items.GLASS_BOTTLE);
        } else {
            return defaultDispense(pointer, stack);
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

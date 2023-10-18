package com.godwei.behaviors;

import com.godwei.config.ConfigReader;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;

public class BottleExtractCauldronBehavior extends FallibleItemDispenserBehavior {
    private final ItemDispenserBehavior fallbackBehavior = new ItemDispenserBehavior();


    private ItemStack tryPutFilledBottle(BlockPointer pointer, ItemStack emptyBottleStack, ItemStack filledBottleStack) {
        emptyBottleStack.decrement(1);
        if (emptyBottleStack.isEmpty()) {
            pointer.getWorld().emitGameEvent(null, GameEvent.FLUID_PICKUP, pointer.getPos());
            return filledBottleStack.copy();
        }
        if (((DispenserBlockEntity)pointer.getBlockEntity()).addToFirstFreeSlot(filledBottleStack.copy()) < 0) {
            this.fallbackBehavior.dispense(pointer, filledBottleStack.copy());
        }
        return emptyBottleStack;
    }
    @Override
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        if (!ConfigReader.canBottleExtract()){
            return super.dispenseSilently(pointer, stack);
        }
        this.setSuccess(false);
        BlockPos blockPos;
        ServerWorld worldAccess = pointer.getWorld();
        BlockState blockState = worldAccess.getBlockState(blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING)));
        Block block = blockState.getBlock();
        if (blockState.isIn(BlockTags.BEEHIVES, state -> state.contains(BeehiveBlock.HONEY_LEVEL) && state.getBlock() instanceof BeehiveBlock) && blockState.get(BeehiveBlock.HONEY_LEVEL) >= 5) {
            ((BeehiveBlock)blockState.getBlock()).takeHoney(worldAccess, blockState, blockPos, null, BeehiveBlockEntity.BeeState.BEE_RELEASED);
            this.setSuccess(true);
            return this.tryPutFilledBottle(pointer, stack, new ItemStack(Items.HONEY_BOTTLE));
        }
        if (worldAccess.getFluidState(blockPos).isIn(FluidTags.WATER)) {
            this.setSuccess(true);
            return this.tryPutFilledBottle(pointer, stack, PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER));
        }
        if (block instanceof AbstractCauldronBlock) {
            if (block instanceof LeveledCauldronBlock) {
                LeveledCauldronBlock.decrementFluidLevel(blockState, worldAccess, blockPos);
                PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER);


            } else {
                return super.dispenseSilently(pointer, stack);
            }
            worldAccess.emitGameEvent(null, GameEvent.BLOCK_CHANGE, blockPos);
            stack.decrement(1);
            if (stack.isEmpty()) {
                return PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER);
            }
            if (((DispenserBlockEntity) pointer.getBlockEntity()).addToFirstFreeSlot(PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER)) < 0) {
                this.fallbackBehavior.dispense(pointer, PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER));
            }
            return stack;
        } else {
            return super.dispenseSilently(pointer, stack);
        }

    }
}


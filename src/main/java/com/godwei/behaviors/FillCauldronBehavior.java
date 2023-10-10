package com.godwei.behaviors;

import com.godwei.mixin.IBucketFluidAccessor;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

public class FillCauldronBehavior implements DispenserBehavior {
    private final ItemDispenserBehavior fallbackBehavior = new ItemDispenserBehavior();
    @Override
    public final ItemStack dispense(BlockPointer blockPointer, ItemStack itemStack) {
        ItemStack itemStack2 = this.dispenseSilently(blockPointer, itemStack);
        this.playSound(blockPointer);
        this.spawnParticles(blockPointer, blockPointer.getBlockState().get(DispenserBlock.FACING));
        return itemStack2;
    }

    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        ItemStack itemStack;
        Item item = stack.getItem();
        pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
        BlockPos blockPos;
        ServerWorld world = pointer.getWorld();
        BlockState blockState = world.getBlockState(blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING)));
        Block block = blockState.getBlock();
        if (!block.getClass().equals(CauldronBlock.class)){
            return defaultDispense(pointer, stack);
        }
        if (((CauldronBlock) block).isFull(blockState)){
            return defaultDispense(pointer, stack);
        }
        if (item instanceof PotionItem){
          if (PotionUtil.getPotion(stack) != Potions.WATER){
              return defaultDispense(pointer, stack);
          } else {
              blockState.cycle(LeveledCauldronBlock.LEVEL);
              itemStack = new ItemStack(Items.GLASS_BOTTLE);
          }
        }
        else  {
            if (item instanceof PowderSnowBucketItem){

                world.setBlockState(blockPos, Blocks.POWDER_SNOW_CAULDRON.getDefaultState().with(PowderSnowCauldronBlock.LEVEL, 3));
                itemStack = new ItemStack(Items.BUCKET);
            }
            else  {
                BucketItem item1 = (BucketItem) item;
                IBucketFluidAccessor fluidAccessor = (IBucketFluidAccessor) item1;
                if (fluidAccessor.getFluid() instanceof LavaFluid){
                    world.setBlockState(blockPos, Blocks.LAVA_CAULDRON.getDefaultState());
                    itemStack = new ItemStack(Items.BUCKET);
                }
                else if (fluidAccessor.getFluid() instanceof WaterFluid){
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
        if (((DispenserBlockEntity)pointer.getBlockEntity()).addToFirstFreeSlot(new ItemStack(item)) < 0) {
            this.fallbackBehavior.dispense(pointer, new ItemStack(item));
        }
        return stack;
    }

    protected ItemStack defaultDispense(BlockPointer pointer, ItemStack stack){
        Direction direction = pointer.getBlockState().get(DispenserBlock.FACING);
        Position position = DispenserBlock.getOutputLocation(pointer);
        ItemStack itemStack = stack.split(1);
        ItemDispenserBehavior.spawnItem(pointer.getWorld(), itemStack, 6, direction, position);
        return stack;
    }

    protected void playSound(BlockPointer pointer) {
        pointer.getWorld().syncWorldEvent(WorldEvents.DISPENSER_DISPENSES, pointer.getPos(), 0);
    }

    protected void spawnParticles(BlockPointer pointer, Direction side) {
        pointer.getWorld().syncWorldEvent(WorldEvents.DISPENSER_ACTIVATED, pointer.getPos(), side.getId());
    }
}

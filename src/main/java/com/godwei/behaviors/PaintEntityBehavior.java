package com.godwei.behaviors;

import com.godwei.Utlis.ColoredBlocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.List;

public class PaintEntityBehavior extends ItemDispenserBehavior {
    @Override
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        BlockPos pos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
        Box box = new Box(pos);
        List<Entity> entities = pointer.getWorld().getOtherEntities(null, box);
        List<SheepEntity> sheep = new ArrayList<>();
        List<ItemEntity> itemEntities = new ArrayList<>();
        entities.forEach(
                i -> {
                    if (i instanceof SheepEntity) {
                        sheep.add((SheepEntity) i);
                    }
                    if (i instanceof ItemEntity) {
                        if (ColoredBlocks.ColoredConcretePowders.contains(((ItemEntity) i).getStack().getItem())) {
                            itemEntities.add((ItemEntity) i);
                        }
                    }
                }
        );
        if (sheep.isEmpty() && itemEntities.isEmpty()) {
            return super.dispenseSilently(pointer, stack);
        }
        if (!(stack.getItem() instanceof DyeItem dyeItem)) {
            return super.dispenseSilently(pointer, stack);
        }
        if (!sheep.isEmpty()) {
            sheep.forEach(
                    i -> {
                        if (stack.getCount() >= 1) {
                            i.setColor(dyeItem.getColor());
                            stack.decrement(1);
                        }
                    }
            );
        }
        if (!itemEntities.isEmpty()) {
            itemEntities.forEach(
                    itemEntity -> {
                        ItemStack itemStack = itemEntity.getStack();
                        if (stack.getCount() >= 1) {
                            DyeColor color = dyeItem.getColor();
                            itemEntity.setStack(new ItemStack(ColoredBlocks.ColorToConcretePowder().get(color), itemStack.getCount()));
                            stack.decrement(1);
                        }
                    }
            );
        }
        return stack;
    }
}

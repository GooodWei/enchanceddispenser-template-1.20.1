package com.godwei.behaviors;

import com.godwei.Utlis.ColoredBlocks;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.List;

public class DyeItemsBehaviors extends ItemDispenserBehavior {

    @Override
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        List<Entity> entities = pointer.getWorld().getOtherEntities(null, new Box(pointer.getPos()));
        List<ItemEntity> itemEntities = new ArrayList<>();
        entities.forEach(
                entity -> {
                    if (entity instanceof ItemEntity) {
                        itemEntities.add((ItemEntity) entity);
                    }
                }
        );
        if (itemEntities.isEmpty()) {
            return super.dispenseSilently(pointer, stack);
        }
        itemEntities.forEach(
                itemEntity -> {
                    ItemStack itemStack = itemEntity.getStack();
                    if (ColoredBlocks.ColoredConcretePowders.contains(itemStack.getItem())){
                        DyeItem dyeItem = (DyeItem) stack.getItem();
                        DyeColor color = dyeItem.getColor();
                    }
                }
        );
        return stack;
    }
}

package com.godwei.mixin;

import net.minecraft.block.*;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(DispenserBlock.class)
public class BottleExtractBehaviorInjector {
    @Shadow
    @Final
    private static Map<Item, DispenserBehavior> BEHAVIORS;
    @Inject(at = @At("TAIL"), method = "registerBehavior")
    private static void registerDefaults(CallbackInfo ci){
        ItemDispenserBehavior bottle_behavior = new ItemDispenserBehavior(){
            private final ItemDispenserBehavior fallbackBehavior = new ItemDispenserBehavior();
            @Override
            protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                BlockPos blockPos;
                ServerWorld worldAccess = pointer.getWorld();
                BlockState blockState = worldAccess.getBlockState(blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING)));
                Block block = blockState.getBlock();
                if (block instanceof AbstractCauldronBlock) {
                    if (block instanceof LeveledCauldronBlock){
                        LeveledCauldronBlock.decrementFluidLevel(blockState, worldAccess, blockPos);
                        PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER);


                    }else{
                        return super.dispenseSilently(pointer, stack);
                    }
                    worldAccess.emitGameEvent(null, GameEvent.BLOCK_CHANGE, blockPos);
                    stack.decrement(1);
                    if (stack.isEmpty()) {
                        return PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER);
                    }
                    if (((DispenserBlockEntity)pointer.getBlockEntity()).addToFirstFreeSlot(PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER)) < 0) {
                        this.fallbackBehavior.dispense(pointer, PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER));
                    }
                    return stack;
                } else {
                    return super.dispenseSilently(pointer, stack);
                }
            }
        };

        BEHAVIORS.put(Items.GLASS_BOTTLE, bottle_behavior);
    }
}

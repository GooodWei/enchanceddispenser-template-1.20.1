package com.godwei.mixin;

import com.godwei.behaviors.BottleExtractCauldronBehavior;
import com.godwei.behaviors.BottleFillCauldronBehavior;
import com.godwei.behaviors.ExtractCauldronBehavior;
import com.godwei.behaviors.FillCauldronBehavior;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(DispenserBlock.class)
public class DispenserBehaviorInjector {
	@Shadow @Final private static Map<Item, DispenserBehavior> BEHAVIORS;

	@Inject(at = @At("HEAD"), method = "registerBehavior")
	private static void registerDefaults(CallbackInfo ci){
		ExtractCauldronBehavior extractCauldronBehavior = new ExtractCauldronBehavior();
		BottleFillCauldronBehavior bottleFillCauldronBehavior = new BottleFillCauldronBehavior();
		FillCauldronBehavior fillCauldronBehavior = new FillCauldronBehavior();
		BottleExtractCauldronBehavior bottleExtractCauldronBehavior = new BottleExtractCauldronBehavior();


		BEHAVIORS.put(Items.BUCKET, extractCauldronBehavior);
		BEHAVIORS.put(Items.WATER_BUCKET, fillCauldronBehavior);
		BEHAVIORS.put(Items.LAVA_BUCKET, fillCauldronBehavior);
		BEHAVIORS.put(Items.POWDER_SNOW_BUCKET, fillCauldronBehavior);
		BEHAVIORS.put(Items.POTION , bottleFillCauldronBehavior);
		BEHAVIORS.put(Items.GLASS_BOTTLE, bottleExtractCauldronBehavior);

	}
}
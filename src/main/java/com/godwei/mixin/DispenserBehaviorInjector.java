package com.godwei.mixin;

import com.godwei.behaviors.BottleExtractCauldronBehavior;
import com.godwei.behaviors.ExtractCauldronBehavior;
import com.godwei.behaviors.FillCauldronBehavior;
import com.godwei.behaviors.PaintEntityBehavior;
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
		FillCauldronBehavior fillCauldronBehavior = new FillCauldronBehavior();
		BottleExtractCauldronBehavior bottleExtractCauldronBehavior = new BottleExtractCauldronBehavior();
		PaintEntityBehavior paintEntityBehavior = new PaintEntityBehavior();


		BEHAVIORS.put(Items.BUCKET, extractCauldronBehavior);
		BEHAVIORS.put(Items.WATER_BUCKET, fillCauldronBehavior);
		BEHAVIORS.put(Items.LAVA_BUCKET, fillCauldronBehavior);
		BEHAVIORS.put(Items.POWDER_SNOW_BUCKET, fillCauldronBehavior);
		BEHAVIORS.put(Items.GLASS_BOTTLE, bottleExtractCauldronBehavior);

		BEHAVIORS.put(Items.BLACK_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.BLUE_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.BROWN_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.CYAN_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.GRAY_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.GREEN_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.LIGHT_BLUE_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.LIGHT_GRAY_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.LIME_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.MAGENTA_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.ORANGE_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.PINK_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.PURPLE_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.RED_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.WHITE_DYE, paintEntityBehavior);
		BEHAVIORS.put(Items.YELLOW_DYE, paintEntityBehavior);



	}
}
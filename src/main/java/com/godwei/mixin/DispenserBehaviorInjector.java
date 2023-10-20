package com.godwei.mixin;

import com.godwei.behaviors.*;
import com.godwei.config.ConfigReader;
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
		MineBlockBehavior mineBlockBehavior = new MineBlockBehavior();


		if (ConfigReader.canBucketExtract()) {
			BEHAVIORS.put(Items.BUCKET, extractCauldronBehavior);
		}


		if (ConfigReader.canBucketFill()) {
			BEHAVIORS.put(Items.WATER_BUCKET, fillCauldronBehavior);
			BEHAVIORS.put(Items.LAVA_BUCKET, fillCauldronBehavior);
			BEHAVIORS.put(Items.POWDER_SNOW_BUCKET, fillCauldronBehavior);
		}


		if (ConfigReader.canBottleExtract()) {
			BEHAVIORS.put(Items.GLASS_BOTTLE, bottleExtractCauldronBehavior);
		}

		if (ConfigReader.canPaintEntities()) {
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

		if (ConfigReader.canMineBlocks()) {
			BEHAVIORS.put(Items.DIAMOND_PICKAXE, mineBlockBehavior);
			BEHAVIORS.put(Items.GOLDEN_PICKAXE,mineBlockBehavior);
			BEHAVIORS.put(Items.IRON_PICKAXE, mineBlockBehavior);
			BEHAVIORS.put(Items.STONE_PICKAXE,mineBlockBehavior);
			BEHAVIORS.put(Items.NETHERITE_PICKAXE,mineBlockBehavior);
			BEHAVIORS.put(Items.WOODEN_PICKAXE,mineBlockBehavior);
		}


	}
}
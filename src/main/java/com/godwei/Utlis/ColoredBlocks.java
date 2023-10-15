package com.godwei.Utlis;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColoredBlocks {
    public static final List<Item> ColoredConcretePowders = Arrays.asList(
            Items.BLACK_CONCRETE_POWDER,
            Items.PINK_CONCRETE_POWDER,
            Items.PURPLE_CONCRETE_POWDER,
            Items.BLUE_CONCRETE_POWDER,
            Items.BROWN_CONCRETE_POWDER,
            Items.CYAN_CONCRETE_POWDER,
            Items.GREEN_CONCRETE_POWDER,
            Items.LIGHT_BLUE_CONCRETE_POWDER,
            Items.LIGHT_GRAY_CONCRETE_POWDER,
            Items.LIME_CONCRETE_POWDER,
            Items.MAGENTA_CONCRETE_POWDER,
            Items.ORANGE_CONCRETE_POWDER,
            Items.RED_CONCRETE_POWDER,
            Items.WHITE_CONCRETE_POWDER,
            Items.YELLOW_CONCRETE_POWDER,
            Items.GRAY_CONCRETE_POWDER
    );

    private static final Map<DyeColor, Item> ColorToPowder = new HashMap<>();

    public static Map<DyeColor, Item> ColorToConcretePowder() {
        ColorToPowder.put(DyeColor.BLACK, Items.BLACK_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.PINK, Items.PINK_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.PURPLE, Items.PURPLE_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.BLUE, Items.BLUE_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.BROWN, Items.BROWN_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.CYAN, Items.CYAN_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.GREEN, Items.GREEN_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.LIME, Items.LIME_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.MAGENTA, Items.MAGENTA_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.ORANGE, Items.ORANGE_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.RED, Items.RED_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.WHITE, Items.WHITE_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.YELLOW, Items.WHITE_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.GRAY, Items.GRAY_CONCRETE_POWDER);
        return ColorToPowder;
    }
}

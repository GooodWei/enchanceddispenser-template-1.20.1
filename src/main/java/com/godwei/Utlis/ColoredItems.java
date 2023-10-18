package com.godwei.Utlis;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColoredItems {
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

    public static final List<Item> ColoredGlass = Arrays.asList(
            Items.BLACK_STAINED_GLASS,
            Items.PINK_STAINED_GLASS,
            Items.PURPLE_STAINED_GLASS,
            Items.BLUE_STAINED_GLASS,
            Items.BROWN_STAINED_GLASS,
            Items.CYAN_STAINED_GLASS,
            Items.GREEN_STAINED_GLASS,
            Items.LIGHT_BLUE_STAINED_GLASS,
            Items.LIGHT_GRAY_STAINED_GLASS,
            Items.LIME_STAINED_GLASS,
            Items.MAGENTA_STAINED_GLASS,
            Items.ORANGE_STAINED_GLASS,
            Items.RED_STAINED_GLASS,
            Items.WHITE_STAINED_GLASS,
            Items.YELLOW_STAINED_GLASS,
            Items.GRAY_STAINED_GLASS
    );

    public static final List<Item> ColoredWools = Arrays.asList(
            Items.BLACK_WOOL,
            Items.PINK_WOOL,
            Items.PURPLE_WOOL,
            Items.BLUE_WOOL,
            Items.BROWN_WOOL,
            Items.CYAN_WOOL,
            Items.GREEN_WOOL,
            Items.LIGHT_BLUE_WOOL,
            Items.LIGHT_GRAY_WOOL,
            Items.LIME_WOOL,
            Items.MAGENTA_WOOL,
            Items.ORANGE_WOOL,
            Items.RED_WOOL,
            Items.WHITE_WOOL,
            Items.YELLOW_WOOL,
            Items.GRAY_WOOL
    );

    public static final Map<DyeColor, Item> ColorToWool = new HashMap<>();

    public static Map<DyeColor, Item> getWoolFromColor() {
        ColorToWool.put(DyeColor.BLACK, Items.BLACK_WOOL);
        ColorToWool.put(DyeColor.PINK, Items.PINK_WOOL);
        ColorToWool.put(DyeColor.PURPLE, Items.PURPLE_WOOL);
        ColorToWool.put(DyeColor.BLUE, Items.BLUE_WOOL);
        ColorToWool.put(DyeColor.BROWN, Items.BROWN_WOOL);
        ColorToWool.put(DyeColor.CYAN, Items.CYAN_WOOL);
        ColorToWool.put(DyeColor.GREEN, Items.GREEN_WOOL);
        ColorToWool.put(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_WOOL);
        ColorToWool.put(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_WOOL);
        ColorToWool.put(DyeColor.LIME, Items.LIME_WOOL);
        ColorToWool.put(DyeColor.MAGENTA, Items.MAGENTA_WOOL);
        ColorToWool.put(DyeColor.ORANGE, Items.ORANGE_WOOL);
        ColorToWool.put(DyeColor.RED, Items.RED_WOOL);
        ColorToWool.put(DyeColor.WHITE, Items.WHITE_WOOL);
        ColorToWool.put(DyeColor.YELLOW, Items.YELLOW_WOOL);
        ColorToWool.put(DyeColor.GRAY, Items.GRAY_WOOL);
        return ColorToWool;
    }

    private static final Map<DyeColor, Item> ColorToPowder = new HashMap<>();

    public static Map<DyeColor, Item> getConcretePowderFromColor() {
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
        ColorToPowder.put(DyeColor.YELLOW, Items.YELLOW_CONCRETE_POWDER);
        ColorToPowder.put(DyeColor.GRAY, Items.GRAY_CONCRETE_POWDER);
        return ColorToPowder;
    }

    private static final Map<DyeColor, Item> ColorToGlass = new HashMap<>();
    public static Map<DyeColor, Item> getGlassFromColor() {
        ColorToGlass.put(DyeColor.BLACK, Items.BLACK_STAINED_GLASS);
        ColorToGlass.put(DyeColor.PINK, Items.PINK_STAINED_GLASS);
        ColorToGlass.put(DyeColor.PURPLE, Items.PURPLE_STAINED_GLASS);
        ColorToGlass.put(DyeColor.BLUE, Items.BLUE_STAINED_GLASS);
        ColorToGlass.put(DyeColor.BROWN, Items.BROWN_STAINED_GLASS);
        ColorToGlass.put(DyeColor.CYAN, Items.CYAN_STAINED_GLASS);
        ColorToGlass.put(DyeColor.GREEN, Items.GREEN_STAINED_GLASS);
        ColorToGlass.put(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_STAINED_GLASS);
        ColorToGlass.put(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_STAINED_GLASS);
        ColorToGlass.put(DyeColor.LIME, Items.LIME_STAINED_GLASS);
        ColorToGlass.put(DyeColor.MAGENTA, Items.MAGENTA_STAINED_GLASS);
        ColorToGlass.put(DyeColor.ORANGE, Items.ORANGE_STAINED_GLASS);
        ColorToGlass.put(DyeColor.RED, Items.RED_STAINED_GLASS);
        ColorToGlass.put(DyeColor.WHITE, Items.WHITE_STAINED_GLASS);
        ColorToGlass.put(DyeColor.YELLOW, Items.YELLOW_STAINED_GLASS);
        ColorToGlass.put(DyeColor.GRAY, Items.GRAY_STAINED_GLASS);
        return ColorToGlass;
    }
}

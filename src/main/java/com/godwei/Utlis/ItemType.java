package com.godwei.Utlis;

import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public enum ItemType {
    CONCRETE_POWDER(ColoredItems.ColoredConcretePowders),
    GLASS(ColoredItems.ColoredGlass),
    WOOL(ColoredItems.ColoredWools);

    private final List<Item> items;
    ItemType(List<Item> items){
        this.items = items;
    }

    public static final List<ItemType> STAINABLE_ITEM_TYPE_LIST = Arrays.asList(ItemType.WOOL,
            ItemType.CONCRETE_POWDER,
            ItemType.GLASS);

    public List<Item> getItemList(){
        return this.items;
    }
}

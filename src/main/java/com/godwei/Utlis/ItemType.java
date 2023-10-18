package com.godwei.Utlis;

import net.minecraft.item.Item;

import java.util.List;

public enum ItemType {
    CONCRETE_POWDER(ColoredItems.ColoredConcretePowders),
    GLASS(ColoredItems.ColoredGlass),
    WOOL(ColoredItems.ColoredWools);

    private final List<Item> items;
    ItemType(List<Item> items){
        this.items = items;
    }

    public List<Item> getItemList(){
        return this.items;
    }
}

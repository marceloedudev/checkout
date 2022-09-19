package com.github.marceloedudev.unit.domain.entity.Item;

import com.github.marceloedudev.domain.entity.Item;

public class ItemObjectMother {
    public static Item valid() {
        return ItemDataBuilder
                .create()
                .withValidDescription()
                .withValidPrice()
                .withValidWidth()
                .withValidHeight()
                .withValidLength()
                .withValidWeight()
                .withValidVolume()
                .withValidDensity()
                .build();
    }
    public static Item validAndItemId() {
        return ItemDataBuilder
                .create()
                .withValidIdItem()
                .withValidDescription()
                .withValidPrice()
                .withValidWidth()
                .withValidHeight()
                .withValidLength()
                .withValidWeight()
                .withValidVolume()
                .withValidDensity()
                .build();
    }

    public static Item validAndItemIdEx() {
        return ItemDataBuilder
                .create()
                .withValidIdItemEx()
                .withValidDescriptionEx()
                .withValidPriceEx()
                .withValidWidth()
                .withValidHeight()
                .withValidLength()
                .withValidWeight()
                .withValidVolume()
                .withValidDensity()
                .build();
    }
}

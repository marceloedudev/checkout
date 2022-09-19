package com.github.marceloedudev.unit.domain.entity.Item;

import com.github.marceloedudev.domain.entity.Item;

public class ItemDataBuilder {

    private Item item;
    public ItemDataBuilder() {
        item = new Item();
    }

    public static ItemDataBuilder create() {
        return new ItemDataBuilder();
    }

    public ItemDataBuilder withValidIdItem() {
        item.setIdItem(2L);
        return this;
    }

    public ItemDataBuilder withValidIdItemEx() {
        item.setIdItem(5L);
        return this;
    }

    public ItemDataBuilder withValidDescription() {
        item.setDescription("Headsets");
        return this;
    }

    public ItemDataBuilder withValidDescriptionEx() {
        item.setDescription("Keyboards");
        return this;
    }


    public ItemDataBuilder withValidPrice() {
        item.setPrice(300.0);
        return this;
    }

    public ItemDataBuilder withValidPriceEx() {
        item.setPrice(450.60);
        return this;
    }

    public ItemDataBuilder withValidWidth() {
        item.setWidth(10);
        return this;
    }

    public ItemDataBuilder withValidHeight() {
        item.setHeight(20);
        return this;
    }

    public ItemDataBuilder withValidLength() {
        item.setLength(8);
        return this;
    }

    public ItemDataBuilder withValidWeight() {
        item.setWeight(4);
        return this;
    }

    public ItemDataBuilder withValidVolume() {
        item.setVolume(4);
        return this;
    }

    public ItemDataBuilder withValidDensity() {
        item.setDensity(4);
        return this;
    }

    public Item build() {
        return item;
    }

}

package com.github.marceloedudev.unit.domain.entity.OrderItem;

import com.github.marceloedudev.domain.entity.OrderItem;

public class OrderItemObjectMother {
    public static OrderItem valid() {
        return OrderItemDataBuilder
                .create()
                .withValidIdItem()
                .withValidPrice()
                .withValidQuantity()
                .build();
    }

    public static OrderItem invalidQuantity() {
        return OrderItemDataBuilder
                .create()
                .withValidIdItem()
                .withValidPrice()
                .withInvalidQuantity()
                .build();
    }
}

package com.github.marceloedudev.unit.domain.entity.Order;

import com.github.marceloedudev.domain.entity.Order;

public class OrderObjectMother {
    public static Order validAndWithoutItem() {
        return OrderDataBuilder.create()
                .withValidUserId()
                .withValidCpf()
                .build();
    }

    public static Order validAndWithItem() {
        return OrderDataBuilder.create()
                .withValidUserId()
                .withValidCpf()
                .withValidItem()
                .build();
    }

    public static Order withItemsDuplicated() {
        return OrderDataBuilder.create()
                .withValidUserId()
                .withValidCpf()
                .withItemsDuplicated()
                .build();
    }

    public static Order withItemsInvalidQuantity() {
        return OrderDataBuilder.create()
                .withValidUserId()
                .withValidCpf()
                .withValidItemInvalidQuantity()
                .build();
    }

    public static Order withItemsValid() {
        return OrderDataBuilder.create()
                .withValidUserId()
                .withValidCpf()
                .withItemsValid()
                .build();
    }
}

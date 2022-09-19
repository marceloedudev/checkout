package com.github.marceloedudev.unit.domain.entity.OrderItem;

import com.github.marceloedudev.domain.entity.OrderItem;

public class OrderItemDataBuilder {

    private OrderItem orderItem;

    public OrderItemDataBuilder() {
        orderItem = new OrderItem();
    }

    public static OrderItemDataBuilder create() {
        return new OrderItemDataBuilder();
    }

    public OrderItemDataBuilder withValidIdItem() {
        orderItem.setIdItem(1L);
        return this;
    }

    public OrderItemDataBuilder withValidPrice() {
        orderItem.setPrice(300.40);
        return this;
    }

    public OrderItemDataBuilder withValidQuantity() {
        orderItem.setQuantity(10);
        return this;
    }

    public OrderItemDataBuilder withInvalidQuantity() {
        orderItem.setQuantity(-1);
        return this;
    }

    public OrderItem build() {
        return orderItem;
    }
}

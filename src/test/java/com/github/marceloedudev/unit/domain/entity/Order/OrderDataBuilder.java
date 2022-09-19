package com.github.marceloedudev.unit.domain.entity.Order;

import com.github.marceloedudev.domain.entity.Order;
import com.github.marceloedudev.unit.domain.entity.Cpf.CpfObjectMother;
import com.github.marceloedudev.unit.domain.entity.Item.ItemObjectMother;

public class OrderDataBuilder {

    private Order order;
    public OrderDataBuilder() {
        this.order = new Order();
    }

    public static OrderDataBuilder create() {
        return new OrderDataBuilder();
    }

    public OrderDataBuilder withValidUserId() {
        order.setUserId(1L);
        return this;
    }

    public OrderDataBuilder withValidCpf() {
        order.setCpf(CpfObjectMother.valid().getValue());
        return this;
    }

    public OrderDataBuilder withValidItem() {
        order.addItem(ItemObjectMother.validAndItemId(), 1);
        return this;
    }

    public OrderDataBuilder withValidItemInvalidQuantity() {
        order.addItem(ItemObjectMother.validAndItemId(), -1);
        return this;
    }

    public OrderDataBuilder withItemsDuplicated() {
        order.addItem(ItemObjectMother.validAndItemId(), 1);
        order.addItem(ItemObjectMother.validAndItemId(), 1);
        return this;
    }

    public OrderDataBuilder withItemsValid() {
        order.addItem(ItemObjectMother.validAndItemId(), 1);
        order.addItem(ItemObjectMother.validAndItemIdEx(), 1);
        return this;
    }

    public Order build() {
        return order;
    }
}

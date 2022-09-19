package com.github.marceloedudev.application.dto;

import com.github.marceloedudev.domain.entity.Order;

public class GetOrderOutput {

    private Order order;

    public GetOrderOutput() {
    }

    public GetOrderOutput(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

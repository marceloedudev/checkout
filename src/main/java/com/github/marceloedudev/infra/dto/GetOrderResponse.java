package com.github.marceloedudev.infra.dto;

import com.github.marceloedudev.domain.entity.Order;

public class GetOrderResponse {

    private Order order;

    public GetOrderResponse() {
    }

    public GetOrderResponse(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

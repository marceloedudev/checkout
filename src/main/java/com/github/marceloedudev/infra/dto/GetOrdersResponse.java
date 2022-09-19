package com.github.marceloedudev.infra.dto;

import com.github.marceloedudev.domain.entity.Order;

import java.util.List;

public class GetOrdersResponse {
    private List<Order> order;

    public GetOrdersResponse() {
    }

    public GetOrdersResponse(List<Order> order) {
        this.order = order;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}

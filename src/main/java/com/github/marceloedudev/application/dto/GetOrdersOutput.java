package com.github.marceloedudev.application.dto;

import com.github.marceloedudev.domain.entity.Order;

import java.util.List;

public class GetOrdersOutput {

    private List<Order> orders;

    public GetOrdersOutput() {
    }

    public GetOrdersOutput(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

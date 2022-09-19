package com.github.marceloedudev.infra.dto;

import java.util.List;

public class PlaceOrderRequest {
    private Long userId;

    private String cpf;

    private List<PlaceOrderRequestOrderItems> orderItems;

    public PlaceOrderRequest() {
    }

    public PlaceOrderRequest(Long userId, String cpf, List<PlaceOrderRequestOrderItems> orderItems) {
        this.userId = userId;
        this.cpf = cpf;
        this.orderItems = orderItems;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<PlaceOrderRequestOrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<PlaceOrderRequestOrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "PlaceOrderRequest{" +
                "userId=" + userId +
                ", cpf='" + cpf + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}

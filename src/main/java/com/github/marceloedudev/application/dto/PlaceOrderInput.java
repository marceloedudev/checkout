package com.github.marceloedudev.application.dto;

import java.util.List;

public class PlaceOrderInput {

    private Long userId;

    private String cpf;

    private List<PlaceOrderInputOrderItems> orderItems;

    public PlaceOrderInput() {
    }

    public PlaceOrderInput(Long userId, String cpf, List<PlaceOrderInputOrderItems> orderItems) {
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

    public List<PlaceOrderInputOrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<PlaceOrderInputOrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "PlaceOrderInput{" +
                "userId=" + userId +
                ", cpf='" + cpf + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}

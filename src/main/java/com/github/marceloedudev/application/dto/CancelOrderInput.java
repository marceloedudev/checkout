package com.github.marceloedudev.application.dto;

public class CancelOrderInput {
    private Long orderId;

    public CancelOrderInput(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}

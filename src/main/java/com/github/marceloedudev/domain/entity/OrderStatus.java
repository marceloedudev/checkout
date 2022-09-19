package com.github.marceloedudev.domain.entity;

import com.github.marceloedudev.domain.errors.http.BadRequestException;

public enum OrderStatus {
    WAITING(1),
    CANCELED(2),
    PURCHASED(3),
    SHIPPED(4),
    DELIVERED(5),
    RETURNED(6);

    private int code;

    private OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new BadRequestException("Invalid order status code");
    }
}

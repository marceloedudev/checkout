package com.github.marceloedudev.domain.queries;

import com.github.marceloedudev.domain.errors.base.NotificationErrors;

import java.util.List;

public class GetOrder {

    private Long orderId;

    private Long userId;

    private NotificationErrors notifications;

    public GetOrder() {
        notifications = new NotificationErrors();
    }

    public GetOrder(Long orderId, Long userId) {
        this.orderId = orderId;
        this.userId = userId;
        notifications = new NotificationErrors();
    }

    public boolean validate() {
        if (orderId == null || orderId <= 0) {
            notifications.addError("Order id is required");
        }
        if (userId == null || userId <= 0) {
            notifications.addError("User id is required");
        }
        return notifications.hasErrors();
    }

    public List<String> getNotifications() {
        return notifications.getErrorsMessage();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

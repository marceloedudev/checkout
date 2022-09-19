package com.github.marceloedudev.domain.queries;

import com.github.marceloedudev.domain.errors.base.NotificationErrors;

import java.util.List;

public class GetOrders {
    private Long userId;
    private Integer page;
    private Integer size;

    private NotificationErrors notifications;

    public GetOrders() {
        notifications = new NotificationErrors();
    }

    public GetOrders(Long userId, Integer page, Integer size) {
        this.userId = userId;
        this.page = page;
        this.size = size;
        notifications = new NotificationErrors();
    }

    public boolean validate() {
        if (userId == null || userId <= 0) {
            notifications.addError("User id is required");
        }
        if (page < 0) {
            notifications.addError("Page must be greater than or equal to 0");
        }
        if (size <= 0) {
            notifications.addError("Size must be greater than 0");
        }
        return notifications.hasErrors();
    }

    public List<String> getNotifications() {
        return notifications.getErrorsMessage();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}

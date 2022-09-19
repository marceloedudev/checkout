package com.github.marceloedudev.domain.entity;


import com.github.marceloedudev.domain.errors.base.NotificationErrors;

public class Pagination {

    private int page;
    private int size;
    private NotificationErrors notifications;

    public Pagination(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public NotificationErrors validation() {
        if (page < 0) {
            notifications.addError("Page must be greater than or equal to 0");
        }
        if (size <= 0) {
            notifications.addError("Size must be greater than 0");
        }
        return notifications;
    }

    public NotificationErrors getNotifications() {
        return notifications;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

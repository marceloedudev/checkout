package com.github.marceloedudev.application.dto;

public class GetOrdersInput {
    private Long userId;
    private Integer page;
    private Integer size;

    public GetOrdersInput() {
    }

    public GetOrdersInput(Long userId, Integer page, Integer size) {
        this.userId = userId;
        this.page = page;
        this.size = size;
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

    @Override
    public String toString() {
        return "GetOrdersInput{" +
                "userId=" + userId +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}

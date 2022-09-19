package com.github.marceloedudev.domain.entity;

import com.github.marceloedudev.domain.errors.base.NotificationErrors;
import com.github.marceloedudev.domain.errors.http.BadRequestException;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Long idOrder;
    private ArrayList<OrderItem> orderItems;
    private Long userId;
    private Cpf cpf;
    private int status;
    private NotificationErrors notifications;

    private void setup() {
        this.status = OrderStatus.WAITING.getCode();
        this.orderItems = new ArrayList<>();
        notifications = new NotificationErrors();
    }

    public Order() {
        setup();
        this.cpf = new Cpf();
    }

    public Order(Long userId, String cpf) {
        setup();
        this.userId = userId;
        this.cpf = new Cpf(cpf);
    }

    public boolean validate(int inputItemsSize) {
        if (userId == null || userId <= 0) {
            notifications.addError("User id invalid");
        }
        if (!cpf.validate()) {
            notifications.addError("Invalid cpf");
        }
        if (inputItemsSize <= 0) {
            notifications.addError("Items not found");
        }
        return notifications.hasErrors();
    }

    public List<String> getNotifications() {
        return notifications.getErrorsMessage();
    }

    public void addItem(Item item, int quantity) {
        if (quantity <= 0) {
            throw new BadRequestException("Invalid quantity");
        }
        boolean existsItem = orderItems.stream().anyMatch(itemOrder -> itemOrder.getIdItem().equals(item.getIdItem()));
        if (existsItem) {
            throw new BadRequestException("Duplicated items");
        }
        orderItems.add(item.createOrderItem(quantity));
    }

    public void setItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotal() {
        double total = orderItems.stream()
                .map(item -> item.getTotal())
                .reduce(0.0, Double::sum);
        return total;
    }

    public void cancel() {
        if (status != OrderStatus.WAITING.getCode() && status != OrderStatus.PURCHASED.getCode()) {
            throw new BadRequestException("Invalid operation");
        }
        this.status = OrderStatus.CANCELED.getCode();
    }

    public void setStatus(int status) {
        this.status = OrderStatus.valueOf(status).getCode();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCpf() {
        return cpf.getValue();
    }

    public int getStatus() {
        return status;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCpf(String cpf) {
        this.cpf.setValue(cpf);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", orderItems=" + orderItems +
                ", userId=" + userId +
                ", cpf=" + cpf +
                ", status=" + status +
                ", notifications=" + notifications +
                '}';
    }
}

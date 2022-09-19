package com.github.marceloedudev.domain.entity;

public class OrderItem {

    private Long idItem;
    private double price;
    private int quantity;
    public OrderItem() {
    }

    public OrderItem(Long idItem, double price, int quantity) {
        this.idItem = idItem;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "idItem=" + idItem +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

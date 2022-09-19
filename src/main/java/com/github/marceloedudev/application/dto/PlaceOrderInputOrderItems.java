package com.github.marceloedudev.application.dto;

public class PlaceOrderInputOrderItems {
    private Long idItem;
    private int quantity;

    public PlaceOrderInputOrderItems() {
    }

    public PlaceOrderInputOrderItems(Long idItem, int quantity) {
        this.idItem = idItem;
        this.quantity = quantity;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PlaceOrderInputOrderItems{" +
                "idItem=" + idItem +
                ", quantity=" + quantity +
                '}';
    }
}

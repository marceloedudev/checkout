package com.github.marceloedudev.infra.dto;

public class PlaceOrderRequestOrderItems {
    private Long idItem;
    private int quantity;

    public PlaceOrderRequestOrderItems() {
    }

    public PlaceOrderRequestOrderItems(Long idItem, int quantity) {
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
        return "PlaceOrderRequestOrderItems{" +
                "idItem=" + idItem +
                ", quantity=" + quantity +
                '}';
    }
}

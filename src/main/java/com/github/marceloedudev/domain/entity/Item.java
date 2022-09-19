package com.github.marceloedudev.domain.entity;

public class Item {

    private Long idItem;
    private String description;
    private double price;
    private Integer width;
    private Integer height;
    private Integer length;
    private Integer weight;
    private Integer volume;
    private Integer density;

    public Item() {
    }

    public Item(Long idItem, String description, double price, Integer width, Integer height, Integer length, Integer weight, Integer volume, Integer density) {
        this.idItem = idItem;
        this.description = description;
        this.price = price;
        this.width = width;
        this.height = height;
        this.length = length;
        this.weight = weight;
        this.volume = volume;
        this.density = density;
    }

    public OrderItem createOrderItem(int quantity) {
        return new OrderItem(idItem, price, quantity);
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getDensity() {
        return density;
    }

    public void setDensity(Integer density) {
        this.density = density;
    }
}

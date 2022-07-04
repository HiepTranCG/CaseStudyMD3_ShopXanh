package com.example.casestudymd3_shopxanh.model;

public class OrderDetail {
    private Long id;
    private Long productId;
    private Long orderId;
    private Long amount;
    private Double price;

    public OrderDetail(Long productId, Long orderId, Long amount, Double price) {
        this.productId = productId;
        this.orderId = orderId;
        this.amount = amount;
        this.price = price;
    }

    public OrderDetail() {
    }

    public OrderDetail(Long id, Long productId, Long orderId, Long amount, Double price) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.amount = amount;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

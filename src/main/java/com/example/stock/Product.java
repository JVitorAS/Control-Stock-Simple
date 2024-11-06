package com.example.stock;

public class Product {
    private Integer id;
    private String code;
    private String product;
    private Integer quantity;
    private String um;
    private Double price;

    // Construtor
    public Product(int id, String code, String product, int quantity, String um, double price) {
        this.id = id;
        this.code = code;
        this.product = product;
        this.quantity = quantity;
        this.um = um;
        this.price = price;
    }


    // Métodos de acesso (getters)
    public Integer getId() {return id;}
    public String getCode() {
        return code;
    }

    public String getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getUm() {
        return um;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setValues(String code, String product, int quantity, String um, double price) {
        this.code = code;
        this.product = product;
        this.quantity = quantity;
        this.um = um;
        this.price = price;
    }
}

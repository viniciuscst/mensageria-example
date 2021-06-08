package com.example.mensageria.invoice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;


@Document(collection = "products")
public class Product {

    public Product(String key, Long id, String name, String description, BigDecimal price, boolean hasInvoice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.hasInvoice = hasInvoice;
    }

    public Product(Long id, String name, String description, BigDecimal price, boolean hasInvoice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.hasInvoice = hasInvoice;
    }

    public Product() {

    }

    @Id
    private String key;
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean hasInvoice;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isHasInvoice() {
        return hasInvoice;
    }

    public void setHasInvoice(boolean hasInvoice) {
        this.hasInvoice = hasInvoice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "key='" + key + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", hasInvoice=" + hasInvoice +
                '}';
    }
}

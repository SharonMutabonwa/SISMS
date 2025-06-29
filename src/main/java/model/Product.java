/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Odeth
 */

import java.sql.Date;
import java.time.LocalDate;

public class Product {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private double price;
    private int supplierId;
    private LocalDate expiryDate;
    private String createdBy;
    private String updatedBy;

    // Constructors
    public Product() {}

    public Product(String name, String category, int quantity, double price, int supplierId, LocalDate expiryDate, String createdBy) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.supplierId = supplierId;
        this.expiryDate = expiryDate;
        this.createdBy = createdBy;
    }

    public Product(String name, String category, double price, int qty, Date expiryDate, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Product(int selectedProductId, String name, String category, double price, int qty, Date expiryDate, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
}

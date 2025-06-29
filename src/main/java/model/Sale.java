/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Odeth
 */
import java.time.LocalDateTime;

public class Sale {
    private int id;
    private int productId;
    private int quantitySold;
    private double totalPrice;
    private LocalDateTime saleDate;
    private String soldBy;

    public Sale() {}

    
     
    public Sale(int productId, int quantitySold, double totalPrice, String soldBy) {
    this.productId = productId;
    this.quantitySold = quantitySold;
    this.totalPrice = totalPrice;
    this.saleDate = LocalDateTime.now();
    this.soldBy = soldBy;
}

public Sale(int id, int productId, int quantitySold, double totalPrice, LocalDateTime saleDate, String soldBy) {
    this.id = id;
    this.productId = productId;
    this.quantitySold = quantitySold;
    this.totalPrice = totalPrice;
    this.saleDate = saleDate;
    this.soldBy = soldBy;
}



    // Getters and Setters

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }
    
    public String getSoldBy() {
        return soldBy;
    }

    public void setSoldBy(String soldBy) {
        this.soldBy = soldBy;
    }

}


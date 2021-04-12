/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblProduct;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author MONS
 */
public class TblProductDTO implements Serializable{
    private String productID;
    private String productName;
    private String productImage;
    private String productColor;
    private int year;
    private float productPrice;
    private String category;
    private String userUpdate;
    private int quantity;
    private int quancart;
    private boolean status;
    private String rentalDate;
    private String returnDate;
    private long days;

    public TblProductDTO() {
    }

    
    public TblProductDTO(String productID, String productName, String productImage, float productPrice, String userUpdate, String category, int quantity, int quancart, String rentalDate,String returnDate,long days ) {
        this.productID = productID;
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.userUpdate = userUpdate;
        this.category = category;
        this.quantity = quantity;
        this.quancart = quancart;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.days = days;
    }

    public TblProductDTO(String productID, String productName, String productImage, String productColor, int year, float productPrice, String category, String userUpdate, int quantity, boolean status) {
        this.productID = productID;
        this.productName = productName;
        this.productImage = productImage;
        this.productColor = productColor;
        this.year = year;
        this.productPrice = productPrice;
        this.category = category;
        this.userUpdate = userUpdate;
        this.quantity = quantity;
        this.status = status;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    
    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    public int getQuancart() {
        return quancart;
    }

    public void setQuancart(int quancart) {
        this.quancart = quancart;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}

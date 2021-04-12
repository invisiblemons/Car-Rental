/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblOrderDetail;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author MONS
 */
public class TblOrderDetailDTO implements Serializable {

    private int orderID;
    private String carID;
    private int quantity;
    private float price;
    private Date rentalDate;
    private Date returnDate;
    private String nameCar;
    private String image;
    private String nameCategory;
    private boolean status;

    public TblOrderDetailDTO() {
    }

    public TblOrderDetailDTO(int orderID, String carID, int quantity, float price, Date rentalDate, Date returnDate, boolean status) {
        this.orderID = orderID;
        this.carID = carID;
        this.quantity = quantity;
        this.price = price;
        this.rentalDate = rentalDate;
        this.status = status;
        
    }

    public TblOrderDetailDTO(int orderID, String carID, int quantity, float price, Date rentalDate, Date returnDate, String nameCar, String image, String nameCategory) {
        this.orderID = orderID;
        this.carID = carID;
        this.quantity = quantity;
        this.price = price;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.nameCar = nameCar;
        this.image = image;
        this.nameCategory = nameCategory;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

}

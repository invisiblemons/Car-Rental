/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblOrder;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author MONS
 */
public class TblOrderDTO implements Serializable{
    private int id;
    private String userID;
    private Date dateOrder;
    private boolean status;
    private float totalPrice;

    public TblOrderDTO() {
    }

    public TblOrderDTO(int id, String userID, Date dateOrder, boolean status, float totalPrice) {
        this.id = id;
        this.userID = userID;
        this.dateOrder = dateOrder;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}

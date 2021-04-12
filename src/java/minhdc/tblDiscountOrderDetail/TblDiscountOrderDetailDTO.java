/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblDiscountOrderDetail;

import java.io.Serializable;

/**
 *
 * @author MONS
 */
public class TblDiscountOrderDetailDTO implements Serializable{
    private int orderID;
    private String discountID;
    private float value;

    public TblDiscountOrderDetailDTO() {
    }

    public TblDiscountOrderDetailDTO(int orderID, String discountID, float value) {
        this.orderID = orderID;
        this.discountID = discountID;
        this.value = value;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
}

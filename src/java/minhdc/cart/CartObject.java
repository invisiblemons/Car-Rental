/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import minhdc.tblProduct.TblProductDTO;

/**
 *
 * @author MONS
 */
public class CartObject implements Serializable{
    private Map<String, TblProductDTO> cartProduct;

    public Map<String, TblProductDTO> getCartProduct() {
        return cartProduct;
    }
    public void addProductToCart(TblProductDTO dto){
        if(this.cartProduct == null){
            this.cartProduct = new HashMap<>();
        }
        if(cartProduct.containsKey(dto.getProductID())){
            if(dto.getQuantity()>cartProduct.get(dto.getProductID()).getQuancart())
                dto.setQuancart(cartProduct.get(dto.getProductID()).getQuancart()+dto.getQuancart());
            if(dto.getQuantity()==cartProduct.get(dto.getProductID()).getQuancart())
                dto.setQuancart(cartProduct.get(dto.getProductID()).getQuancart());
        }
        cartProduct.put(dto.getProductID(), dto);
    }
    
    public void removeProductFromCart(String productID){
        if(this.cartProduct==null){
            return;
        }
        if(cartProduct.containsKey(productID)){
            cartProduct.remove(productID);
            if(cartProduct.isEmpty()){
                cartProduct=null;
            }
        }
    }
}

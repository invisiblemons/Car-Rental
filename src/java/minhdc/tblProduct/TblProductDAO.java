/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblProduct;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import minhdc.ultils.DBUtils;

/**
 *
 * @author MONS
 */
public class TblProductDAO implements Serializable {

    public ArrayList<TblProductDTO> loadStore(boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblProductDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            String sql;
            if (role)//is admin
            {
                sql = "SELECT  R.id, R.name, L.colorName, R.image, Y.year, R.price,  C.categoryName, R.userUpdate, R.quantity , R.status "
                        + "FROM tblCar R JOIN tblCategory C ON R.categoryID = C.id JOIN tblColor L ON R.colorID = L.id JOIN tblYear Y ON R.yearID = Y.id "
                        + "ORDER BY convert(date,R.date,105) DESC ";
            } else//is customer
            {
                sql = "SELECT  R.id, R.name, L.colorName, R.image, Y.year, R.price,  C.categoryName, R.userUpdate, R.quantity, R.status "
                        + "FROM tblCar R JOIN tblCategory C ON R.categoryID = C.id JOIN tblColor L ON R.colorID = L.id JOIN tblYear Y ON R.yearID = Y.id "
                        + "WHERE R.quantity > 0 AND R.status = 1 "
                        + "ORDER BY convert(date,R.date,105) DESC";
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("id");
                String productName = rs.getString("name");
                String productColor = rs.getString("colorName");
                String productImage = rs.getString("image");
                int year = rs.getInt("year");
                float productPrice = rs.getFloat("price");
                String category = rs.getString("categoryName");
                String userUpdate = rs.getString("userUpdate");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                TblProductDTO dto = new TblProductDTO(productID, productName, productImage, productColor, year, productPrice, category, userUpdate, quantity, status);
                list.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public ArrayList<TblProductDTO> loaListProduct(String searchValue, String searchQuantiy, String rentalExp, String returnExp, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblProductDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            String sql;
            if (isAdmin)//is admin
            {
                sql = "SELECT  R.id, R.name, L.colorName, R.image, Y.year, R.price,  C.categoryName, R.userUpdate, R.quantity, R.status "
                        + "FROM tblCar R JOIN tblCategory C ON R.categoryID = C.id JOIN tblColor L ON R.colorID = L.id JOIN tblYear Y ON R.yearID = Y.id ";
            } else//is customer
            {
                sql = "SELECT  R.id, R.name, L.colorName, R.image, Y.year, R.price,  C.categoryName, R.userUpdate, R.quantity, R.status "
                        + "FROM tblCar R JOIN tblCategory C ON R.categoryID = C.id JOIN tblColor L ON R.colorID = L.id JOIN tblYear Y ON R.yearID = Y.id "
                        + "WHERE R.quantity > 0";
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("id");
                String productName = rs.getString("name");
                String productColor = rs.getString("colorName");
                String productImage = rs.getString("image");
                int year = rs.getInt("year");
                float productPrice = rs.getFloat("price");
                String category = rs.getString("categoryName");
                String userUpdate = rs.getString("userUpdate");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                TblProductDTO dto = new TblProductDTO(productID, productName, productImage, productColor, year, productPrice, category, userUpdate, quantity, status);
                list.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
    
    public ArrayList<TblProductDTO> loaListProducts(String categoryValue, String searchQuantiy, String rentalExp, String returnExp, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblProductDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            String sql;
            if (isAdmin)//is admin
            {
                sql = "SELECT  R.id, R.name, L.colorName, R.image, Y.year, R.price, C.categoryName, R.userUpdate, R.quantity, R.status "
                        + "FROM tblCar R JOIN tblCategory C ON R.categoryID = C.id JOIN tblColor L ON R.colorID = L.id JOIN tblYear Y ON R.yearID = Y.id ";
            } else//is customer
            {
                sql = "SELECT  R.id, R.name, L.colorName, R.image, Y.year, R.price, C.categoryName, R.userUpdate, R.quantity, R.status "
                        + "FROM tblCar R JOIN tblCategory C ON R.categoryID = C.id JOIN tblColor L ON R.colorID = L.id JOIN tblYear Y ON R.yearID = Y.id "
                        + "WHERE R.quantity > 0";
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("id");
                String productName = rs.getString("name");
                String productColor = rs.getString("colorName");
                String productImage = rs.getString("image");
                int year = rs.getInt("year");
                float productPrice = rs.getFloat("price");
                String category = rs.getString("categoryName");
                String userUpdate = rs.getString("userUpdate");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                TblProductDTO dto = new TblProductDTO(productID, productName, productImage, productColor, year, productPrice, category, userUpdate, quantity, status);
                list.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public boolean updateQuantity(String id, int quantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DBUtils.makeConnection();
            String sql = "UPDATE dbo.tblCar\n "
                    + "SET quantity = ?\n "
                    + "WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(2, id);
            ps.setInt(1, quantity);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    public boolean updateStatus(String id, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DBUtils.makeConnection();
            String sql = "UPDATE dbo.tblCar\n "
                    + "SET status = ?\n "
                    + "WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(2, id);
            ps.setBoolean(1, status);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public TblProductDTO getCar(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TblProductDTO dto = new TblProductDTO();
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT  R.id, R.name, L.colorName, R.image, Y.year, R.price,  C.categoryName, R.userUpdate, R.quantity , R.status "
                    + "FROM tblCar R JOIN tblCategory C ON R.categoryID = C.id JOIN tblColor L ON R.colorID = L.id JOIN tblYear Y ON R.yearID = Y.id "
                    + "WHERE  R.id = ?";
            
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("id");
                String productName = rs.getString("name");
                String productColor = rs.getString("colorName");
                String productImage = rs.getString("image");
                int year = rs.getInt("year");
                float productPrice = rs.getFloat("price");
                String category = rs.getString("categoryName");
                String userUpdate = rs.getString("userUpdate");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                dto = new TblProductDTO(productID, productName, productImage, productColor, year, productPrice, category, userUpdate, quantity, status);
                
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }
}

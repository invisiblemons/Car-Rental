/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblOrderDetail;

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
public class TblOrderDetailDAO implements Serializable {

    public boolean insertRecord(int id, String userID, int quantity, float price, String rentalDate, String returnDate, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtils.makeConnection();
            String sql = "INSERT INTO tblOrderDetail \n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, userID);
            ps.setInt(3, quantity);
            ps.setFloat(4, price);
            ps.setString(5, rentalDate);
            ps.setString(6, returnDate);
            ps.setBoolean(7, status);
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

    public ArrayList<TblOrderDetailDTO> loadOrderDetail() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblOrderDetailDTO> list = new ArrayList<>();
        java.util.Date date = new java.util.Date();
        Date buyDate = new Date(date.getTime());
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT orderID, carID, quantity, price, rentalDate, returnDate, status "
                    + "FROM tblOrderDetail "
                    + "WHERE returnDate < ? AND status=1";

            ps = con.prepareStatement(sql);
            ps.setDate(1, buyDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String carID = rs.getString("carID");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                Date rentalDate = rs.getDate("rentalDate");
                Date returnDate = rs.getDate("returnDate");
                boolean status = rs.getBoolean("status");
                TblOrderDetailDTO dto = new TblOrderDetailDTO(orderID, carID, quantity, price, rentalDate, returnDate, status);
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

    public ArrayList<TblOrderDetailDTO> loadOrderDetail(int id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblOrderDetailDTO> list = new ArrayList<>();
        java.util.Date date = new java.util.Date();
        Date buyDate = new Date(date.getTime());
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT D.orderID, D.carID, D.quantity, D.price, D.rentalDate, D.returnDate, C.name, C.image, G.categoryName "
                    + "FROM tblOrderDetail D JOIN tblCar C ON D.carID = C.id JOIN tblCategory G ON C.categoryID = G.id "
                    + "WHERE D.orderID = ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String carID = rs.getString("carID");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                Date rentalDate = rs.getDate("rentalDate");
                Date returnDate = rs.getDate("returnDate");
                String name = rs.getString("name");
                String image = rs.getString("image");
                String nameC = rs.getString("categoryName");
                TblOrderDetailDTO dto = new TblOrderDetailDTO(orderID, carID, quantity, price, rentalDate, returnDate, name, image, nameC);
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

    public boolean updateStatus(int ID, String id, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtils.makeConnection();
            String sql = "UPDATE dbo.tblOrderDetail\n "
                    + "SET status = ?\n "
                    + "WHERE orderID = ? AND carID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(2, ID);
            ps.setString(3, id);
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

    
}

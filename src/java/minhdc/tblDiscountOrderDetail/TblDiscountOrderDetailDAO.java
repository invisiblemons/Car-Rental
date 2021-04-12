/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblDiscountOrderDetail;

import java.io.Serializable;
import java.sql.Connection;
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
public class TblDiscountOrderDetailDAO implements Serializable {

    public boolean insertRecord(int id, String discountID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtils.makeConnection();
            String sql = "INSERT INTO tblDiscountOrderDetail \n"
                    + "VALUES (?, ?);";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, discountID);
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

    public ArrayList<TblDiscountOrderDetailDTO> loadOrders(int orderID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblDiscountOrderDetailDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT  O.orderID, O.discountID , D.value "
                    + "FROM tblDiscountOrderDetail O JOIN tblDiscount D ON O.discountID = D.code "
                    + "WHERE O.orderID = ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, orderID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("orderID");
                String ID = rs.getString("discountID");

                float totalPrice = rs.getFloat("value");
                TblDiscountOrderDetailDTO dto = new TblDiscountOrderDetailDTO(orderID, ID, totalPrice);
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
}

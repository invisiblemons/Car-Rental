/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblOrder;

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
public class TblOrderDAO implements Serializable{
    public boolean insertRecord(String userID,Date date,float price, boolean status) throws SQLException, NamingException
    {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DBUtils.makeConnection();
            String sql = "INSERT INTO tblOrder \n" +
                            "VALUES (?, ?, ?, ?);";
            ps = con.prepareStatement(sql);
            ps.setString(1, userID);
            ps.setDate(2, date);
            ps.setFloat(3, price);
            ps.setBoolean(4, status);
            int row = ps.executeUpdate();
            if(row>0)
            {
                return true;
            }
        } finally {
            if(ps!=null)
                ps.close();
            if(con!=null)
                con.close();
        }
        return false;
    }
    public int getId() throws SQLException, NamingException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT TOP 1 id\n" +
                            "  FROM tblOrder\n" +
                            " ORDER BY Id DESC;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next())
            {
                id = rs.getInt("id");
                
            }
            
        } finally {
            if(rs!=null)
                rs.close();
            if(ps!=null)
                ps.close();
            if(con!=null)
                con.close();
        } return id;
    }
    public ArrayList<TblOrderDTO> loadOrders(String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblOrderDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT  id, userID, dateOrder, totalPrice, status  "
                        + "FROM tblOrder "
                    + "WHERE userID = ? AND status = 1"
                    + "ORDER BY  id DESC";
            
            ps = con.prepareStatement(sql);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                 int id = rs.getInt("id");
     String ID = rs.getString("userID");
     Date dateOrder = rs.getDate("dateOrder");
     boolean status = rs.getBoolean("status");
     float totalPrice = rs.getFloat("totalPrice");
     TblOrderDTO dto = new TblOrderDTO(id, ID, dateOrder, status, totalPrice);
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
    public boolean updateOrder(int ID, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.makeConnection();
            String sql = " UPDATE dbo.tblOrder\n "
                    + "SET status = ?\n "
                    + "WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(2, ID);
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

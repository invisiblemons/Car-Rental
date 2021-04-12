/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblUser;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import minhdc.ultils.DBUtils;

/**
 *
 * @author MONS
 */
public class TblUserDAO implements Serializable {

    public TblUserDTO checkLogin(String userID, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT userID, password, fullName, isAdmin "
                        + "FROM dbo.tblUser "
                        + "WHERE userID = ? "
                        + "AND password = ? COLLATE SQL_Latin1_General_CP1_CS_AS";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    TblUserDTO dto = new TblUserDTO(rs.getString("userID"), rs.getString("password"),
                            rs.getString("fullName"), rs.getBoolean("isAdmin"));
                    return dto;
                }
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
        return null;
    }

    public boolean createNewAccount(String userID, String password, String fullname, boolean role, boolean status, String phone, String address, Date createDate, String code) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "Insert Into tblUser(userID, password, fullName, isAdmin, status, phone, address, createDate, code) "
                    + "Values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, userID);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.setBoolean(4, role);
            ps.setBoolean(5, status);
            ps.setString(6, phone);
            ps.setString(7, address);
            ps.setDate(8, createDate);
            ps.setString(9, code);
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

    public String getCode(String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String code = "";
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT code "
                        + "FROM dbo.tblUser "
                        + "WHERE userID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println(userID);
                    code = rs.getString("code");
                    System.out.println(code);
                }
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
        return code;
    }

    public boolean getStatus(String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean status = false;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT status "
                        + "FROM dbo.tblUser "
                        + "WHERE userID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    status = rs.getBoolean("status");
                }
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
        return status;
    }

    public boolean updateAccount(String userID, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.makeConnection();
            String sql = " UPDATE dbo.tblUser\n "
                    + "SET status = ?\n "
                    + "WHERE userID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(2, userID);
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

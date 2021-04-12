/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblDiscount;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import minhdc.tblProduct.TblProductDTO;
import minhdc.ultils.DBUtils;

/**
 *
 * @author MONS
 */
public class TblDiscountDAO implements Serializable{
    public float loadValue(String code, Date day) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        float value = 0;
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT value "
                        + "FROM tblDiscount  "
                        + "WHERE code = ? AND ? >= startDate AND ? <= endDate";
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ps.setDate(2, day);
            ps.setDate(3, day);
            rs = ps.executeQuery();
            if (rs.next()) {
                value = rs.getFloat("value");
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
        return value;
    }
}

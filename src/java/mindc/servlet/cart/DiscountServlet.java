/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindc.servlet.cart;

import java.io.IOException;
import static java.lang.System.out;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.tblDiscount.TblDiscountDAO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "DiscountServlet", urlPatterns = {"/DiscountServlet"})
public class DiscountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("txtDiscountCode");
        String oldValue = request.getParameter("valueDiscount");
        float oldvalue = 0;
        if (null != oldValue) {
            oldvalue = Float.parseFloat(oldValue);
        }
        String userBuyProduct = request.getParameter("userBuyProduct");
        String searchValue = request.getParameter("searchValueName");
        String searchQuantiy = request.getParameter("searchValueQuantity");
        String rentalDate = request.getParameter("rentalDate");
        String returnDate = request.getParameter("returnDate");
        String categorySearch = request.getParameter("categorySearchValue");
        float value = 0;
        float totalDiscount = 0;
        try {

            TblDiscountDAO dao = new TblDiscountDAO();
            java.util.Date date = new java.util.Date();
            Date day = new Date(date.getTime());
            if (null != code) {
                value = dao.loadValue(code, day);
            }
            if (value != 0) {
                HttpSession session = request.getSession();
                session.getAttribute("discountValue");

                Map<String, Float> arr = new HashMap<String, Float>();
                arr = (Map<String, Float>) session.getAttribute("key");
                if (null != arr) {
                    if (arr.keySet().contains(code)) {
                        totalDiscount = oldvalue;
                    } else {
                        arr.put(code, value);

                        for (Float value1 : arr.values()) {
                            totalDiscount += value1;
                        }

                    }
                } else {
                    arr = new HashMap<String, Float>();
                    arr.put(code, value);

                    for (Float value1 : arr.values()) {
                        totalDiscount += value1;
                    }

                }
                session.setAttribute("key", arr);
                session.setAttribute("discountValue", totalDiscount);
            }
        } catch (SQLException e) {
            log("DiscountServlet_SQLex: " + e.getMessage());
        } catch (NamingException e) {
            log("DiscountServlet_NamingEx: " + e.getMessage());
        } finally {
            String urlRewriting = "Search"
                    + "?searchValueName=" + searchValue
                    + "&searchValueQuantity=" + searchQuantiy
                    + "&rentalDate=" + rentalDate
                    + "&returnDate=" + returnDate
                    + "&categorySearchValue=" + categorySearch
                    + "&renting=true"
                    + "&discountCode=" + code
                    + "&edit=true";
            response.sendRedirect(urlRewriting);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

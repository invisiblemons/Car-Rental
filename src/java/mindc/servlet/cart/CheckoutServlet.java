/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindc.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.cart.CartObject;
import minhdc.tblDiscountOrderDetail.TblDiscountOrderDetailDAO;
import minhdc.tblOrder.TblOrderDAO;
import minhdc.tblOrderDetail.TblOrderDetailDAO;
import minhdc.tblProduct.TblProductDAO;
import minhdc.tblProduct.TblProductDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String totalPrice = request.getParameter("totalPrice");
        float totalprice = Float.parseFloat(totalPrice);
        String rentalDay = request.getParameter("rentalDay");
        String returnDay = request.getParameter("returnDay");

        String userBuyProduct = request.getParameter("userBuyProduct");
        String searchValue = request.getParameter("searchValueName");
        String searchQuantiy = request.getParameter("searchValueQuantity");
        String rentalDate = request.getParameter("rentalDate");
        String returnDate = request.getParameter("returnDate");
        String categorySearch = request.getParameter("categorySearchValue");

        String urlRewriting = "Search"
                + "?searchValueName=" + searchValue
                + "&searchValueQuantity=" + searchQuantiy
                + "&rentalDate=" + rentalDate
                + "&returnDate=" + returnDate
                + "&categorySearchValue=" + categorySearch
                + "&edit=true"
                + "&renting=true";
        try {
            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("CARTCUSTOMER");
            if (cart == null) {
                cart = new CartObject();
            }
            java.util.Date date = new java.util.Date();
            Date buyDate = new Date(date.getTime());
            TblOrderDAO dao = new TblOrderDAO();
            if (null != cart.getCartProduct()) {
                if (cart.getCartProduct().size() != 0) {
                    if (dao.insertRecord(userBuyProduct, buyDate, totalprice, true)) {
                        int idOrder = dao.getId();

                        for (TblProductDTO value : cart.getCartProduct().values()) {
                            TblOrderDetailDAO Dao = new TblOrderDetailDAO();
                            Dao.insertRecord(idOrder, value.getProductID(), value.getQuancart(), value.getQuancart() * value.getProductPrice(), value.getRentalDate(), value.getReturnDate(),true);
                            TblProductDAO car = new TblProductDAO();
                            int count = car.getCar(value.getProductID()).getQuantity() - value.getQuancart();
                            if (count <= 0) {
                                car.updateQuantity(value.getProductID(), 0);
                                car.updateStatus(value.getProductID(), false);
                            } else {
                                car.updateQuantity(value.getProductID(), count);
                            }
                        }
                        Map<String, Float> arr = (Map<String, Float>) session.getAttribute("key");
                        if (null != arr) {
                            if (arr.size() != 0) {
                                for (String string : arr.keySet()) {
                                    TblDiscountOrderDetailDAO DAO = new TblDiscountOrderDetailDAO();
                                    DAO.insertRecord(idOrder, string);
                                }
                            }
                        }
                        session.removeAttribute("CARTCUSTOMER");
                        session.removeAttribute("key");
                        session.removeAttribute("discountValue");
                    }
                }
            }

        } catch (SQLException e) {
            log("CheckoutServlet_SQLex: " + e.getMessage());
        } catch (NamingException e) {
            log("CheckoutServlet_NamingEx: " + e.getMessage());
        } finally {

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.history;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.cart.CartObject;
import minhdc.tblDiscountOrderDetail.TblDiscountOrderDetailDAO;
import minhdc.tblDiscountOrderDetail.TblDiscountOrderDetailDTO;
import minhdc.tblOrder.TblOrderDAO;
import minhdc.tblOrder.TblOrderDTO;
import minhdc.tblOrderDetail.TblOrderDetailDAO;
import minhdc.tblOrderDetail.TblOrderDetailDTO;
import minhdc.tblProduct.TblProductDAO;
import minhdc.tblUser.TblUserDAO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "DeleteOrderHistoryServlet", urlPatterns = {"/DeleteOrderHistoryServlet"})
public class DeleteOrderHistoryServlet extends HttpServlet {

    private final String HOME_PAGE = "LoadProductsServlet";

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
        String id = request.getParameter("orderID");

        TblOrderDAO orderDAO = new TblOrderDAO();
        TblOrderDetailDAO DAO = new TblOrderDetailDAO();
        TblDiscountOrderDetailDAO discountDao = new TblDiscountOrderDetailDAO();
        TblProductDAO dao = new TblProductDAO();
        TblUserDAO Dao = new TblUserDAO();

        HttpSession session = request.getSession();
        try {

            orderDAO.updateOrder(Integer.parseInt(id), false);

            request.setAttribute("orderHistoryModal", true);

        } catch (SQLException ex) {
            log("DeleteOrderHistoryServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteOrderHistoryServlet_Naming " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(HOME_PAGE);
            rd.forward(request, response);
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

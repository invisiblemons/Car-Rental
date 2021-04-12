/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindc.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.cart.CartObject;

/**
 *
 * @author MONS
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {

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
        String productID = request.getParameter("productID");
        String productQuanity = request.getParameter("quanCart");
        String userBuyProduct = request.getParameter("userBuyProduct");
        String searchValue = request.getParameter("searchValueName");
        String searchQuantiy = request.getParameter("searchValueQuantity");
        String rentalDate = request.getParameter("rentalDate");
        String returnDate = request.getParameter("returnDate");
        String categorySearch = request.getParameter("categorySearchValue");

        HttpSession session = request.getSession();
        CartObject cart = (CartObject) session.getAttribute("CARTCUSTOMER");
        try {
            if (Integer.parseInt(productQuanity) == 0) {
                if (cart != null) {

                    if (productID != null) {
                        cart.removeProductFromCart(productID);

                        if (cart.getCartProduct() == null) {
                            session.setAttribute("Cart", null);
                        } else {
                            session.setAttribute("Cart", cart);
                        }
                    }
                }
            } else {

                if (cart != null) {
                    cart.getCartProduct().get(productID).setQuancart(Integer.parseInt(productQuanity));
                }
                session.setAttribute("CARTCUSTOMER", cart);
            }
        } finally {
            String urlRewriting = "Search"
                    + "?searchValueName=" + searchValue
                    + "&searchValueQuantity=" + searchQuantiy
                    + "&rentalDate=" + rentalDate
                    + "&returnDate=" + returnDate
                    + "&categorySearchValue=" + categorySearch
                    + "&renting=true"
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

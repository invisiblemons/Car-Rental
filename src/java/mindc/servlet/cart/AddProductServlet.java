/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindc.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.cart.CartObject;
import minhdc.tblProduct.TblProductDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProductServlet"})
public class AddProductServlet extends HttpServlet {

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
        String productName = request.getParameter("productName");
        String productPrice = request.getParameter("productPrice");
        String productCategory = request.getParameter("productCategory");
        String image = request.getParameter("image");
        String quantity = request.getParameter("quantity");
        String rentalDay = request.getParameter("rentalDay");
        String returnDay = request.getParameter("returnDay");
        int quanCart = 1;
        long daysBetween = 1;
        //count days
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        try {
             
            Date dateBefore = dateFormat1.parse(dateFormat1.format(dateFormat.parse(rentalDay)));
            Date dateAfter = dateFormat1.parse(dateFormat1.format(dateFormat.parse(returnDay)));
            long time = dateAfter.getTime() - dateBefore.getTime();
            daysBetween = (time / (1000*60*60*24));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        String userBuyProduct = request.getParameter("userBuyProduct");
        String searchValue = request.getParameter("searchValueName");
        String searchQuantiy = request.getParameter("searchValueQuantity");
        String rentalDate = request.getParameter("rentalDate");
        String returnDate = request.getParameter("returnDate");
        String categorySearch = request.getParameter("categorySearchValue");
        try {
            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("CARTCUSTOMER");
            if (cart == null) {
                cart = new CartObject();
            }
            java.util.Date date = new java.util.Date();
            Date buyDate = new Date(date.getTime());
            TblProductDTO dto = new TblProductDTO(productID, productName, image, Float.parseFloat(productPrice), userBuyProduct, productCategory, Integer.parseInt(quantity), quanCart, rentalDay, returnDay, daysBetween);
            cart.addProductToCart(dto);
            session.setAttribute("CARTCUSTOMER", cart);
        } finally {
            String urlRewriting = "Search"
                    + "?searchValueName=" + searchValue
                    + "&searchValueQuantity=" + searchQuantiy
                    + "&rentalDate=" + rentalDate
                    + "&returnDate=" + returnDate
                    + "&categorySearchValue=" + categorySearch
                    + "&renting=true";
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

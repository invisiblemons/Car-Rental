/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.tblDiscountOrderDetail.TblDiscountOrderDetailDAO;
import minhdc.tblDiscountOrderDetail.TblDiscountOrderDetailDTO;
import minhdc.tblOrder.TblOrderDAO;
import minhdc.tblOrder.TblOrderDTO;
import minhdc.tblOrderDetail.TblOrderDetailDAO;
import minhdc.tblOrderDetail.TblOrderDetailDTO;
import minhdc.tblProduct.TblProductDAO;
import minhdc.tblProduct.TblProductDTO;
import minhdc.tblUser.TblUserDAO;
import minhdc.tblUser.TblUserDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final String SHOPPING_PAGE = "index.jsp";

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
        String searchValueName = request.getParameter("searchValueName");
        String searchValue = request.getParameter("searchValueName");
        String searchQuantiy = request.getParameter("searchValueQuantity");
        String rentalDate = request.getParameter("rentalDate");
        String returnDate = request.getParameter("returnDate");
        String categorySearch = request.getParameter("categorySearchValue");

        String renting = request.getParameter("renting");
        String edit = request.getParameter("edit");
        String orderHistoryModal = request.getParameter("orderHistoryModal");

        HttpSession session = request.getSession();
        session.removeAttribute("ITEMSLIST");
        TblProductDAO dao = new TblProductDAO();
        TblOrderDAO orderDAO = new TblOrderDAO();
        TblOrderDetailDAO DAO = new TblOrderDetailDAO();
        TblDiscountOrderDetailDAO discountDao = new TblDiscountOrderDetailDAO();
        TblUserDAO Dao = new TblUserDAO();
        //chk admin
        boolean isAdmin = false;
        TblUserDTO user = (TblUserDTO) session.getAttribute("USER");
        if (user != null) {
            isAdmin = user.isIsAdmin();
            try {
                ArrayList<TblProductDTO> arrListItem = (ArrayList<TblProductDTO>) session.getAttribute("ITEMSLIST");
                if (arrListItem == null) {
                    arrListItem = new ArrayList<>();
                } else {
                    arrListItem.clear();
                }
                if (!searchValue.equals("")) {
                    arrListItem = dao.loaListProduct(searchValue, searchQuantiy, rentalDate, returnDate, isAdmin);
                } else {
                    arrListItem = dao.loaListProducts(categorySearch, searchQuantiy, rentalDate, returnDate, isAdmin);
                }
                session.setAttribute("ITEMSLIST", arrListItem);

                if (!isAdmin) {
                    session.getAttribute("LISTORDERS");
                    session.getAttribute("LISTORDERSDETAIL");
                    session.getAttribute("LISTDISCOUNTSDETAIL");
                    ArrayList<TblOrderDTO> listOrders = orderDAO.loadOrders(user.getUsername());
                    Map<Integer, ArrayList<TblOrderDetailDTO>> orderDetailMap = new HashMap<>();
                    Map<Integer, ArrayList<TblDiscountOrderDetailDTO>> discountDetailMap = new HashMap<>();
                    for (TblOrderDTO order : listOrders) {
                        ArrayList<TblOrderDetailDTO> listOrdersDetail = DAO.loadOrderDetail(order.getId());
                        orderDetailMap.put(order.getId(), listOrdersDetail);
                        ArrayList<TblDiscountOrderDetailDTO> listDiscounts = discountDao.loadOrders(order.getId());
                        discountDetailMap.put(order.getId(), listDiscounts);
                    }
                    session.setAttribute("LISTORDERS", listOrders);
                    session.setAttribute("LISTORDERSDETAIL", orderDetailMap);
                    session.setAttribute("LISTDISCOUNTSDETAIL", discountDetailMap);
                }

                if (!searchValue.equals("null")) {
                    request.setAttribute("searchValueName", searchValue);
                }
                if (!searchQuantiy.equals("null")) {
                    request.setAttribute("searchValueQuantity", searchQuantiy);
                }
                if (!rentalDate.equals("null")) {
                    request.setAttribute("rentalDate", rentalDate);
                }
                if (!returnDate.equals("null")) {
                    request.setAttribute("returnDate", returnDate);
                }
                if (!categorySearch.equals("null")) {
                    request.setAttribute("categorySearchValue", categorySearch);
                }

                request.setAttribute("ACTION", "searchServlet");

                if (null != edit) {
                    request.setAttribute("edit", true);
                }
                if (null != renting) {
                    request.setAttribute("renting", true);
                }
                if (null != edit) {
                    request.setAttribute("orderHistoryModal", true);
                }

            } catch (SQLException ex) {
                log("SearchServlet_SQL " + ex.getMessage());
            } catch (NamingException ex) {
                log("SearchServlet_Naming " + ex.getMessage());
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(SHOPPING_PAGE);
                rd.forward(request, response);
                out.close();
            }
        } else if (user == null) { // is Customer
            try {
                ArrayList<TblProductDTO> arrListItem = (ArrayList<TblProductDTO>) session.getAttribute("ITEMSLIST");
                if (arrListItem == null) {
                    arrListItem = new ArrayList<>();
                } else {
                    arrListItem.clear();
                }
                if (!searchValue.equals("")) {
                    arrListItem = dao.loaListProduct(searchValue, searchQuantiy, rentalDate, returnDate, false);
                } else {
                    arrListItem = dao.loaListProducts(categorySearch, searchQuantiy, rentalDate, returnDate, false);
                }
                session.setAttribute("ITEMSLIST", arrListItem);
                if (!searchValue.equals("null")) {
                    request.setAttribute("searchValueName", searchValue);
                }
                if (!searchQuantiy.equals("null")) {
                    request.setAttribute("searchValueQuantity", searchQuantiy);
                }
                if (!rentalDate.equals("null")) {
                    request.setAttribute("rentalDate", rentalDate);
                }
                if (!returnDate.equals("null")) {
                    request.setAttribute("returnDate", returnDate);
                }
                if (!categorySearch.equals("null")) {
                    request.setAttribute("categorySearchValue", categorySearch);
                }

                request.setAttribute("ACTION", "searchServlet");

                if (null != edit) {
                    request.setAttribute("edit", true);
                }
                if (null != renting) {
                    request.setAttribute("renting", true);
                }
                if (null != edit) {
                    request.setAttribute("orderHistoryModal", true);
                }

            } catch (SQLException ex) {
                log("SearchByNameServlet_SQL " + ex.getMessage());
            } catch (NamingException ex) {
                log("SearchByNameServlet_Naming " + ex.getMessage());
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(SHOPPING_PAGE);
                rd.forward(request, response);
                out.close();
            }
        }//is Customer 
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

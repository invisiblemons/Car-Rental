/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.view;

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
@WebServlet(name = "LoadProductsServlet", urlPatterns = {"/LoadProductsServlet"})
public class LoadProductsServlet extends HttpServlet {

    private final String HOME_PAGE = "index.jsp";

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

        String searchName = request.getParameter("searchName_Order");
        String searchDate = request.getParameter("searchName_Order");
        String orderHistoryModal =request.getParameter("orderHistoryModal");
        HttpSession session = request.getSession();

        TblOrderDAO orderDAO = new TblOrderDAO();
        TblOrderDetailDAO DAO = new TblOrderDetailDAO();
        TblDiscountOrderDetailDAO discountDao = new TblDiscountOrderDetailDAO();
        TblProductDAO dao = new TblProductDAO();
        TblUserDAO Dao = new TblUserDAO();
        //chk admin
        boolean isAdmin = false;
        TblUserDTO user = (TblUserDTO) session.getAttribute("USER");
        if (user != null) {
            isAdmin = user.isIsAdmin();
            String errorVerify = (String) request.getAttribute("FAILVERIFY");
            try {

                ArrayList<TblProductDTO> arrListItem = (ArrayList<TblProductDTO>) session.getAttribute("ITEMSLIST");
                if (arrListItem == null) {
                    arrListItem = new ArrayList<>();
                } else {
                    arrListItem.clear();
                }
                arrListItem = dao.loadStore(isAdmin);
                //update quantity for cars in out of date order

                ArrayList<TblOrderDetailDTO> listOrder = DAO.loadOrderDetail();
                for (TblProductDTO tblProductDTO : arrListItem) {
                    for (TblOrderDetailDTO tblOrderDetailDTO : listOrder) {
                        if (tblOrderDetailDTO.getCarID().equals(tblProductDTO.getProductID())) {
                            DAO.updateStatus(tblOrderDetailDTO.getOrderID(), tblProductDTO.getProductID(), false);
                            dao.updateQuantity(tblProductDTO.getProductID(), tblProductDTO.getQuantity() + tblOrderDetailDTO.getQuantity());

                        }
                    }
                }
                //loadOrders
                if (null == searchName || null == searchDate) {
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
                }
                if(!isAdmin)
                {
                    request.setAttribute("searchDate", searchDate);
                    request.setAttribute("searchName", searchName);
                    request.setAttribute("orderHistoryModal", orderHistoryModal);
                }
                //loadCars
                arrListItem = dao.loadStore(isAdmin);
                session.setAttribute("ITEMSLIST", arrListItem);
                //verify email

                if (!Dao.getStatus(user.getUsername())) {
                    session.getAttribute("VERIFY");
                    session.setAttribute("VERIFY", "false");
                }
                if (null != errorVerify) {
                    request.setAttribute("FAILVERIFY", "Wrong verify code! Verify Again!!!");
                }

            } catch (SQLException ex) {
                log("LoadItemsServlet_SQL " + ex.getMessage());
            } catch (NamingException ex) {
                log("LoadItemsServlet_Naming " + ex.getMessage());
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(HOME_PAGE);
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
                arrListItem = dao.loadStore(false);
                //update quantity for cars in out of date order

                ArrayList<TblOrderDetailDTO> listOrder = DAO.loadOrderDetail();
                for (TblProductDTO tblProductDTO : arrListItem) {
                    for (TblOrderDetailDTO tblOrderDetailDTO : listOrder) {
                        if (tblOrderDetailDTO.getCarID().equals(tblProductDTO.getProductID())) {
                            DAO.updateStatus(tblOrderDetailDTO.getOrderID(), tblProductDTO.getProductID(), false);
                            dao.updateQuantity(tblProductDTO.getProductID(), tblProductDTO.getQuantity() + tblOrderDetailDTO.getQuantity());

                        }
                    }
                }
                //loadCars
                arrListItem = dao.loadStore(false);
                session.setAttribute("ITEMSLIST", arrListItem);

            } catch (SQLException ex) {
                log("LoadItemsServlet_SQL " + ex.getMessage());
            } catch (NamingException ex) {
                log("LoadItemsServlet_Naming " + ex.getMessage());
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(HOME_PAGE);
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

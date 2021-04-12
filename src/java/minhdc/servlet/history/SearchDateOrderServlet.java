/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.history;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
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
import minhdc.tblUser.TblUserDAO;
import minhdc.tblUser.TblUserDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "SearchDateOrderServlet", urlPatterns = {"/SearchDateOrderServlet"})
public class SearchDateOrderServlet extends HttpServlet {

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

        String searchDate = request.getParameter("searchDate_Order");
        System.out.println(searchDate);
        HttpSession session = request.getSession();

        TblOrderDAO orderDAO = new TblOrderDAO();
        TblOrderDetailDAO DAO = new TblOrderDetailDAO();
        TblDiscountOrderDetailDAO discountDao = new TblDiscountOrderDetailDAO();
        TblProductDAO dao = new TblProductDAO();
        TblUserDAO Dao = new TblUserDAO();
        //chk admin
        boolean isAdmin = false;
        TblUserDTO user = (TblUserDTO) session.getAttribute("USER");

        try {
            //loadRootOrders
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

             //search
            listOrders = (ArrayList<TblOrderDTO>) session.getAttribute("LISTORDERS");
            orderDetailMap = (Map<Integer, ArrayList<TblOrderDetailDTO>>) session.getAttribute("LISTORDERSDETAIL");
            discountDetailMap = (Map<Integer, ArrayList<TblDiscountOrderDetailDTO>>) session.getAttribute("LISTDISCOUNTSDETAIL");

            ArrayList<TblOrderDTO> listOrderss = new ArrayList<>();

            for (TblOrderDTO listOrder : listOrders) {
                for (Integer integer : orderDetailMap.keySet()) {
                    if (listOrder.getId() == integer) {
                        String pattern = "yyyy-MM-dd";
                        DateFormat df = new SimpleDateFormat(pattern);
                        Date date = listOrder.getDateOrder();
                        String datee = df.format(date);
                        if (datee.equals(searchDate)) {

                            listOrderss.add(listOrder);

                        }
                    }
                }
            }
            System.out.println(listOrderss);
            for (TblOrderDTO order : listOrderss) {
                ArrayList<TblOrderDetailDTO> listOrdersDetail = DAO.loadOrderDetail(order.getId());
                orderDetailMap.put(order.getId(), listOrdersDetail);
                ArrayList<TblDiscountOrderDetailDTO> listDiscounts = discountDao.loadOrders(order.getId());
                discountDetailMap.put(order.getId(), listDiscounts);
            }
            session.setAttribute("LISTORDERS", listOrderss);
            session.setAttribute("LISTORDERSDETAIL", orderDetailMap);
            session.setAttribute("LISTDISCOUNTSDETAIL", discountDetailMap);

        } catch (SQLException ex) {
            log("SearchDateOrderServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchDateOrderServlet_Naming " + ex.getMessage());
        } finally {
            String urlRewriting = "loadProducts"
                    + "?searchName_Order=" + searchDate
                    + "&orderHistoryModal=true";
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

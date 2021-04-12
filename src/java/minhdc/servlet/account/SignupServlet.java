/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhdc.tblUser.Mailer;
import minhdc.tblUser.RandomString;
import minhdc.tblUser.TblSignupInsertError;
import minhdc.tblUser.TblUserDAO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {
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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String url = HOME_PAGE;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String fullname = request.getParameter("txtFullName");
            String address = request.getParameter("txtAddress");
            String phone = request.getParameter("txtPhone");
            TblUserDAO dao = new TblUserDAO();
            TblSignupInsertError rie = new TblSignupInsertError();
            boolean error = false;
            if (username != null && password != null && fullname != null) {
                
                if (password.trim().length() < 6 || password.trim().length() > 30) {
                    error = true;
                    rie.setPasswordLengthError("Password must be 6 - 50 character");
                }
                if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                    error = true;
                    rie.setFullnameLengthError("Fullname must be 2 - 50 character");
                }
                
                
            }

            if (error) {
                request.setAttribute("INSERTERROR", rie);
            } else {
                try {
                    java.util.Date dateUtil = new java.util.Date();
                    java.sql.Date createDate = new java.sql.Date(dateUtil.getTime());
                    String random = RandomString.getAlphaNumericString(6);
                    boolean result = dao.createNewAccount(username, password, fullname, false, false, phone, address, createDate, random);
                    if(result)
                    {
                        Mailer.send(username, "Verify Email", "Code: " + random);
                        request.setAttribute("SHOW", "Sign up success! Please login to verify email!");
                    }
                } catch (SQLException ex) {
                    log("SignUpServlet_SQL " + ex.getMessage());
                    if (username != null) {
                        rie.setUsernameIsExist(username + " has existed");
                        request.setAttribute("INSERTERROR", rie);
                    }
                } catch (NamingException ex) {
                    log("SignUpServlet_Naming " + ex.getMessage());
                }
            }

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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

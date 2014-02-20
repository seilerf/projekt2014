/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.controller;

import fh.ostfalia.projekt2014.dao.UserDaoLocal;
import fh.ostfalia.projekt2014.model.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anton
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UseLloginServlet extends HttpServlet {
    @EJB
    private UserDaoLocal userDao;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String userIdStr = request.getParameter("userId");
        int userId=0;
        if(userIdStr!=null && !userIdStr.equals("")){
            userId=Integer.parseInt(userIdStr);    
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
      
     
        User user = new User(userId, username, password, role);
        
        if("Add".equalsIgnoreCase(action)){
            userDao.addUser(user);
        }else if("Edit".equalsIgnoreCase(action)){
            userDao.editUser(user);
        }else if("Delete".equalsIgnoreCase(action)){
            userDao.deleteUser(userId);
        }else if("Search".equalsIgnoreCase(action)){
            user = userDao.getUser(userId);
        }
        request.setAttribute("user", user);
        request.setAttribute("allUsers", userDao.getAllUsers());
        request.getRequestDispatcher("userinfo.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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

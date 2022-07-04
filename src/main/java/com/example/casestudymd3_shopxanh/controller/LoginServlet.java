package com.example.casestudymd3_shopxanh.controller;

import com.example.casestudymd3_shopxanh.model.Account;
import com.example.casestudymd3_shopxanh.model.Product;
import com.example.casestudymd3_shopxanh.service.IAccountService;
import com.example.casestudymd3_shopxanh.service.IProductService;
import com.example.casestudymd3_shopxanh.service.impl.AccountServiceImpl;
import com.example.casestudymd3_shopxanh.service.impl.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "LoginServlet", value = "/logins")
public class LoginServlet extends HttpServlet {
    IAccountService accountService = new AccountServiceImpl();
    static Long currentId = 0L;
    static String name ="";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "view":
                showViewUser(request,response);
                break;
            case "logout":
                logout(request,response,session);
                break;
            default:
                showLoginForm(request, response);
        }
    }

    private void showViewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = null;
        try {
            account = accountService.findById(currentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("accounts", account);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login/view.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login/login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                try {
                    login(request, response, session);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "view":
                try {
                    editUser(request,response,session);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException, SQLException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        accountService.update(new Account(currentId,username,password,name,phone,email,roleId));
        session.setAttribute("name", name);
        response.sendRedirect("/");
    }

    private void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        if (session.getAttribute("name")!=null){
            session.invalidate();
        }
        response.sendRedirect("/");
    }

    private void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accountService.findByUserNamePassword(username, password);
        if(account == null){
            request.setAttribute("mess", "Nhập sai tài khoản hoặc mật khẩu");
            request.getRequestDispatcher("login/login.jsp").forward(request,response);
        } else {
            session.setAttribute("phone", account.getPhone());
            session.setAttribute("name" , account.getName());
            session.setAttribute("roleId", account.getRole());
            session.setAttribute("userId", account.getId());
            session.setAttribute("email", account.getEmail());
            currentId = account.getId();
            name = account.getName();
            response.sendRedirect("/");
        }
    }
}

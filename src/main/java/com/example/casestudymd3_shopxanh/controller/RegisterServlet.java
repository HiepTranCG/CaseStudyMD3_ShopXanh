package com.example.casestudymd3_shopxanh.controller;

import com.example.casestudymd3_shopxanh.model.Account;
import com.example.casestudymd3_shopxanh.service.IAccountService;
import com.example.casestudymd3_shopxanh.service.IRoleService;
import com.example.casestudymd3_shopxanh.service.impl.AccountServiceImpl;
import com.example.casestudymd3_shopxanh.service.impl.RoleServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "RegisterServlet", urlPatterns = "/registers")
public class RegisterServlet extends HttpServlet {
    IAccountService accountService = new AccountServiceImpl();
    IRoleService roleService = new RoleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showRegisterForm(request, response);
        }
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register/register.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    createCustomer(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        String username = request.getParameter("register-username");
        String password = request.getParameter("register-password");
        String name = request.getParameter("register-name");
        String phone = request.getParameter("register-phone");
        String email = request.getParameter("register-email");
        int roleId = 1;
        if (accountService.findByUsernameAndEmail(username, email) == false) {
            accountService.add(new Account(username, password, name, phone, email, roleId));
            response.sendRedirect("/logins");
        } else {
            request.setAttribute("mess", "Account already exits!");
            request.getRequestDispatcher("register/register.jsp").forward(request,response);
        }
    }
}

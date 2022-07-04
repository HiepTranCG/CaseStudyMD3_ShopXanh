package com.example.casestudymd3_shopxanh.controller;


import com.example.casestudymd3_shopxanh.model.Product;
import com.example.casestudymd3_shopxanh.service.IProductService;
import com.example.casestudymd3_shopxanh.service.impl.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/categories")
public class CategoryServlet extends HttpServlet {
    IProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "search":
                searchBook(request, response);
                break;
            default:
                showListBook(request, response);
        }
    }
    private void searchBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("category/category.jsp");
        String name = request.getParameter("name");
        List<Product> products = productService.findByName(name);
        request.setAttribute("products", products);
        requestDispatcher.forward(request, response);
    }

    private void showListBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("category/category.jsp");
        List<Product> products = productService.findAll();
        request.setAttribute("products", products);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

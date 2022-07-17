package com.example.casestudymd3_shopxanh.controller;


import com.example.casestudymd3_shopxanh.model.Category;
import com.example.casestudymd3_shopxanh.model.Product;
import com.example.casestudymd3_shopxanh.service.ICategoryService;
import com.example.casestudymd3_shopxanh.service.IProductService;
import com.example.casestudymd3_shopxanh.service.impl.CategoryServiceImpl;
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
    ICategoryService categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showAll":
                showAllProduct(request, response);
                break;
            case "search":
                searchCategory(request, response);
                break;
            default:
                showListCategory(request, response);
        }
    }
    private void searchCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("category/category.jsp");
        String name = request.getParameter("searchName");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Category category = categoryService.getCategory(categoryId);
        List<Product> products = productService.findByNameAndCategory(name, categoryId);
        request.setAttribute("category", category);
        request.setAttribute("products", products);
        requestDispatcher.forward(request, response);
    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("category/category.jsp");
        List<Product> products = productService.findAll();
        request.setAttribute("products", products);
        requestDispatcher.forward(request, response);
    }

    private void showListCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("category/category.jsp");
        List<Product> products = productService.findByCategoryId(categoryId);
        Category category = categoryService.getCategory(categoryId);
        request.setAttribute("products", products);
        request.setAttribute("category", category);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

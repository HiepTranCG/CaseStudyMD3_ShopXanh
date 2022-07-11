package com.example.casestudymd3_shopxanh.controller;

import com.example.casestudymd3_shopxanh.model.Category;
import com.example.casestudymd3_shopxanh.model.Product;
import com.example.casestudymd3_shopxanh.service.ICategoryService;
import com.example.casestudymd3_shopxanh.service.IProductService;
import com.example.casestudymd3_shopxanh.service.impl.CategoryServiceImpl;
import com.example.casestudymd3_shopxanh.service.impl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductManagementServlet", value = "/product-managements")
public class ProductManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
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
            case "search":
                searchProduct(request, response);
                break;
            case "create":
                showCreateProduct(request, response);
            default:
                showList(request, response);
        }
    }

    private void showCreateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        requestDispatcher.forward(request, response);
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        String name = request.getParameter("name");
        List<Product> products = productService.findByName(name);
        request.setAttribute("products", products);
        requestDispatcher.forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/product-management.jsp");
        List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
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
                    createProduct(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        productService.add(new Product(name, price, description, image, categoryId));
        response.sendRedirect("/product-managements");
    }

}

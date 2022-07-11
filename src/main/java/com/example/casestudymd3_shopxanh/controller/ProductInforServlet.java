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
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProductInforServlet", value = "/product-infors")
public class ProductInforServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IProductService productService = new ProductServiceImpl();
    ICategoryService categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                try {
                    showEditProduct(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    deleteProduct(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    showProductInfor(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    private void showEditProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        List<Category> categories = categoryService.findAll();
        request.setAttribute("product", product);
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
        requestDispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);
        response.sendRedirect("/product-managements");
    }

    private void showProductInfor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("product", product);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/product-infor.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                try {
                    editProduct(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Category category = categoryService.findById(categoryId);
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        productService.update(new Product(id, name, price, description, image, categoryId));
        response.sendRedirect("/product-infors?id=" + id);
    }

}

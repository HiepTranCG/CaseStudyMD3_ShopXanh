package com.example.casestudymd3_shopxanh.controller;


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

@WebServlet(name = "CheckoutServlet", value = "/checkouts")
public class CheckoutServlet extends HttpServlet {
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
            case "cart":
                showCart(request, response);
            case "checkout":
                showCheckout(request, response);
            case "checkout-shipping":
                showShipping(request, response);
            case "checkout-payment":
                showPayment(request, response);
        }
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("checkout/cart.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showCheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("checkout/checkout.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showShipping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("checkout/checkout-payment.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("checkout/checkout-shipping.jsp");
        requestDispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package com.example.casestudymd3_shopxanh.service.impl;

import com.example.casestudymd3_shopxanh.model.Account;
import com.example.casestudymd3_shopxanh.model.OrderDetail;
import com.example.casestudymd3_shopxanh.service.IOrderDetailService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailServiceImpl implements IOrderDetailService {
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopxanh?useSSL=false", "root", "123123");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void add(OrderDetail orderDetail) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into orderdetail(productId, orderId, amount, price) values(?, ?, ?, ?)");) {
            preparedStatement.setLong(1, orderDetail.getProductId());
            preparedStatement.setLong(2, orderDetail.getOrderId());
            preparedStatement.setLong(3, orderDetail.getAmount());
            preparedStatement.setDouble(4, orderDetail.getPrice());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDetail findById(Long id) throws SQLException {
        OrderDetail orderDetail = null ;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from orderdetail where id = ?");){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long idFind = rs.getLong("id");
                Long productId = rs.getLong("productId");
                Long orderId = rs.getLong("orderId");
                Long amount = rs.getLong("amount");
                Double price = rs.getDouble("price");
                orderDetail = new OrderDetail(idFind, productId,orderId, amount, price);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetail;
    }

    @Override
    public List<OrderDetail> findAll() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from orderdetail");) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long idFind = rs.getLong("id");
                Long productId = rs.getLong("productId");
                Long orderId = rs.getLong("orderId");
                Long amount = rs.getLong("amount");
                Double price = rs.getDouble("price");
                orderDetailList.add(new OrderDetail(idFind, productId,orderId, amount, price));
            }
        } catch (SQLException e) {
        }
        return orderDetailList;
    }

    @Override
    public List<OrderDetail> findByName(String findName) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from orderdetail where name like ?");) {
            preparedStatement.setString(1, "%" + findName + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long idFind = rs.getLong("id");
                Long productId = rs.getLong("productId");
                Long orderId = rs.getLong("orderId");
                Long amount = rs.getLong("amount");
                Double price = rs.getDouble("price");
                orderDetailList.add(new OrderDetail(idFind, productId,orderId, amount, price));
            }
        } catch (SQLException e) {
        }
        return orderDetailList;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from orderdetail where id = ?")){
            preparedStatement.setLong(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws SQLException {
        Long findId = orderDetail.getId();
        boolean rowUpdate;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("update orderdetail set orderdetail.productId = ?, orderdetail.orderId = ?, orderdetail.amount = ?, orderdetail.price = ? where id = ?")){
            preparedStatement.setLong(1, orderDetail.getProductId());
            preparedStatement.setLong(2, orderDetail.getOrderId());
            preparedStatement.setLong(3, orderDetail.getAmount());
            preparedStatement.setDouble(4, orderDetail.getPrice());
            preparedStatement.setDouble(5, findId);
            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }
}

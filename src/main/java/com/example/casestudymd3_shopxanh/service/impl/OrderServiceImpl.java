package com.example.casestudymd3_shopxanh.service.impl;

import com.example.casestudymd3_shopxanh.model.Account;
import com.example.casestudymd3_shopxanh.model.Orderr;
import com.example.casestudymd3_shopxanh.service.IOrderService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements IOrderService {
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
    public void add(Orderr orderr) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into orderr(accountId, createDate, status) values(?, ?, ?)");) {
            preparedStatement.setInt(1, orderr.getAccountId());
            preparedStatement.setDate(2, orderr.getCreateDate());
            preparedStatement.setInt(3, orderr.getStatus());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Orderr findById(int id) throws SQLException {
        Orderr orderr = null ;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from orderr where id = ?");){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idFind = rs.getInt("id");
                int accountId = rs.getInt("accountId");
                Date createDate = rs.getDate("createDate");
                int status = rs.getInt("status");
                orderr = new Orderr(idFind, accountId,createDate, status);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderr;
    }

    @Override
    public List<Orderr> findAll() {
        List<Orderr> orderrList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from orderr");) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idFind = rs.getInt("id");
                int accountId = rs.getInt("accountId");
                Date createDate = rs.getDate("createDate");
                int status = rs.getInt("status");
                orderrList.add(new Orderr(idFind, accountId,createDate, status));
            }
        } catch (SQLException e) {
        }
        return orderrList;
    }

    @Override
    public List<Orderr> findByName(String findName) {
        List<Orderr> orderrList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from orderr where name like ?");) {
            preparedStatement.setString(1, "%" + findName + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idFind = rs.getInt("id");
                int accountId = rs.getInt("accountId");
                Date createDate = rs.getDate("createDate");
                int status = rs.getInt("status");
                orderrList.add(new Orderr(idFind, accountId,createDate, status));
            }
        } catch (SQLException e) {
        }
        return orderrList;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from orderr where id = ?")){
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Orderr orderr) throws SQLException {
        int findId = orderr.getId();
        boolean rowUpdate;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("update orderr set orderr.accountId = ?, orderr.createDate = ?, orderr.status = ? where id = ?")){
            preparedStatement.setInt(1, orderr.getAccountId());
            preparedStatement.setDate(2, orderr.getCreateDate());
            preparedStatement.setInt(3, orderr.getStatus());
            preparedStatement.setInt(4, findId);
            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }
}

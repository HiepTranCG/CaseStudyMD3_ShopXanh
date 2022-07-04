package com.example.casestudymd3_shopxanh.service.impl;

import com.example.casestudymd3_shopxanh.model.Account;
import com.example.casestudymd3_shopxanh.model.Category;
import com.example.casestudymd3_shopxanh.service.ICategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
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
    public void add(Category category) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into category(name) values(?)");) {
            preparedStatement.setString(1, category.getName());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category findById(Long id) throws SQLException {
        Category category = null ;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from category where id = ?");){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long idFind = rs.getLong("id");
                String name = rs.getString("name");
                category = new Category(idFind, name);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from category");) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long idFind = rs.getLong("id");
                String name = rs.getString("name");
                categoryList.add(new Category(idFind, name));
            }
        } catch (SQLException e) {
        }
        return categoryList;
    }

    @Override
    public List<Category> findByName(String findName) {
        List<Category> categoryList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from category where name like ?");) {
            preparedStatement.setString(1, "%" + findName + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long idFind = rs.getLong("id");
                String name = rs.getString("name");
                categoryList.add(new Category(idFind, name));
            }
        } catch (SQLException e) {
        }
        return categoryList;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from category where id = ?")){
            preparedStatement.setLong(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Category category) throws SQLException {
        Long findId = category.getId();
        boolean rowUpdate;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("update category set category.name = ? where id = ?")){
            preparedStatement.setString(1, category.getName());
            preparedStatement.setLong(2, findId);
            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }
}

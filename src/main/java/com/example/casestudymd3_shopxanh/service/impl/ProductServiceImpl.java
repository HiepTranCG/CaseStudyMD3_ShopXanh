package com.example.casestudymd3_shopxanh.service.impl;

import com.example.casestudymd3_shopxanh.model.Account;
import com.example.casestudymd3_shopxanh.model.Product;
import com.example.casestudymd3_shopxanh.service.IProductService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService {
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
    public void add(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into product(name, price, description, image, categoryId) values(?, ?, ?, ?, ?)");) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getImage());
            preparedStatement.setLong(5, product.getCategoryId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findById(Long id) throws SQLException {
        Product product = null ;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long idFind = rs.getLong("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                String description = rs.getString("description");
                String image = rs.getString("image");
                Long category = rs.getLong("category");
                product = new Product(idFind, name, price, description, image, category);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product");) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long idFind = rs.getLong("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                String description = rs.getString("description");
                String image = rs.getString("image");
                Long category = rs.getLong("categoryId");
                productList.add(new Product(idFind, name, price, description, image, category));
            }
        } catch (SQLException e) {
        }
        return productList;
    }

    @Override
    public List<Product> findByName(String findName) {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where name like ?");) {
            preparedStatement.setString(1, "%" + findName + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long idFind = rs.getLong("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                String description = rs.getString("description");
                String image = rs.getString("image");
                Long category = rs.getLong("category");
                productList.add(new Product(idFind, name, price, description, image, category));
            }
        } catch (SQLException e) {
        }
        return productList;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id = ?")){
            preparedStatement.setLong(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        Long findId = product.getId();
        boolean rowUpdate;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("update product set product.name = ?, product.price = ?, product.description = ?, product.image = ?, product.categoryId = ? where id = ?")){
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getImage());
            preparedStatement.setLong(5, product.getCategoryId());
            preparedStatement.setLong(6, findId);
            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }
}

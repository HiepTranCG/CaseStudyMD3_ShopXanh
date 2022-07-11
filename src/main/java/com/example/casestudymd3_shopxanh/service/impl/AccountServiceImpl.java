package com.example.casestudymd3_shopxanh.service.impl;

import com.example.casestudymd3_shopxanh.model.Account;
import com.example.casestudymd3_shopxanh.service.IAccountService;

import java.sql.*;
import java.util.*;

public class AccountServiceImpl implements IAccountService {

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
    public void add(Account account) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into account(username, password, name, phone, email, roleId) values(?, ?, ?, ?, ?, ?)");) {
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getName());
            preparedStatement.setString(4, account.getPhone());
            preparedStatement.setString(5, account.getEmail());
            preparedStatement.setInt(6, account.getRole());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findById(int id) throws SQLException {
        Account account = null ;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from account where id = ?");){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idFind = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int role = rs.getInt("roleId");
                account = new Account(idFind, username,password, name, phone, email, role);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from account");) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idFind = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int role = rs.getInt("roleId");
                accountList.add(new Account(idFind, username,password, name, phone, email, role));
            }
        } catch (SQLException e) {
        }
        return accountList;
    }

    @Override
    public List<Account> findByName(String findName) {
        List<Account> accountList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from account where name like ?");) {
            preparedStatement.setString(1, "%" + findName + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idFind = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int role = rs.getInt("roleId");
                accountList.add(new Account(idFind, username,password, name, phone, email, role));
            }
        } catch (SQLException e) {
        }
        return accountList;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from account where id = ?")){
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Account account) throws SQLException {
        int findId = account.getId();
        boolean rowUpdate;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("update account set account.username = ?, account.password = ?, account.name = ?, account.phone = ?, account.email = ?, role = ? where id = ?")){
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getName());
            preparedStatement.setString(4, account.getPhone());
            preparedStatement.setString(5, account.getEmail());
            preparedStatement.setInt(6, account.getRole());
            preparedStatement.setInt(7, findId);
            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }

    public Account findByUserNamePassword(String username,String password) {
        Account account = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from account where (username like ? and password like ?) or (email like ? and  password like ?)");) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4,password);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String usr = rs.getString("username");
                String pw = rs.getString("password");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int roleId = rs.getInt("roleId");
                account = new Account(id, usr, pw,name,phone, email,roleId);
            }
        } catch (SQLException e) {
        }
        return account;
    }

    public boolean findByUsernameAndEmail(String username,String email) {
        boolean find = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from account where username like ? or email like ?");) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                find = true;
            }
        } catch (SQLException e) {
        }
        return find;
    }
}

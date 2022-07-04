package com.example.casestudymd3_shopxanh.service;

import java.sql.SQLException;
import java.util.*;

public interface IGeneralService<T> {
    void add(T t) throws SQLException;
    T findById(Long id) throws SQLException;
    List<T> findAll();
    List<T> findByName(String name);
    boolean delete(Long id) throws SQLException;
    boolean update(T t) throws SQLException;
}

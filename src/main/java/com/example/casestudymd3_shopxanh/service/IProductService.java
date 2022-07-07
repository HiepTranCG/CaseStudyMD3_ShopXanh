package com.example.casestudymd3_shopxanh.service;

import com.example.casestudymd3_shopxanh.model.Product;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    public List<Product> findByCategoryId(int categoryId);
}

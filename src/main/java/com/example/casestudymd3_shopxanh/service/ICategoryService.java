package com.example.casestudymd3_shopxanh.service;

import com.example.casestudymd3_shopxanh.model.Account;
import com.example.casestudymd3_shopxanh.model.Category;
import com.example.casestudymd3_shopxanh.model.Product;

public interface ICategoryService extends IGeneralService<Category>{
    public Category getCategory(int id);
}

package com.example.casestudymd3_shopxanh.service;

import com.example.casestudymd3_shopxanh.model.Account;

public interface IAccountService extends IGeneralService<Account> {
    public Account findByUserNamePassword(String username,String password);
}

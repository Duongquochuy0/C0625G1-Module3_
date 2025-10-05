package com.example.demo.service;

import com.example.demo.entity.Account;

import java.util.List;

public interface IAccountService {
    // Thêm tài khoản mới
    boolean createAccount(Account account);

    // Tìm theo ID
    Account getAccountById(int id);

    // Tìm theo username
    Account getAccountByUsername(String username);

    // Lấy tất cả tài khoản
    List<Account> getAllAccounts();
}

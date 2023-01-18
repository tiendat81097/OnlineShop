package com.onlineshop.service.impl;

import com.onlineshop.dao.AccountDAO;
import com.onlineshop.entity.Account;
import com.onlineshop.entity.Role;
import com.onlineshop.model.AccountInfo;
import com.onlineshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Account getAccountByUserName(String userName) {
        return accountDAO.getAccountByUserName(userName);
    }

    @Override
    public void registerNewAccount(AccountInfo accountInfo) {
        accountDAO.registerNewAccount(accountInfo);
    }

    @Override
    public String getAccountRoleName(int id) {
        return accountDAO.getAccountRoleName(id);
    }

    @Override
    public List<AccountInfo> getAllAccount() {
        return accountDAO.getAllAccount();
    }

    @Override
    public void saveAccount(Account account, Role role) {
        accountDAO.saveRoleName(account, role);
    }

    @Override
    public List<Role> getAllRoleName() {
        return accountDAO.getAllRoleName();
    }

    @Override
    public Role getAccountRole(Account account) {
        return accountDAO.getAccountRole(account);
    }

    @Override
    public Role getRoleById(int roleId) {
        return accountDAO.getRoleById(roleId);
    }

    @Override
    public AccountInfo getAccountInfoByUserName(String userName) {
        return accountDAO.getAccountInfoByUserName(userName);
    }
}

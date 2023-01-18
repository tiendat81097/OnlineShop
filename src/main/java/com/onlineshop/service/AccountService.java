package com.onlineshop.service;

import com.onlineshop.entity.Account;
import com.onlineshop.entity.Role;
import com.onlineshop.model.AccountInfo;

import java.util.List;

public interface AccountService {
    public Account getAccountByUserName(String userName);

    public void registerNewAccount(AccountInfo accountInfo);

    public String getAccountRoleName(int id);

    public List<AccountInfo> getAllAccount();

    public void saveAccount(Account account, Role role);

    public List<Role> getAllRoleName();

    public Role getAccountRole(Account account);

    public Role getRoleById(int roleId);

    public AccountInfo getAccountInfoByUserName(String userName);
}

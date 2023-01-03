package com.onlineshop.dao;

import java.util.List;

import com.onlineshop.entity.Account;
import com.onlineshop.entity.Role;
import com.onlineshop.model.AccountInfo;

public interface AccountDAO {
    public Account getAccountByUserName(String userName);

    public AccountInfo getAccountInfoByUserName(String userName);

    public String getAccountRoleName(int id);

    public void registerNewAccount(AccountInfo accountInfo);

    public List<AccountInfo> getAllAccount();

    public void saveRoleName(Account account, Role role);

    public Role getRoleById(int roleId);
    
    public List<Role> getAllRoleName();
    
    public Role getAccountRole(Account account);
}

package com.onlineshop.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.onlineshop.dao.AccountDAO;
import com.onlineshop.dao.CustomerDAO;
import com.onlineshop.entity.Account;
import com.onlineshop.entity.Customer;
import com.onlineshop.entity.Role;
import com.onlineshop.model.AccountInfo;
import com.onlineshop.model.CustomerInfo;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerDAO customerDao;
	
	@Override
	public Account getAccountByUserName(String userName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT ACC FROM ACCOUNT ACC WHERE ACC.USERNAME = :USERNAME";
		Query<Account> query = session.createQuery(hql);
		query.setParameter("USERNAME", userName);
		Account account = (Account) query.uniqueResult();
		return account;
	}

	@Override
	public AccountInfo getAccountInfoByUserName(String userName) {
		// TODO Auto-generated method stub
		Account account = getAccountByUserName(userName);
		if(account != null)
			return new AccountInfo(account.getId(), account.getUserName(), account.getPassword(), account.getRole(), account.getCustomer());
		return null;
	}

	@Override
	public String getAccountRoleName(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT R FROM ROLE R WHERE R.ID = :ID";
		Query<Role> query = session.createQuery(hql);
		query.setParameter("ID", id);
		Role role = (Role) query.uniqueResult();
		return role.getRoleName();
	}

	@Override
	public void registerNewAccount(AccountInfo accountInfo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String userName = accountInfo.getUserName();
		Account account = null;
		Customer customer = null;
		Role role = null;
		boolean isNew = false;
		
		if(userName != null) {
			account = new Account();
			account.setUserName(accountInfo.getUserName());
			String encryptedPassword = passwordEncoder.encode(accountInfo.getPassword());
			account.setPassword(encryptedPassword);
			
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setName(accountInfo.getName());
			customerInfo.setAddress(accountInfo.getAddress());
			customerInfo.setEmail(accountInfo.getEmail());
			customerInfo.setPhone(accountInfo.getPhone());
			customer = customerDao.registerNewCustomer(customerInfo);
			role = customerDao.getCustomerRole("USER");
			
			account.setRole(role);
			account.setCustomer(customer);
			account.setActive(true);
			account.setValid(true);
			customer.setAccount(account);
			isNew = true;
		}
		if(isNew) {
			session.persist(customer);
			session.persist(account);
		}
		session.flush();
	}

	@Override
	public List<AccountInfo> getAllAccount() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + AccountInfo.class.getName() + "(ACC.USERNAME)" 
				+ "FROM ACCOUNT ACC WHERE ACC.ROLE.ID NOT LIKE 1";
		Query<AccountInfo> query = session.createQuery(hql);
		List<AccountInfo> accountInfoList = (List<AccountInfo>) query.list();
		return accountInfoList;
	}

	@Override
	public void saveRoleName(Account account, Role role) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		account.setRole(role);
		session.update(account);
		
		session.flush();
	}

	@Override
	public Role getRoleById(int roleId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT R FROM ROLE R WHERE R.ID = :ID";
		Query<Role> query = session.createQuery(hql);
		query.setParameter("ID", roleId);
		Role role = (Role) query.uniqueResult();
		return role;
	}

	@Override
	public List<Role> getAllRoleName() {
		// TODO Auto-generated method stub
		Session session  = sessionFactory.getCurrentSession();
		String hql = "SELECT R FROM ROLE R WHERE R.ID = :ID1 OR R.ID = :ID2";
		Query<Role> query = session.createQuery(hql);
		query.setParameter("ID1", 2).setParameter("ID2", 3);
		List<Role> roleList = (List<Role>) query.list();
		return roleList;
	}

	@Override
	public Role getAccountRole(Account account) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT R FROM ROLE R WHERE R.ID = :ID";
		Query<Role> query = session.createQuery(hql);
		query.setParameter("ID", account.getRole().getId());
		Role role = (Role) query.uniqueResult();
		return role;
	}
	
}

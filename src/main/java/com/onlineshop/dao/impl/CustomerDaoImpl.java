package com.onlineshop.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineshop.dao.CustomerDAO;
import com.onlineshop.entity.Customer;
import com.onlineshop.entity.Role;
import com.onlineshop.model.CustomerInfo;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveCustomerInfo(CustomerInfo customerInfo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		int id = customerInfo.getId();
		Customer customer = null;
		boolean isValid = false;
		
		if(id != 0) {
			customer = getCustomerById(id);
			if(customer != null) 
				isValid = true;
		}
		
		customer.setFullName(customerInfo.getName());
		customer.setEmail(customerInfo.getEmail());
		customer.setAddress(customerInfo.getAddress());
		customer.setPhone(customerInfo.getPhone());
		if(isValid)
			session.persist(customer);
		session.flush();
	}

	@Override
	public CustomerInfo getCustomerInfoById(int id) {
		// TODO Auto-generated method stub
		Customer customer = getCustomerById(id);
		if(customer != null)
			return new CustomerInfo(customer.getId(), customer.getFullName(), customer.getAddress(), customer.getEmail(), customer.getPhone());
		return null;
	}

	@Override
	public Role getCustomerRole(String roleName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT R FROM CUSTOMER R WHERE R.ROLENAME = :ROLENAME";
		Query<Role> query = session.createQuery(hql);
		query.setParameter("ROLENAME", roleName);
		Role role = (Role) query.uniqueResult();
		return role;
	}

	@Override
	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT CUS FROM CUSTOMER CUS WHERE CUS.ID = :ID";
		Query<Customer> query = session.createQuery(hql);
		query.setParameter("ID", id);
		Customer customer = (Customer) query.uniqueResult();
		return customer;
	}

	@Override
	public Customer registerNewCustomer(CustomerInfo customerInfo) {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		customer.setFullName(customerInfo.getName());
		customer.setAddress(customerInfo.getAddress());
		customer.setEmail(customerInfo.getEmail());
		customer.setPhone(customerInfo.getPhone());
		return customer;
	}

}

package com.onlineshop.dao;

import com.onlineshop.entity.Customer;
import com.onlineshop.entity.Role;
import com.onlineshop.model.CustomerInfo;

public interface CustomerDAO {
	
	public void saveCustomerInfo(CustomerInfo customerInfo);
	
	public CustomerInfo getCustomerInfoById(int id);
	
	public Role getCustomerRole(String roleName);
	
	public Customer getCustomerById(int id);
	
	public Customer registerNewCustomer(CustomerInfo customerInfo);
}

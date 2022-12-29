package com.onlineshop.model;

import com.onlineshop.entity.Customer;
import com.onlineshop.entity.Role;

public class AccountInfo {
	private int id;
	
	private String userName;
	
	private String password;
	
	private String name;
	
	private String address;
	
	private String email;
	
	private String phone;
	
	private boolean active;
	
	private Role role;
	
	private Customer customer;
	
	public AccountInfo() {
		
	}



	public AccountInfo(int id, String userName, String password, Role role, Customer customer) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.customer = customer;
	}



	public AccountInfo(String userName) {
		this.userName = userName;
	}
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String roleName(){
		return this.role.getRoleName();
	}
}

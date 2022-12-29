package com.onlineshop.model;

public class CustomerInfo {
	private int id;

	private String name;
	
	private String address;
	
	private String email;
	
	private String phone;
	
	private boolean Valid;

	
	
	public boolean isValid() {
		return Valid;
	}
	
	public CustomerInfo() {
		
	}
	
	public CustomerInfo(String name, String address, String email, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}
	
	public CustomerInfo(int id, String name, String address, String email, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}
	
	public CustomerInfo(String name, String address, String email, String phone, boolean Valid) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.Valid = Valid;
	}

	public void setValid(boolean isValid) {
		this.Valid = isValid;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}

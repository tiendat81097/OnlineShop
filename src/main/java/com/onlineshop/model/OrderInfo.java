package com.onlineshop.model;

import java.util.Date;
import java.util.List;

import com.entity.Customer;

public class OrderInfo {
	private String id;
	
	private Date orderDate;
	
	private int orderNum;
	
	private double amount;
	
	private CustomerInfo customerInfo;
	
	private List<OrderDetailInfo> orderDetailInfos;
	
	private String accountName;

	public OrderInfo(String id, Date orderDate, int orderNum, double amount, String accountName) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.amount = amount;
		this.accountName = accountName;
	}

	public OrderInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderInfo(String id, Date orderDate, int orderNum, double amount, CustomerInfo customerInfo) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.amount = amount;
		this.customerInfo = customerInfo;
	}
	
	public OrderInfo(String id, Date orderDate, int orderNum, double amount) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<OrderDetailInfo> getOrderDetailInfos() {
		return orderDetailInfos;
	}

	public void setOrderDetailInfos(List<OrderDetailInfo> orderDetailInfos) {
		this.orderDetailInfos = orderDetailInfos;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


}

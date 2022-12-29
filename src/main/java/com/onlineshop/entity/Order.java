package com.onlineshop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "Orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", length = 50)
	private String id;

	@Column(name = "Order_Date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@Column(name = "Order_Num", nullable = false)
	private int orderNum;

	@Column(name = "Amount", nullable = false)
	private double amount;

	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade={CascadeType.ALL})
	private List<OrderDetail> orderDetails;
	
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

	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
	public Order(String id, Date orderDate, int orderNum, double amount, Customer customer,
			List<OrderDetail> orderDetails) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.amount = amount;
		this.customer = customer;
		this.orderDetails = orderDetails;
	}

	public Order() {
		super();
	}
	
	
	
}
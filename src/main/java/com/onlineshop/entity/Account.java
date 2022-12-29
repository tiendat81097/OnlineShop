package com.onlineshop.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name = "User_Name", length = 20, nullable = false)
	private String userName;

	@Column(name = "Password", length = 255, nullable = false)
	private String password;

	@Column(name = "Active", length = 1, nullable = false)
	private boolean active;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false, foreignKey = @ForeignKey(name = "ROLE_ACC_FK"))
	private Role role;
	
	@OneToOne
	@JoinColumn(name="Customer_id")
	private Customer customer;

	
	private boolean valid;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Account(int id, String userName, String password, boolean active, Role role, Customer customer,
			boolean valid) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.active = active;
		this.role = role;
		this.customer = customer;
		this.valid = valid;
	}

	public Account() {
		super();
	}

	

}
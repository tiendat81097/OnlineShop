package com.onlineshop.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="Roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="Role_Name")
	private String roleName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade={CascadeType.ALL})
	private List<Account> accounts;

	public Role(int id, String roleName){
		this.id = id;
		this.roleName = roleName;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Role(int id, String roleName, List<Account> accounts) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.accounts = accounts;
	}

	public Role() {
		super();
	}
	

	
}

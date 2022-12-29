package com.onlineshop.model;

import java.util.ArrayList;
import java.util.List;

public class AccountDetailInfo {	
	private List<AccountInfo> accountInfos = new ArrayList<AccountInfo>();

	public AccountDetailInfo() {
		
	}
	
	public List<AccountInfo> getAccountInfo() {
		return accountInfos;
	}

	public void setAccountInfo(List<AccountInfo> accountInfo) {
		this.accountInfos = accountInfo;
	}
	
	public void addAccountInfo(AccountInfo accountInfo) {
		this.accountInfos.add(accountInfo);
	}
}

package com.onlineshop.authentication;

import java.util.ArrayList;
import java.util.List;

import com.onlineshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlineshop.entity.Account;



@Service
public class MyDBAuthenticationService implements UserDetailsService {
	
	@Autowired
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountService.getAccountByUserName(username);
		System.out.println("Account = " + account);

		if (account == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		String roleName = accountService.getAccountRoleName(account.getRole().getId());
		List<GrantedAuthority> grantList = new ArrayList<>();

		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName);
		grantList.add(authority);
		
		UserDetails userDetails = (UserDetails) new User(account.getUserName(), account.getPassword(), grantList);
		return userDetails;
	}
}

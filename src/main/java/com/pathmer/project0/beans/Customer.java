package com.pathmer.project0.beans;

import java.util.List;


public class Customer extends User {
	private List<Account> accounts;

	public Customer(Integer id, String utype, String username, String passwords, Integer social, String firstname,
			String lastname, String status, List<Account> accounts) {
		super(id, utype, username, passwords, social, firstname, lastname, status);
		this.accounts = accounts;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	

}

package com.pathmer.repositorytests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.pathmer.project0.beans.Account;
import com.pathmer.project0.repositories.AccountRepository;

public class AccountRepositoryTests {
	private AccountRepository ar = new AccountRepository();
	
	@Test
	public void getAllAccountTest() {
		
		List<Account> accounts = new ArrayList<Account>();
		Account a1 = new Account(1, 1, "BANK", 950.0, "active", 1);
		Account a2 = new Account(2, 2, "checking", 650.0, "active", 2);
		Account a3 = new Account(3, 3, "savings", 0.0, "pending", 2);
		Account a4 = new Account(4, 4, "checking", 300.0, "active", 3);
		Account a5 = new Account(5, 5, "savings", 0.0, "pending", 3);
	
		accounts.add(a1);
		accounts.add(a2);
		accounts.add(a3);
		accounts.add(a4);
		accounts.add(a5);

	
		Assert.assertEquals(accounts, ar.getAll());
		
		System.out.println(ar.getAll());
		
	}
	
	@Test
	public void getAccountByIdTest() {
		
		Account a2 = new Account(2, 2, "checking", 650.0, "active", 2);
		
		System.out.println(a2);
		System.out.println(ar.getById(2));
		
		Assert.assertEquals(a2, ar.getById(2));
	}
	
	@Test
	public void addAccountTest() {
		
		Account a6 = new Account(6, 6, "savings", 0.0, "pending", 3);
		
		System.out.println(a6);
		ar.add(a6);
		//Assert.assertEquals(a6, ar.add(a6));
		
		System.out.println(a6);
		System.out.println(ar.getById(6));
		
	}
	
	@Test
	public void updateAccountTest() {
		
		Account a6 = new Account(6, 6, "savings", 0.0, "active", 3);
		
		Assert.assertEquals(a6, ar.update(a6));
		
		System.out.println(a6);
		System.out.println(ar.getById(6));
		
	}
	
	@Test
	public void deleteAccountTest() {
		
		Account a6 = new Account(6, 6, "savings", 0.0, "active", 3);
		
		Assert.assertEquals(true, ar.delete(a6.getId()));
	
	}
	
	@Test
	public void getByStatusTest() {
		
		List<Account> accounts = new ArrayList<Account>();
		Account a3 = new Account(3, 3, "savings", 0.0, "pending", 2);
		Account a5 = new Account(5, 5, "savings", 0.0, "pending", 3);
	
		accounts.add(a3);
		accounts.add(a5);

	
		Assert.assertEquals(accounts, ar.getByAstatus("pending"));
		
		System.out.println(ar.getByAstatus("pending"));
		
	}

	@Test
	public void getUserAccountsTest() {
		
		List<Account> accounts = new ArrayList<Account>();
		Account a4 = new Account(4, 4, "checking", 300.0, "active", 3);
		Account a5 = new Account(5, 5, "savings", 0.0, "pending", 3);
	
		accounts.add(a4);
		accounts.add(a5);

	
		Assert.assertEquals(accounts, ar.getByUser(3));
		
		System.out.println(ar.getByUser(3));
		
	}
	
	@Test
	public void getAccountByAccNumberTest() {
		
		Account a2 = new Account(2, 2, "checking", 650.0, "active", 2);
		
		System.out.println(a2);
		System.out.println(ar.getByAccNumber(2));
		
		Assert.assertEquals(a2, ar.getById(2));
	}
	
}

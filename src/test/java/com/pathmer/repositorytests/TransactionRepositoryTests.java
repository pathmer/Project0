package com.pathmer.repositorytests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.pathmer.project0.beans.Transaction;
import com.pathmer.project0.repositories.TransactionRepository;

public class TransactionRepositoryTests {
	private TransactionRepository tr = new TransactionRepository();
	
	
	@Test
	public void getAllTransactionTest() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction t1 = new Transaction(1, "deposit", 1000.0, 1, 11, 111111111, "completed", 0, 2);
		Transaction t2 = new Transaction(2, "withdrawl", 50.0, 11, 1, 111111111, "completed", 0, 2);
		Transaction t3 = new Transaction(3, "transferout", 300.0, 11, 21, 111111111, "completed", 1, 2);
		Transaction t4 = new Transaction(4, "transferin", 300.0, 11, 21, 222222222, "completed", 1, 4);
		Transaction t5 = new Transaction(5, "transferout", 200.0, 11, 21, 111111111, "pending", 2, 2);
		Transaction t6 = new Transaction(6, "transferin", 200.0, 11, 21, 222222222, "pending", 2, 4);
		
		transactions.add(t1);
		transactions.add(t2);
		transactions.add(t3);
		transactions.add(t4);
		transactions.add(t5);
		transactions.add(t6);

		System.out.println(transactions);
		System.out.println(tr.getAll());
		
		Assert.assertEquals(transactions, tr.getAll());
		List<Transaction> test = new ArrayList<Transaction>();
		test = tr.getAll();
		for (Transaction temp : test) {
            System.out.println(temp);
		}
	}
	
	@Test
	public void getTransactionByIdTest() {
		
		Transaction t2 = new Transaction(2, "withdrawl", 50.0, 11, 1, 111111111, "completed", 0, 2);
		
		System.out.println(t2);
		System.out.println(tr.getById(2));
		
		Assert.assertEquals(t2, tr.getById(2));
	}
	
	@Test
	public void addTransactionTest() {
		
		Transaction t7 = new Transaction(7, "withdrawl", 150.0, 11, 1, 111111111, "pending", 0, 2);
		
		Assert.assertEquals(t7, tr.add(t7));
		
		System.out.println(t7);
		System.out.println(tr.getById(7));
		
	}
	
	@Test
	public void updateTransactionTest() {
		
		Transaction t7 = new Transaction(7, "withdrawl", 150.0, 11, 1, 111111111, "completed", 0, 2);
		
		Assert.assertEquals(t7, tr.update(t7));
		
		System.out.println(t7);
		System.out.println(tr.getById(7));
		
	}
	
	@Test
	public void deleteTransactionTest() {
		
		Transaction t7 = new Transaction(7, "withdrawl", 150.0, 11, 1, 111111111, "completed", 0, 2);
		
		Assert.assertEquals(true, tr.delete(t7.getId()));
	
	}
	
	@Test
	public void getByTypeTest() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction t4 = new Transaction(4, "transferin", 300.0, 11, 21, 222222222, "completed", 1, 4);
		Transaction t6 = new Transaction(6, "transferin", 200.0, 11, 21, 222222222, "pending", 2, 4);
	
		transactions.add(t4);
		transactions.add(t6);
	
		Assert.assertEquals(transactions, tr.getByTtype("transferin", 4));
		
		System.out.println(tr.getByTtype("transferin", 4));
		
	}
	
	@Test
	public void getBankingAccountsTest() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction t4 = new Transaction(4, "transferin", 300.0, 11, 21, 222222222, "completed", 1, 4);
		Transaction t6 = new Transaction(6, "transferin", 200.0, 11, 21, 222222222, "pending", 2, 4);
	
		transactions.add(t4);
		transactions.add(t6);

	
		Assert.assertEquals(transactions, tr.getByAccount(4));
		
		System.out.println(tr.getByAccount(4));
		
	}
	
	@Test
	public void getByTransferIdTest() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction t5 = new Transaction(5, "transferout", 200.0, 11, 21, 111111111, "pending", 2, 2);
		Transaction t6 = new Transaction(6, "transferin", 200.0, 11, 21, 222222222, "pending", 2, 4);
	
		transactions.add(t5);
		transactions.add(t6);

	
		Assert.assertEquals(transactions, tr.getByTransferId(2));
		
		System.out.println(tr.getByTransferId(2));
		
	}
}
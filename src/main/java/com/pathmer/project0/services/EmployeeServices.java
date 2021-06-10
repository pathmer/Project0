package com.pathmer.project0.services;

import java.util.ArrayList;
import java.util.List;

import com.pathmer.project0.beans.Account;
import com.pathmer.project0.beans.Transaction;
import com.pathmer.project0.beans.User;
import com.pathmer.project0.driver.Main;
import com.pathmer.project0.logging.AppLogger;
import com.pathmer.project0.repositories.AccountRepository;
import com.pathmer.project0.repositories.TransactionRepository;
import com.pathmer.project0.repositories.UserRepository;

public class EmployeeServices {
	private TransactionRepository tr = new TransactionRepository();
	private AccountRepository ar = new AccountRepository();
	private UserRepository ur = new UserRepository();
	public static User selUser = new User();
	public static Account selAccount = new Account();
	boolean logout = false;
	boolean mainmenu = true;
	
	
	public void mainM() {
		AppLogger.logger.info("Employee login successful.");
		do {
			String input;
			boolean good = false;
			do {
			System.out.println(Main.nm);
			System.out.println(Main.nm);
			System.out.println("Selected user account = " + selUser.empView());
			System.out.println("\n");
			System.out.println("Selected bank account = " + selAccount.empView());
			System.out.println("\n");
			System.out.println("Options:");
			System.out.println("1. User accounts menu");
			System.out.println("2. Bank accounts menu");
			System.out.println("3. Transactions menu");
			System.out.println("4. Log out");
			input = Main.collectInput();
			good = Main.checkIntInput(input, 0, 4);
			}
			while (good == false);
			
			switch(input) {
			  	case "1":
			  		usersMenu();
			  		break;
			  	case "2":
			  		accountsMenu();
			  		break;
			  	case "3":
			  		transactionsMenu();
			  		break;  
			  	case "4":
			  		logout = true;
			  		break;
			}
		}
		while (logout == false);
	}
	
	public void usersMenu() {
		String input;
		boolean good = false;
		mainmenu = false;
		do {
			do {
			System.out.println(Main.nm);
			System.out.println("Selected user account = " + selUser.empView());
			System.out.println("\n");
			System.out.println("Selected bank account = " + selAccount.empView());
			System.out.println("\n");
			System.out.println("User Accounts Menu");
			System.out.println("\n");
			System.out.println("Options:");
			System.out.println("1. Select different user account");
			System.out.println("2. View all user accounts");
			System.out.println("3. View pending user accounts");
			System.out.println("4. Approve selected user account");
			System.out.println("5. Main menu");
			input = Main.collectInput();
			good = Main.checkIntInput(input, 0, 5);
			}
			while (good == false);
			
			switch(input) {
			  	case "1":
			  		// Select different user account
			  		String suainput;
					boolean suagood = false;
					
			  		System.out.println(Main.nm);
			  		System.out.println("Select different user account by id number:");
			  		suainput = Main.collectInput();
					suagood = Main.checkIntInput(suainput, 0, 999999999);
			  		if (suagood) {
			  			int temp = Integer.parseInt(suainput);
			  			User suaaccount = new User();
			  			suaaccount = ur.getById(temp);
			  			if (suaaccount == null) {
			  				System.out.println("Invalid user account id entered");
			  			}
			  			else {
			  				selUser = suaaccount;
			  			}
			  		}
			  		else {
			  			System.out.println("Invalid user account id entered");
			  		}
			  		break;
			  		
			  	case "2":
			  		// View all user accounts
			  		System.out.println(Main.nm);
			  		System.out.println("\n");
					System.out.println("All user accounts");
					System.out.println("\n");
			  		List<User> allusers = new ArrayList<User>();
			  		allusers = ur.getAll();
					for (User temp : allusers) {
			            System.out.println(temp);
					}
			  		break;
			  		
			  	case "3":
			  		// View pending user accounts
			  		System.out.println(Main.nm);
			  		System.out.println("\n");
					System.out.println("All user accounts pending approval");
					System.out.println("\n");
			  		List<User> allpendingusers = new ArrayList<User>();
			  		allpendingusers = ur.getByUstatus("pending");
					for (User temp : allpendingusers) {
			            System.out.println(temp);
					}
			  		break;

			  	case "4":
			  		// Approve selected user account
			  		if (selUser.getId() == null) {
			  			System.out.println("First select a valid user account.");
			  		}
			  		else {
			  			selUser.setUstatus("active");
			  			ur.update(selUser);
			  			selUser = ur.getById(selUser.getId());
			  			System.out.println("User account activated.");
			  			AppLogger.logger.info("New user account approved.");
			  		}
			  		break;
			  		
			  	case "5":
			  		mainmenu = true;
			  		break;
			}
		}
		while (mainmenu == false);
	}
	
	public void accountsMenu() {
		String input;
		boolean good = false;
		mainmenu = false;
		do {
			do {
			System.out.println(Main.nm);
			System.out.println("Selected user account = " + selUser.empView());
			System.out.println("\n");
			System.out.println("Selected bank account = " + selAccount.empView());
			System.out.println("\n");
			System.out.println("Bank Accounts Menu");
			System.out.println("\n");
			System.out.println("Options:");
			System.out.println("1. Select different bank account");
			System.out.println("2. View all bank accounts");
			System.out.println("3. View selected user's bank accounts");
			System.out.println("4. View pending bank accounts");
			System.out.println("5. Approve selected bank account");
			System.out.println("6. Main menu");
			input = Main.collectInput();
			good = Main.checkIntInput(input, 0, 6);
			}
			while (good == false);
			
			switch(input) {
			  	case "1":
			  		// Select different bank account
			  		String sbainput;
					boolean sbagood = false;
					
			  		System.out.println(Main.nm);
			  		System.out.println("Select new bank account by id number:");
			  		sbainput = Main.collectInput();
					sbagood = Main.checkIntInput(sbainput, 0, 999999999);
			  		if (sbagood) {
			  			int temp = Integer.parseInt(sbainput);
			  			Account sbaaccount = new Account();
			  			sbaaccount = ar.getById(temp);
			  			if (sbaaccount == null) {
			  				System.out.println("Invalid bank account id entered");
			  			}
			  			else {
			  				selAccount = sbaaccount;
			  			}
			  		}
			  		else {
			  			System.out.println("Invalid bank account id entered");
			  		}
			  		break;
			  		
			  	case "2":
			  		// View all bank accounts
			  		System.out.println(Main.nm);
			  		System.out.println("\n");
					System.out.println("All bank accounts");
					System.out.println("\n");
			  		List<Account> allaccounts = new ArrayList<Account>();
			  		allaccounts = ar.getAll();
					for (Account temp : allaccounts) {
			            System.out.println(temp);
					}
			  		break;
			  		
			  	case "3":
			  		// View selected users bank accounts
			  		System.out.println(Main.nm);
			  		if (selUser.getId() == null) {
			  			System.out.println("First select a valid user account.");
			  		}
			  		else {
			  			System.out.println("\n");
						System.out.println("All bank accounts for user: " + selUser.getUsernames());
						System.out.println("\n");
			  			List<Account> sbatransactions = new ArrayList<Account>();
			  			sbatransactions = ar.getByUser(selUser.getId());
						for (Account temp : sbatransactions) {
				            System.out.println(temp);
						}
			  		}
			  		break;
			  		
			  	case "4":
			  		// View pending bank accounts
			  		System.out.println(Main.nm);
			  		System.out.println("\n");
					System.out.println("All bank accounts pending approval");
					System.out.println("\n");
			  		List<Account> allpendingaccounts = new ArrayList<Account>();
			  		allpendingaccounts = ar.getByAstatus("pending");
					for (Account temp : allpendingaccounts) {
			            System.out.println(temp);
					}
			  		break; 
			  		
			  	case "5":
			  		// Approve selected bank account
			  		if (selAccount.getId() == null) {
			  			System.out.println("First select a valid bank account.");
			  		}
			  		else {
			  			selAccount.setAstatus("active");
			  			ar.update(selAccount);
			  			selAccount = ar.getById(selAccount.getId());
			  			System.out.println("Bank account activated.");
			  			AppLogger.logger.info("New bank account approved.");
			  		}
			  		break;
			  		
			  	case "6":
			  		mainmenu = true;
			  		break;
			}
		}
		while (mainmenu == false);
	}

	public void transactionsMenu() {
		String input;
		boolean good = false;
		mainmenu = false;
		do {
			do {
			System.out.println(Main.nm);
			System.out.println("Selected user account = " + selUser.empView());
			System.out.println("\n");
			System.out.println("Selected bank account = " + selAccount.empView());
			System.out.println("\n");
			System.out.println("Transactions Menu");
			System.out.println("\n");
			System.out.println("Options:");
			System.out.println("1. View all transactions");
			System.out.println("2. View selected bank account's transactions");
			System.out.println("3. Main menu");
			input = Main.collectInput();
			good = Main.checkIntInput(input, 0, 3);
			}
			while (good == false);
			
			switch(input) {
			  	case "1":
			  		// View all transactions
			  		System.out.println(Main.nm);
			  		System.out.println("\n");
					System.out.println("All bank transactions");
					System.out.println("\n");
			  		List<Transaction> alltransactions = new ArrayList<Transaction>();
			  		alltransactions = tr.getAll();
					for (Transaction temp : alltransactions) {
			            System.out.println(temp);
					}
			  		break;
			  		
			  	case "2":
			  		// View selected bank accounts transactions
			  		System.out.println(Main.nm);
			  		if (selAccount.getId() == null) {
			  			System.out.println("First select a valid bank account.");
			  		}
			  		else {
						System.out.println("All transactions For bank account: " + selAccount.getAccnumber());
						System.out.println("\n");
			  			List<Transaction> sbatransactions = new ArrayList<Transaction>();
			  			sbatransactions = tr.getByAccount(selAccount.getId());
						for (Transaction temp : sbatransactions) {
				            System.out.println(temp);
						}
			  		}
			  		break;
			  		
			  	case "3":
			  		mainmenu = true;
			  		break;
			}
		}
		while (mainmenu == false);
}

}

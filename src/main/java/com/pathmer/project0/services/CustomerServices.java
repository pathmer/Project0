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

public class CustomerServices {
		private static TransactionRepository tr = new TransactionRepository();
		private static AccountRepository ar = new AccountRepository();
		public static User activeuser = new User();
		public static Account selaccount = new Account();
		public static Account bank = ar.getByAccNumber(1);
		boolean logout = false;
		boolean mainmenu = true;
		
		
		public void mainM() {
			AppLogger.logger.info("Customer login successful.");
			activeuser = LoginServices.activeuser;
			do {
				String input;
				boolean good = false;
				do {
				System.out.println(Main.nm);
				System.out.println("Selected bank account = " + selaccount.userView());
				System.out.println("\n");
				System.out.println("Options:");
				System.out.println("1. Select different bank account");
				System.out.println("2. View all bank accounts");
				System.out.println("3. View bank account's completed transactions");
				System.out.println("4. Withdrawal");
				System.out.println("5. Deposit");
				System.out.println("6. Electronic funds transfers (EFT)");
				System.out.println("7. Request new checking account");
				System.out.println("8. Request new savings account");
				System.out.println("9. Log out");
				input = Main.collectInput();
				good = Main.checkIntInput(input, 0, 10);
				}
				while (good == false);
				
				switch(input) {
				  	case "1":
				  		// Select different bank account
				  		String sbainput;
						boolean sbagood = false;
						
				  		System.out.println(Main.nm);
				  		System.out.println("Select new bank account by account number:");
				  		sbainput = Main.collectInput();
						sbagood = Main.checkIntInput(sbainput, 0, 999999999);
				  		if (sbagood) {
				  			int temp = Integer.parseInt(sbainput);
				  			Account sbaaccount = new Account();
				  			sbaaccount = ar.getByAccNumber(temp);
				  			if ((sbaaccount == null) || (sbaaccount.getUsers() != activeuser.getId())) {
				  				System.out.println("Invalid bank account selected");
				  			}
				  			else if ("pending".equals(sbaaccount.getAstatus())) {
				  				System.out.println("Account approval still pending.");
				  			}
				  			else {
				  				selaccount = sbaaccount;
				  			}
				  		}
				  		else {
				  			System.out.println("Invalid bank account selected");
				  		}
				  		break;
				  		
				  	case "2":
				  		// View all user's bank accounts
				  		System.out.println(Main.nm);
							System.out.println("All bank accounts:");
							System.out.println("\n");
				  			List<Account> suaaccounts = new ArrayList<Account>();
				  			suaaccounts = ar.getByUser(activeuser.getId());
							for (Account temp : suaaccounts) {
					            System.out.println(temp.userView());
							}
				  		break;
				  		
				  	case "3":
				  		// View bank account's completed transactions
				  		System.out.println(Main.nm);
				  		if (selaccount == null) {
				  			System.out.println("First select a valid bank account.");
				  		}
				  		else {
							System.out.println("All completed transactions For bank account: " + selaccount.getAccnumber());
							System.out.println("\n");
				  			List<Transaction> sbatransactions = new ArrayList<Transaction>();
				  			sbatransactions = tr.getByAccount(selaccount.getId());
							for (Transaction temp : sbatransactions) {
								if ("completed".equals(temp.getTstatus()))
								System.out.println(temp.userView());
							}
				  		}
				  		break;
				  		
				  	case "4":
				  		// Withdrawal
				  		if (selaccount.getId() == null) {
				  			System.out.println("First select a valid bank account.");
				  		}
				  		else {
				  			System.out.println("Enter an ammount to withdraw from the selected account.");
				  			input = Main.collectInput();
							good = Main.checkMoney(input);
							if (good) {
								Double money = Double.parseDouble(input);
								if (money > selaccount.getBalance()) {
									System.out.println("Insufficient funds.");
								}
								else {
									Transaction withdrawal = makeTransaction("withdrawal", money, 1);
									withdrawal.setTransferid(0);
									tr.update(withdrawal);
									selaccount.setBalance(selaccount.getBalance() - money);
									ar.update(selaccount);
									bank.setBalance(selaccount.getBalance() - money);
									ar.update(bank);
									System.out.println("Withdrawal successful.");
									AppLogger.logger.info("Customer withdrew money.");
								}
							}
							else {
								System.out.println("That is not a valid ammount. Please try again.");
							}
				  		}
				  		break;
				  		
				  	case "5":
				  		// Deposit
				  		if (selaccount.getId() == null) {
				  			System.out.println("First select a valid bank account.");
				  		}
				  		else {
				  			System.out.println("Enter an ammount to deposit to the selected account.");
				  			input = Main.collectInput();
							good = Main.checkMoney(input);
							if (good) {
								Double money = Double.parseDouble(input);
								Transaction deposit = makeTransaction("deposit", money, 1);
								deposit.setTransferid(0);
								tr.update(deposit);
								
								selaccount.setBalance(selaccount.getBalance() + money);
								ar.update(selaccount);
								bank.setBalance(selaccount.getBalance() + money);
								ar.update(bank);
								System.out.println("Deposit successful.");
								AppLogger.logger.info("Customer withdrew money.");
							}
							else {
								System.out.println("That is not a valid ammount. Please try again.");
							}
				  		}
				  		break;
				  		
				  	case "6":
				  		// Electronic funds transfers (EFT) menu
				  		if (selaccount.getId() == null) {
				  			System.out.println("First select a valid bank account.");
				  		}
				  		else {
				  			transfersMenu();
				  		}
				  		break;
				  		
				  	case "7":
				  		// Request new checking account
				  		requestAccount("checking", activeuser);
				  		break;
				  		
				  	case "8":
				  		// Request new savings account
				  		requestAccount("savings", activeuser);
				  		break;
				  		
				  	case "9":
				  		logout = true;
				  		break;
				}
			}
			while (logout == false);
		}
		
		
		public void requestAccount(String type, User activeuser) {
			String input;
			boolean good = false;
			do {
				System.out.println(Main.nm);
				System.out.println("Please confirm request for new " + type + " account.");
				System.out.println("1. Confirm");
				System.out.println("2. Cancel");
				input = Main.collectInput();
				good = Main.checkIntInput(input, 0, 2);
				}
				while (good == false);
				
				switch(input) {
				  	case "1":
				  		// request new bank account
				  		Account reqacc = new Account();
				  		reqacc.setAtype(type);
				  		reqacc.setUsers(activeuser.getId());
				  		ar.add(reqacc);
				  		System.out.println("New bank account created. Approval pending.");
				  		AppLogger.logger.info("New bank account requested.");
				  		
				  		break;
				  		
				  	case "2":
				  		// cancel request
				  		break;
			}
		}
		
		
		public Transaction makeTransaction(String type, Double ammount, Integer otheracc) {
			Integer toacc = 0;
  			Integer fromacc = 0;
  			String tstatus = "";
  			Integer accountid = 0;

  			switch(type) {
		  		case "withdrawal":
		  			toacc = 1;
		  			fromacc = selaccount.getAccnumber();
		  			tstatus = "completed";
		  			accountid = selaccount.getId();
					break;
					
		  		case "deposit":
		  			toacc = selaccount.getAccnumber();
		  			fromacc = 1;
		  			tstatus = "completed";
		  			accountid = selaccount.getId();
					break;
					
		  		case "transferin":
		  			toacc = selaccount.getAccnumber();
		  			fromacc = otheracc;;
		  			tstatus = "pending";
		  			accountid = otheracc;
					break;
					
		  		case "transferout":
		  			toacc = otheracc;;
		  			fromacc = selaccount.getAccnumber();
		  			tstatus = "pending";
		  			accountid = selaccount.getId();
		  			break;
  			}
		  	System.out.println(accountid);
		  	Transaction t = new Transaction();
			t.setTtype(type);
			t.setAmount(ammount);
			t.setFromacc(fromacc);
			t.setToacc(toacc);
			t.setUserssn(activeuser.getSsn());
			t.setTstatus(tstatus);
			t.setAccounts(accountid);
			Transaction newt = tr.add(t);
			return newt;
  		}

		
		public void transfersMenu() {
			String input;
			boolean good = false;
			mainmenu = false;
			do {
				do {
				System.out.println(Main.nm);
				System.out.println("Selected bank account = " + selaccount.userView());
				System.out.println("\n");

				System.out.println("Electronic funds transfers (EFT) Menu");
				System.out.println("\n");
				System.out.println("Options:");
				System.out.println("1. View status of sent EFTs");
				System.out.println("2. Send new EFT");
				System.out.println("3. View status of received EFTs");
				System.out.println("4. Approve or reject received EFTs");
				System.out.println("5. Main menu");
				input = Main.collectInput();
				good = Main.checkIntInput(input, 0, 5);
				}
				while (good == false);
				
				System.out.println(Main.nm);
		  		List<Transaction> alltransactions = new ArrayList<Transaction>();
		  		alltransactions = tr.getByAccount(selaccount.getId());
		  		List<Transaction> alltransin = new ArrayList<Transaction>();
		  		List<Transaction> alltransout = new ArrayList<Transaction>();
		  		for (Transaction tran : alltransactions) {
					if ("transferout".equals(tran.getTtype())) {
						alltransout.add(tran);
					}
					else if ("transferin".equals(tran.getTtype())) {
						alltransin.add(tran);
					}
		  		}
				
				switch(input) {
				  	case "1":
				  		// View status of sent EFTs
				  		System.out.println("Sent Transfers");
				  		System.out.println("\n");
						System.out.println("Completed Transfers:");
						System.out.println("\n");
						for (Transaction transf : alltransout) {
							if ("completed".equals(transf.getTstatus())) {
								System.out.println(transf.userTransfer());
							}
						}
						System.out.println("\n");
						System.out.println("Rejected Transfers:");
						System.out.println("\n");
						for (Transaction transf : alltransout) {
							if ("rejected".equals(transf.getTstatus())) {
								System.out.println(transf.userTransfer());
							}
						}
						System.out.println("\n");
						System.out.println("Pending Transfers");
						System.out.println("\n");
						for (Transaction transf : alltransout) {
							if ("pending".equals(transf.getTstatus())) {
							System.out.println(transf.userTransfer());
							}
						}
				  		break;
				  		
				  	case "2":
				  		// Send new EFT
				  		String inputmoney;
				  		String inputacc;
				  		boolean moneygood;
				  		boolean accgood;
				  		
				  		System.out.println("Enter an ammount to transfer from the selected account.");
				  		inputmoney = Main.collectInput();
						moneygood = Main.checkMoney(inputmoney);
						System.out.println("Enter a destination account number.");
			  			inputacc = Main.collectInput();
						accgood = Main.checkIntInput(inputacc, 1, 999999999);
						
						if (moneygood) {
							Double money = Double.parseDouble(inputmoney);
							if (money > selaccount.getBalance()) {
								System.out.println("Insufficient funds.");
							}
							else {
								if (accgood) {
									Account otheracc = new Account();
									int inputaccint = Integer.parseInt(inputacc);
									otheracc = ar.getByAccNumber(inputaccint);
									if (otheracc == null || otheracc.getId() == selaccount.getId()) {
						  				System.out.println("Invalid bank account selected");
						  			}
						  			else {						  				
						  				Transaction temptransout = makeTransaction("transferout", money, inputaccint);
						  				tr.update(temptransout);
						  				Transaction temptransin = makeTransaction("transferin", money, inputaccint);
						  				temptransin.setTransferid(temptransout.getTransferid());
						  				tr.update(temptransin);
										System.out.println("Funds transer initiated. Approval pending.");
										AppLogger.logger.info("Customer sent EFT for approval.");
						  			}
								}
								else {
									System.out.println("That is not a valid account number. Please try again.");
								}
							}
						}
						else {
							System.out.println("That is not a valid ammount. Please try again.");
						}
				  		break;
				  		
				  	case "3":
				  		// View status of received EFTs
				  		System.out.println("Received Transfers");
				  		System.out.println("\n");
						System.out.println("Completed Transfers:");
						System.out.println("\n");
						for (Transaction transf : alltransin) {
							if ("completed".equals(transf.getTstatus())) {
								System.out.println(transf.userTransfer());
							}
						}
						System.out.println("\n");
						System.out.println("Rejected Transfers:");
						System.out.println("\n");
						for (Transaction transf : alltransin) {
							if ("rejected".equals(transf.getTstatus())) {
								System.out.println(transf.userTransfer());
							}
						}
						System.out.println("\n");
						System.out.println("Pending Transfers");
						System.out.println("\n");
						for (Transaction transf : alltransin) {
							if ("pending".equals(transf.getTstatus())) {
								System.out.println(transf.userTransfer());
							}
						}
				  		break;
				  		
				  	case "4":
				  		// Approve or reject received EFTs
				  		String reftinput;
				  		Account otheracc = new Account();
						boolean reftgood;
						
				  		System.out.println(Main.nm);
				  		System.out.println("Select pending received EFT by Transfer ID number:");
				  		System.out.println("\n");
				  		reftinput = Main.collectInput();
				  		reftgood = Main.checkIntInput(reftinput, 0, 999999999);
				  		if (reftgood) {
				  			int reftinputint = Integer.parseInt(reftinput);
				  			List<Transaction> transferpair = new ArrayList<Transaction>();
				  			transferpair = tr.getByTransferId(reftinputint);
				  			if (transferpair.size() == 2) {
				  				Transaction eftin = new Transaction();
				  				for (Transaction transf : transferpair) {
									if ("transferin".equals(transf.getTtype())) {
										eftin = transf;
									}
				  				}
				  				String eftinchoice;
				  				boolean eftgood = false;
				  				do {
				  					System.out.println(Main.nm);
				  					System.out.println(eftin.userTransfer());
				  					System.out.println("1. Approve");
				  					System.out.println("2. Reject");
				  					System.out.println("3. Cancel");
				  					eftinchoice = Main.collectInput();
				  					eftgood = Main.checkIntInput(eftinchoice, 0, 3);
				  					}
				  					while (eftgood == false);
				  					
				  					switch(eftinchoice) {
				  					  	case "1":
				  					  		// approve EFT
				  					  		Double ammount = 0.0;
				  					  		for (Transaction transf : transferpair) {
				  					  			transf.setTstatus("completed");
				  					  			if (selaccount.getId() != transf.getAccounts()) {
				  					  				otheracc = ar.getById(transf.getAccounts());
				  					  				ammount = transf.getAmount();
				  					  			}
				  					  			tr.update(transf);
				  					  		}
				  					  		selaccount.setBalance(selaccount.getBalance() + ammount);
				  					  		otheracc.setBalance(selaccount.getBalance() - ammount);
				  					  		ar.update(otheracc);
				  					  		selaccount = ar.update(selaccount);
				  					  		System.out.println("Money transfer has been approved.");
				  					  	AppLogger.logger.info("Customer approved EFT.");
				  					  		break;
				  					  		
				  					  	case "2":
				  					  		// reject EFT
				  					  		for (Transaction transf : transferpair) {
				  					  			transf.setTstatus("rejected");
				  					  			tr.update(transf);
				  					  		}
				  					  		System.out.println("Money transfer has been rejected.");
				  					  		AppLogger.logger.info("Customer rejected EFT");
				  					  		break;
				  					  		
				  					  	case "3":
				  					  		// cancel
				  					  		break;
				  					}
				  			}
				  			else {
				  				System.out.println("Invalid Transfer ID number selected.");
				  			}
				  		}
				  		else {
			  				System.out.println("Invalid Transfer ID number selected.");
			  			}
				  		break;
				  		
				  	case "5":
				  		mainmenu = true;
				  		break;
				}
		  	}
			while (mainmenu == false);
		}
}
		
			
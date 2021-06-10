package com.pathmer.project0.services;

import com.pathmer.project0.beans.User;
import com.pathmer.project0.driver.Main;
import com.pathmer.project0.logging.AppLogger;
import com.pathmer.project0.repositories.UserRepository;

public class LoginServices {
	public static UserRepository ur = new UserRepository();
	public static User activeuser;
	public static User newuser;
	public static User checkuser;


	public void mainM() {
		String input;
		boolean good = false;
		do {
		System.out.println(Main.nm);
		System.out.println("Please log in or create a new login account:");
		System.out.println("1. Log in");
		System.out.println("2. Create new login account");
		System.out.println("3. Leave site");
		input = Main.collectInput();
		good = Main.checkIntInput(input, 0, 3);
		}
		while (good == false);
		
		switch(input) {
		  	case "1":
		  		logIn();
		  		break;
		  	case "2":
		  		register();
		  		break;
		  	case "3":
		  		Main.quit = true;
		  		break;
		}
	}
	
	
	public void logIn() {
		activeuser = new User();
		String input;

		System.out.println(Main.nm);
		System.out.println("Enter your username:");
		input = Main.collectInput();
		activeuser.setUsernames(input);
		System.out.println("Enter your password:");
		input = Main.collectInput();
		activeuser.setPasswords(input);
		
		checkuser = ur.getByUsername(activeuser.getUsernames());
		
		if (checkuser != null) {
			if ((activeuser.getPasswords().equals(checkuser.getPasswords())) && (checkuser.getUstatus().equals("active"))) {
				activeuser = checkuser;
				checkuser = new User();
				System.out.println("Login Successful. Welcome " + activeuser.getFirstname() + " " + activeuser.getLastname());
				if (activeuser.getUtype().equals("employee")) {
					EmployeeServices es = new EmployeeServices();
					es.mainM();
				}
				else {
					CustomerServices cs = new CustomerServices();
					cs.mainM();
				}
			}
		}
		else {
			checkuser = new User();
			AppLogger.logger.info("Login failure.");
			System.out.println("Login failed. Please try again.");
		}
		activeuser = new User();
	}
	
	
	public void register() {
		newuser = new User();
		newuser.setUtype("customer");
		newuser.setUstatus("pending");
		String input;
		boolean good = false;
		
		System.out.println(Main.nm);
		System.out.println("Select a username:");
		input = Main.collectInput();
		newuser.setUsernames(input);
		System.out.println("Select a password:");
		input = Main.collectInput();
		newuser.setPasswords(input);
		System.out.println("Enter your first name:");
		input = Main.collectInput();
		newuser.setFirstname(input);
		System.out.println("Enter your last name:");
		input = Main.collectInput();
		newuser.setLastname(input);
		System.out.println("Enter your Social Security Number:");
		input = Main.collectInput();
		good = Main.checkIntInput(input, 100000000, 999999999);
		if (good) {
			int ssn = Integer.parseInt(input);
			newuser.setSsn(ssn);
		}
		
		User addeduser = ur.add(newuser);
		if (addeduser == newuser) {
			AppLogger.logger.info("New user account request.");
			System.out.println("New user account created. Approval pending.");
		}
		else {
			AppLogger.logger.info("Failed account creation request.");
			System.out.println("Login account creation failed. Please try again.");
		}
		newuser = new User();
	}
}

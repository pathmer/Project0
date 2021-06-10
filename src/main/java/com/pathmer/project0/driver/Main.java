package com.pathmer.project0.driver;

import java.util.Scanner;

import com.pathmer.project0.logging.AppLogger;
import com.pathmer.project0.services.LoginServices;


public class Main {
	
	public static Scanner scanner = new Scanner(System.in);
	public static LoginServices ls = new LoginServices();
	public static boolean quit = false;
	public static String nm = "\n-------------------------------------------------------------------------------------------------------------\n";


	public static void main(String[] args) {
		do {
			AppLogger.logger.info("Program started.");
			ls.mainM();
		}
		while (quit == false);
		System.out.println("Goodbye.");
	}
	

	public static String collectInput() {
		//collect user input
		String strArray = scanner.nextLine();
		return strArray;
	}
	
	
	public static boolean checkIntInput(String command, int low, int high) {
		// verify valid menu input
		boolean goodInput = false;
		try {
			int choicetest = Integer.parseInt(command);
			if ((choicetest <=  low) || (choicetest > high)) {
				System.out.println("Please enter a valid number.");
			}
			else {
				goodInput = true;
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Please enter a valid number.");
		}
		return goodInput;
	}
	
	
	public static boolean checkMoney(String command) {
		// verify valid menu input
		boolean goodInput = false;
		try {
			Double.parseDouble(command);
			String[] splitter = command.split("\\.");
			if ((splitter.length > 1) && (splitter[1].length() > 2)) {
				System.out.println("Please enter a valid ammount.");
			}
			else {
				goodInput = true;
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Please enter a valid ammount.");
		}
		return goodInput;
	}
}

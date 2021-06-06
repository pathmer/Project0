package PackageB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import PackageA.User;

@SuppressWarnings("serial")
public class Main {
	public static int menuChoice;
	public static String currentPage = "frontPage";
	public static boolean goodInput = false;
	public static User activeUser = null;
	
	//Page redirects
	public static HashMap<String, ArrayList<String>> menuChoicesMap = new HashMap<>();
	
	public static void main(String[] args) {
		buildMenuChoicesMap();
		do {
			int menuCount = printPage();
			String[] input = collectInput();
			goodInput = checkMenuInput(input, menuCount);
			if (goodInput) {
				String cp = currentPage;
				currentPage = menuChoicesMap.get(cp).get(menuChoice - 1);
			}
		}
		while (currentPage != "quitPage");
		System.out.println("Goodbye.");
	}
	
	private static String[] collectInput() {
		//collect user input
		Scanner scanner = new Scanner(System.in);
		String[] strArray = new String[] {scanner.nextLine()};
		return strArray;
	}
	
	private static boolean checkMenuInput(String[] command, int count) {
		// verify valid menu input
		boolean goodInput = false;
		try {
			int choicetest = Integer.parseInt(command[0]);
			if ((choicetest <= 0) || (choicetest > count)) {
				System.out.println("Please make a valid number choice.");
			}
			else {
				menuChoice = Integer.parseInt(command[0]);
				goodInput = true;
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Please make a valid number choice.");
		}
		return goodInput;
	}

	public static void buildMenuChoicesMap() {
		ArrayList<String> frontPage = new ArrayList<String>() {{add("loginPage");
																add("createUserPage");
																add("quitPage");}}; // done
		menuChoicesMap.put("frontPage", frontPage);
		ArrayList<String> loginPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("loginPage", loginPage);
		ArrayList<String> createUserPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("createUserPage", createUserPage);
		ArrayList<String> employeeHomePage = new ArrayList<String>() {{add("eManageAccountsPage");
																	   add("eViewTransactionsPage");
																	   add("frontPage");}}; // done
		menuChoicesMap.put("employeeHomePage", employeeHomePage);
		ArrayList<String> eManageAccountsPage = new ArrayList<String>() {{add("eManageUserAccountsPage");
																		  add("eManageBankAccountsPage");
																		  add("employeeHomePage");}}; //done
		menuChoicesMap.put("eManageAccountsPage", eManageAccountsPage);
		ArrayList<String> eManageUserAccountsPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("eManageUserAccountsPage", eManageUserAccountsPage);
		ArrayList<String> eManageBankAccountsPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("eManageBankAccountsPage", eManageBankAccountsPage);
		ArrayList<String> eViewTransactionsPage = new ArrayList<String>() {{add("eViewAllTransactionsPage");
																			add("eViewUserTransactionsPage");
																			add("eViewAccountTransactionsPage");
																			add("employeeHomePage");}}; //done
		menuChoicesMap.put("eViewTransactionsPage", eViewTransactionsPage);
		ArrayList<String> eViewAllTransactionsPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("eViewAllTransactionsPage", eViewAllTransactionsPage);
		ArrayList<String> eViewUserTransactionsPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("eViewUserTransactionsPage", eViewUserTransactionsPage);
		ArrayList<String> eViewAccountTransactionsPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("eViewAccountTransactionsPage", eViewAccountTransactionsPage);
		
		ArrayList<String> customerHomePage = new ArrayList<String>() {{add("cUpdateInformationPage");
																	   add("cManageBankAccountsPage");
																	   add("frontPage");}}; //done
		menuChoicesMap.put("customerHomePage", customerHomePage);
		ArrayList<String> cUpdateInformationPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("cUpdateInformationPage", cUpdateInformationPage);
		ArrayList<String> cManageBankAccountsPage = new ArrayList<String>() {{add("cRequestNewAccountPage");
																			  add("cViewtransactionsPage");
																			  add("cDepositPage");
																			  add("cWithdrawPage");
																			  add("cSendTranserPage");
																			  add("cReceiveTranserPage");
																			  add("cCloseAccountPage");
																			  add("customerHomePage");}}; //done
		menuChoicesMap.put("cManageBankAccountsPage", cManageBankAccountsPage);
		ArrayList<String> cRequestNewAccountPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("cRequestNewAccountPage", cRequestNewAccountPage);
		ArrayList<String> cViewtransactionsPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("cViewtransactionsPage", cViewtransactionsPage);
		ArrayList<String> cDepositPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("cDepositPage", cDepositPage);
		ArrayList<String> cWithdrawPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("cWithdrawPage", cWithdrawPage);
		ArrayList<String> cSendTranserPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("cSendTranserPage", cSendTranserPage);
		ArrayList<String> cReceiveTranserPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("cReceiveTranserPage", cReceiveTranserPage);
		ArrayList<String> cCloseAccountPage = new ArrayList<String>() {{add(" ");}};
		menuChoicesMap.put("cCloseAccountPage", cCloseAccountPage);
		
	}
	
	
	//Pages
	
	public static int printPage() {
		int count = 0;
		
		if (currentPage == "frontPage") {
			count = frontPage();
		}
		else if (currentPage == "quitPage") {
			count = quitPage();
		}
		else if (currentPage == "loginPage") {
			count = loginPage();
		}
		else if (currentPage == "createUserPage") {
			count = createUserPage();
		}
		return count;
	}
	
	public static int frontPage() {
		activeUser = null;
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Please log in or create a new login account:");
		System.out.println("1. Log in");
		System.out.println("2. Create new login account");
		System.out.println("3. Leave site");
		return 3;
	}
	
	public static int loginPage() {
		// Collect login data
				System.out.println("Please enter your user name:");
				String[] input = collectInput();
				// add code to check for valid entry
				String userName = input[0];
				
				System.out.println("Please enter your password:");
				input = collectInput();
				// add code to check for valid entry
				String userPassword = input[0];
				
				//add code to load user account

		return 0;
	}
	
	public static int createUserPage() {
		// Collect created login data
		System.out.println("Please create a username for login:");
		String[] input = collectInput();
		// add code to check for valid entry and unique name
		String userName = input[0];
		
		System.out.println("Please create a password for login:");
		input = collectInput();
		// add code to check for valid entry
		String userPassword = input[0];
		
		// add unapproved account to db

		return 0;
	}
	
	public static int quitPage() {
		return 0;
	}
	
	public static int employeeHomePage() {
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Please choose an activity:");
		System.out.println("1. Manage accouts");
		System.out.println("2. View Transactions");
		System.out.println("3. Logout");
		return 3;
	}
	
	public static int eManageAccountsPage() {
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Please choose an activity:");
		System.out.println("1. Manage user accounts");
		System.out.println("2. Manage bank accounts");
		System.out.println("3. Main menu");
		return 3;
	}
	
	public static int eManageUserAccountsPage() {
		// Choose an account to make active
		// approve
		// lock/unlock
		// delete
		return 0;
	}
	
	public static int eManageBankAccountsPage() {
		// approve
		// delete
		return 0;
	}
	
	public static int eViewTransactionsPage() {
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Please choose an activity:");
		System.out.println("1. View all bank transactions");
		System.out.println("2. View user transactions");
		System.out.println("3. View account transactions");
		System.out.println("4. Main menu");
		return 4;
	}
	
	public static int eViewAllTransactionsPage() {
		// view all transactions
		return 0;
	}
	
	public static int eViewUserTransactionsPage() {
		// select user
		//view user transactions
		return 0;
	}
	
	public static int eViewAccountTransactionsPage() {
		//select account
		//view account transactions
		return 0;
	}
	
	public static int customerHomePage() {
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Please choose an activity:");
		System.out.println("1. Update personal information");
		System.out.println("2. Manage accouts");
		System.out.println("3. Logout");
		return 2;
	}
	
	public static int cUpdateInformationPage() {
		// update personal information
		return 0;
	}
	
	public static int cManageBankAccountsPage() {
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Please choose an activity:");
		System.out.println("1. Request new account");
		System.out.println("2. View account transactions");
		System.out.println("3. Deposit");
		System.out.println("4. Withdrawl");
		System.out.println("5. Send money transfer");
		System.out.println("6. Received money transfers");
		System.out.println("7. Close account");
		System.out.println("8. Main menu");
		return 8;
	}
	
	public static int cRequestNewAccountPage() {
		// request an account
		return 0;
	}
	
	public static int cViewtransactionsPage() {
		// select account to view transactions
		return 0;
	}
	
	public static int cDepositPage() {
		// select account to deposit
		return 0;
	}
	
	public static int cWithdrawPage() {
		// select account to withdrawl
		return 0;
	}
	
	public static int cSendTranserPage() {
		// select account to send transfer
		return 0;
	}
	
	public static int cReceiveTranserPage() {
		// select account to approve transfers
		return 0;
	}

	public static int cCloseAccountPage() {
		// select account to close
		return 0;
	}
}

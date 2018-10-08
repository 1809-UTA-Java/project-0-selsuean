package com.revature.BankingApp;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.BankingApp.repository.UserDAO;

public class BankingApp {
	static ArrayList<User> userList = new ArrayList<User>();
	
 	public static void login(Scanner sc) {

		System.out.println("Are you a new user? Y/N");
		String ans = sc.next();
		sc.nextLine();

		if (ans.equalsIgnoreCase("y")) {
			newUser(sc);
		}

		else if (ans.equalsIgnoreCase("n")) {
			searchUser(sc);
		}

		else {
			System.out.println("Wrong input. \nAccepted inputs are: 'y', 'Y', 'n', or 'N'.");
			login(sc);
		}
	}

	/**
	 * 
	 * 
	 * @param sc
	 */
	public static void searchUser(Scanner sc) {
//		String strU = null;
//		String strP = null;

		System.out.println("Please login. \n");
		System.out.println("Enter your username: ");
		String storeUser = sc.nextLine();

		System.out.println("Type your password: ");
		String storePW = sc.nextLine();
		
		UserDAO uDAO = new UserDAO();
		User u = uDAO.checkForUser(storeUser);
		
		//if username does not match
		if (u == null) {
			System.out.println("Incorrect login, please try again.");
			searchUser(sc);
		}
		else if (u.password.equals(storePW)) {
			u.setAccountList(storeUser);
			actionPage(u, sc);
		}
		else if (!u.password.equals(storePW)) {
			System.out.println("Incorrect login, please try again.");
			searchUser(sc);
		}
		
//		for (User obj : userList) {
//			strU = obj.getUsername();
//			strP = obj.getPassword();
//
//			if (strU.equals(storeUser) && strP.equals(storePW)) {
//
//				actionPage(obj, sc);
//			} else {
//				System.out.println("Incorrect login. Try again.");
//				searchUser(sc);
//			}
//		}
	}

	public static void actionPage(User currUser, Scanner sc) {

		System.out.println("Welcome to THE BANK.\n");

		System.out.println(
				"What would you like to do?\n" + "1 - View user information.\n" + "2 - View account information.\n"
						+ "3 - Apply to create an account.\n" + "4 - Apply to a joint account.\n"
						+ "5 - Deposit into an account.\n" + "6 - Withdraw from an account.\n" + "7 - Transfer money.");

		int ans = sc.nextInt();

		if (ans == 1) {
			currUser.displayUserInfo();

			actionPage(currUser, sc);

		} else if (ans == 2) {
			if (currUser.accountList.size() != 0) {
				currUser.displayAccInfo();
			} else {
				System.out.println("You have zero accounts.\n");
			}

			actionPage(currUser, sc);

		} else if (ans == 3) {
			// TODO: terminal dialogue; add check in dao for more than one type application pending 
			currUser.createApplication("Normal", currUser);
			actionPage(currUser, sc);
			
		} else if (ans == 4) {
			// TODO: terminal dialogue
			currUser.createApplication("Joint", currUser);
			actionPage(currUser, sc);
			
		} else if (ans == 5) {
			actionMoney("deposit", currUser, sc);
			
		} else if (ans == 6) {
			actionMoney("withdraw", currUser, sc);
			
		} else if (ans == 7) {
			actionMoney("transfer", currUser, sc);
			
		} else {
			System.out.println("Input out of range. Please enter numbers 1-7 according to your desired action.\n");
			actionPage(currUser, sc);
		}
	}

	public static void actionMoney(String action, User currUser, Scanner sc) {
		if (currUser.accountList.isEmpty()) {
			System.out.println("You currently have no open accounts. \n"
					+ "Please apply to open an account or await an admin to approve your application.");
			actionPage(currUser, sc);
		} else {
			if (action.equals("deposit")) {
				System.out.println("Please enter the account ID of the account you would like to deposit into."
						+ " Your accounts are listed below. \n");
				
				currUser.displayAccInfo();
				
				int ans = sc.nextInt();
				
				Account thisAcc = currUser.getAccount(ans);

				System.out.println("How much would you like to deposit?");
				double depAm = sc.nextInt();
				
				double output = currUser.depositMoney(currUser, thisAcc, depAm);
				System.out.println("Balance in " + thisAcc.accountType + " is now $" + output);
				
				actionPage(currUser, sc);

			} else if (action.equals("withdraw")) {
				System.out.println("Please enter the account ID of the account you would like to withdraw from."
						+ " Your accounts are listed below. \n");
				currUser.displayAccInfo();
				int ans = sc.nextInt();
				Account thisAcc = currUser.getAccount(ans);

				System.out.println("How much would you like to withdraw?");
				double withAm = sc.nextInt();
				if ((thisAcc.amount - withAm) < 0) {
					System.out.println(
							"Withdrawing this amount will make your account balance negative. Please withdraw a smaller amount or deposit more money into this account first \n");
					actionPage(currUser, sc);
				} else {
					double wOutput = currUser.withdrawMoney(currUser, thisAcc, withAm);
					System.out.println("Balance in " + thisAcc.accountType + " is now $" + wOutput);
					
					actionPage(currUser, sc);
				}

			} else if (action.equals("transfer")) {
				System.out.println("Please enter the account ID of the account you would like to transfer from."
						+ " Your accounts are listed below. \n");
				currUser.displayAccInfo();
				int transF = sc.nextInt();
				Account accSource = currUser.getAccount(transF);

				System.out.println("How much would you like to transfer?");
				double amt = sc.nextInt();
				// check if there's enough money in accSource to transfer
				if ((accSource.amount - amt) < 0) {
					System.out.println(
							"Withdrawing this amount will make your account balance negative. Please withdraw a smaller amount or deposit more money into this account first \n");
					actionPage(currUser, sc);
				} 

				System.out.println("Please enter the account ID of the account you would like to transfer to."
						+ " Your accounts are listed below. \n");
				currUser.displayAccInfo();
				int transT = sc.nextInt();
				Account accDest = currUser.getAccount(transT);
				
				String prMes = currUser.transferMoney(currUser, accSource, accDest, amt);
				System.out.println(prMes);
				actionPage(currUser, sc);
			}
		}
	}

	public static void newUser(Scanner sc) {
		User newUser = new User();

		userList.add(newUser);

		System.out.println("What is your full name?");
		String makeName = sc.nextLine();
		newUser.name = makeName;
		newUsername(newUser, sc);
	}

	public static void newUsername(User currUser, Scanner sc) {

		System.out.println("Enter your username:");
		String makeUsername = sc.nextLine();
		makeUsername = makeUsername.replaceAll("\\s", "");

		boolean availCheck = checkAvailability(makeUsername);
		if (availCheck == true) {

			System.out.print("Your username is: " + makeUsername
					+ ". \nIf there were any whitespaces included in your entry, " + "they have been removed.\n");

			currUser.username = makeUsername;

			createPW(sc, currUser);
		} else {
			System.out.println("That username has already been taken, please try a different username. \n");
			newUsername(currUser, sc);
		}

	}

	
	public static void createPW(Scanner sc, User user) {
		System.out.println("Enter your password: ");
		String makePW = sc.nextLine();

		System.out.println("Enter your password again: ");
		String pwCheck = sc.nextLine();

		if (makePW.equals(pwCheck)) {
			user.password = makePW;
			fillUserInfo(user, sc);
		} else {
			System.out.println("Password does not match. Please try again.");
			createPW(sc, user);
		}
	}

	public static void fillUserInfo(User currUser, Scanner sc) {
		// birthday
		System.out.println("What is your birthday month and day?");
		String makeBday = sc.nextLine();
		currUser.birthday = makeBday;

		// age
		System.out.println("What is your age?");
		int makeAge = sc.nextInt();
		currUser.age = makeAge;

		// city
		sc.nextLine();
		System.out.println("What is your current city?");
		String makeCity = sc.nextLine();
		currUser.city = makeCity;

		System.out.println("User successfully added to system. \n");
		UserDAO udao = new UserDAO();
		System.out.println(udao.insertUser(currUser));

		searchUser(sc);
	}

	public static boolean checkAvailability(String input) {
		
		UserDAO uDAO = new UserDAO();
		User u = uDAO.checkForUser(input);
		if (u == null) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void main(String[] args) {
		// new user input reader
		Scanner sc = new Scanner(System.in);
		login(sc);
		System.out.println("Exiting application. Thank you for choosing THE BANK.");
	}
}
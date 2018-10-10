package com.revature.BankingApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.BankingApp.menu.Menu;
import com.revature.BankingApp.repository.AccountDAO;
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

	public static void searchUser(Scanner sc) {

		System.out.println("Please login. \n");
		System.out.println("Enter your username: ");
		String storeUser = sc.nextLine();

		System.out.println("Type your password: ");
		String storePW = sc.nextLine();

		UserDAO uDAO = new UserDAO();
		Person u = uDAO.checkForUser(storeUser);

		// if username does not match
		if (u == null) {
			System.out.println("Incorrect login, please try again.");
			searchUser(sc);
		} else if (u.password.equals(storePW)) {
			checkRole(u, storeUser, sc);
		} else if (!u.password.equals(storePW)) {
			System.out.println("Incorrect login, please try again.");
			searchUser(sc);
		}

	}

	public static void checkRole(Person p, String username, Scanner sc) {
		if (p instanceof Employee) {
			Employee e = (Employee) p;
			empAction(e, sc);
		}

		
		else if (p instanceof User) {
			User u = (User) p;
			//u.setAccountList(username);
			actionPage(u, sc);
		}

		else if (p instanceof Admin) {
			Admin a = (Admin) p;
			adminAction(a, sc);
		}
	}
	
//	"1 - View a user's information.\n" + 
//	"2 - View a user's account information.\n" +
//	"3 - Take action on open applications.\n
	public static void empAction(Employee e, Scanner sc) {
		int ans = Menu.employeeView(sc, e);
		
		switch (ans) {
		case 1:
			Employee.viewUserInfo();
			empAction(e, sc);
			break;
			
		case 2:
			System.out.println("Enter a username to receive the list of accounts under their name: ");
			String username = sc.next();
			Employee.viewAccountInfo(username);
			empAction(e, sc);
			break;
			
		case 3:
			System.out.println("The users below have submitted applications to open an account: \n ");
			List<Object> thisAppList = Employee.approveApplication("false");
			if (thisAppList.isEmpty()) {
				System.out.println("There are currently no open applications.");
				empAction(e, sc);
			}
			else {
			for (Object obj : thisAppList) {
				if (obj instanceof String) {
					System.out.println("Username: " + obj + "  ");
				}
				if (obj instanceof Integer) {
					System.out.println("AccountID: " + obj + "\n");
				}
			}
			}
			
			AccountDAO accdao = new AccountDAO();
			
			System.out.println("Enter a username to review their account information. Else, enter an account ID to approve the application. \n");
			if (sc.hasNextInt()) {
				System.out.println("Updating application...");
				System.out.println(accdao.updateApplicationStatus(sc.nextInt(), "pend"));	
				empAction(e, sc);
			}
			if (sc.hasNext()) {
				Employee.viewAccountInfo(sc.next());
				empAction(e, sc);
			}
			
			else {
				System.out.println("Invalid input. Please try again.");
			}
		
			break;
			
		default:
				System.out.println("Invalid output. Please try again.");
				empAction(e, sc);
			break;
		}
	}

//	"1 - View a user's information.\n" + 
//	"2 - View a user's account information.\n" +
//	"3 - Take action on a user's account.\n" +
//	"4 - Take action on pending applications.\n");
	public static void adminAction(Admin a, Scanner sc) {
		int ans = Menu.adminView(sc, a);
		switch (ans) {
		case 1:
			System.out.println("The users below have submitted applications to open an account: \n ");
			Employee.viewUserInfo();
			adminAction(a, sc);
			break;
			
		case 2:
			System.out.println("Enter a username to receive the list of accounts under their name: ");
			String username = sc.next();
			Employee.viewAccountInfo(username);
			adminAction(a, sc);
			break;
			
		case 3:
			
			break;
		
		case 4:
			List<Object> thisAppList = Employee.approveApplication("pend");
			System.out.println("The users below have pending applications to open an account: \n ");
			
			if (thisAppList.isEmpty()) {
				System.out.println("There are currently no pending applications.");
				adminAction(a, sc);
			}
			else {
			for (Object obj : thisAppList) {
				if (obj instanceof String) {
					System.out.println("Username: " + obj + "  ");
				}
				if (obj instanceof Integer) {
					System.out.println("AccountID: " + obj + "\n");
				}
			}
			}
			AccountDAO accdao = new AccountDAO();
			
			System.out.println("Enter a username to review their account information. Else, enter an account ID to open the account. \n");
			if (sc.hasNextInt()) {
				System.out.println("Creating account...");
				System.out.println(accdao.updateApplicationStatus(sc.nextInt(), "true"));	
				adminAction(a, sc);
			}
			if (sc.hasNext()) {
				Employee.viewAccountInfo(sc.next());
				adminAction(a, sc);
			}
			
			else {
				System.out.println("Invalid input. Please try again.");
			}
			break;
		}
	}

	public static void actionPage(User currUser, Scanner sc) {
		currUser.setAccountList(currUser.username);
		int ans = Menu.customerView(sc, currUser);

		switch (ans) {
		case 1:
			currUser.displayUserInfo();
			actionPage(currUser, sc);
			break;

		case 2:
			if (currUser.accountList.size() != 0) {
				currUser.displayAccInfo();
			} else {
				System.out.println("You have zero accounts.\n");
			}

			actionPage(currUser, sc);
			break;

		case 3:
			// TODO: terminal dialogue; add check in dao for more than one type application
			// pending
			System.out.println("Would you like a checkings or a savings account?");
			String ansTypeAcc = sc.next();
			if (ansTypeAcc.equals("checkings")) {
				System.out.println(currUser.createApplication("checkings", currUser));
				actionPage(currUser, sc);
			}
			else if (ansTypeAcc.equals("savings")) {
				System.out.println(currUser.createApplication("savings", currUser));
				actionPage(currUser, sc);
			}
			else {
				System.out.println("Input not within range, please try again.");
				actionPage(currUser, sc);
			}
			break;

		case 4:
			System.out.println("Who would you like to invite to this joint account? Please enter their username.");
			String JUsername = sc.next();
			
//			UserDAO judao = new UserDAO();
//			Person tempU = judao.checkForUser(JUsername);
//			
//			if (tempU == null) {
//				System.out.println("Username does not exist. Please try again.");
//				actionPage(currUser, sc);
//			}
//			
			System.out.println(currUser.createApplication("Joint", currUser, JUsername));
			actionPage(currUser, sc);
			break;

		case 5:
			actionMoney("deposit", currUser, sc);
			break;

		case 6:
			actionMoney("withdraw", currUser, sc);
			break;

		case 7:
			actionMoney("transfer", currUser, sc);
			break;

		default:
			System.out.println("Input out of range. Please enter numbers 1-7 according to your desired action.\n");
			actionPage(currUser, sc);
			break;
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
		Person u = uDAO.checkUsername(input);
		if (u == null) {
			return true;
		} else {
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
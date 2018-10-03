package com.revature.BankingApp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class BankingApp {
	// arraylist of user objects
	static ArrayList<User> userList = new ArrayList<User>();

	public static void login() {

		// new user input reader
		Scanner sc = new Scanner(System.in);

		// asks if person is a new user and stores user input in ans
		System.out.println("Are you a new user? Y/N");
		String ans = sc.next();
		sc.nextLine();

		// if they r a new user, goes to newUser method
		if (ans.equalsIgnoreCase("y")) {
			newUser(sc);
		}

		// if not, goes to searchUser page
		else if (ans.equalsIgnoreCase("n")) {
			searchUser(sc);
		}

		// if does not match accepted inputs, prints declared statement and starts
		// login() method again
		else {
			System.out.println("Wrong input. \nAccepted inputs are: 'y', 'Y', 'n', or 'N'.");
			login();
		}
	}

	// takes username and compares it to the user.username field in the user object
	// stored
	// in an arraylist
	public static void searchUser(Scanner sc) {
		String strU = null;
		String strP = null;

		System.out.println("Please login. \n");
		System.out.println("Enter your username: ");
		String storeUser = sc.nextLine();

		System.out.println("Type your password: ");
		String storePW = sc.nextLine();

		for (User obj : userList) {
			strU = ((Person) obj).getUsername();
			strP = ((Person) obj).getPassword();

			if (strU.equals(storeUser) && strP.equals(storePW)) {

				actionPage(obj, sc);
			} else {
				System.out.println("Incorrect login. Try again.");
				searchUser(sc);
			}
		}
	}

	public static void actionPage(User currUser, Scanner sc) {

		System.out.println("Welcome to THE BANK.\n");

		System.out.println(
				"What would you like to do?\n " + "1 - View user information.\n" + "2 - View account information.\n"
						+ "3 - Apply to create an account.\n" + "4 - Apply to a joint account.");

		int ans = sc.nextInt();
		sc.nextLine();

		if (ans == 1) {
			System.out.println("Name: " + currUser.name + "\n" + "Username: " + currUser.username + "\n" + "Age: "
					+ currUser.age + "\n" + "Birthday: " + currUser.birthday + "\n" + "City: " + currUser.city + "\n"
			// TODO: + "Number of accounts: " + currUser.getNumAccounts() + "\n"
			);
		} else if (ans == 2) {
			for (Account acc : currUser.accountList) {
				System.out.println("Account type: " + acc.accountType + "\n" + "Amount: " + acc.amount + "\n"
						+ "Number of owners: " + acc.numOwner + "\n");
				acc.getOwners();
			}

		} else if (ans == 3) {
			// TODO: create application
			createApplication("normal", currUser);
		} else if (ans == 4) {
			// TODO: create application
			createApplication("joint", currUser);
		}
	}

	public static void createApplication(String appType, User u) {
		// TODO: create
	}

	public static void actionMoney(User currUser, Scanner sc) {

	}

	public static void newUser(Scanner sc) {
		// creates new User object
		User newUser = new User();

		// adds User instance to arrayList
		userList.add(newUser);

		// gets name
		System.out.println("What is your full name?");
		String makeName = sc.nextLine();
		newUser.name = makeName;
		fillUserInfo(newUser, sc);
	}

	public static void fillUserInfo(User currUser, Scanner sc) {
		// gets username; if there are spaces, removes spaces from string
		System.out.println("Enter your username:");
		String makeUsername = sc.nextLine();
		makeUsername = makeUsername.replaceAll("\\s", "");

		boolean availCheck = checkAvailability(makeUsername);
		if (availCheck == true) {

			System.out.print("Your username is: " + makeUsername
					+ ". \nIf there were any whitespaces included in your entry, " + "they have been removed.\n");

			// sets User instance username
			currUser.username = makeUsername;

			createPW(sc, currUser);
		} else {
			System.out.println("That username has already been taken, please try a different username. \n");
			fillUserInfo(currUser, sc);
		}

		// birthday
		System.out.println("What is your birthday month and day?");
		String makeBday = sc.nextLine();
		currUser.birthday = makeBday;

		// age
		System.out.println("What is your age?");
		int makeAge = sc.nextInt();
		currUser.age = makeAge;

		// city
		System.out.println("What is your current city?");
		String makeCity = sc.nextLine();
		currUser.city = makeCity;

	}

	public static boolean checkAvailability(String input) {
		boolean avail = true;

		for (User iUser : userList) {
			if (userList.size() != 1) {
				if (iUser.username.equals(input)) {
					return false;
				}
			} else {
				return true;
			}

		}

		return avail;
	}

	public static void createPW(Scanner sc, Person user) {
		System.out.println("Enter your password: ");
		String makePW = sc.nextLine();

		System.out.println("Enter your password again: ");
		String pwCheck = sc.nextLine();

		if (makePW.equals(pwCheck)) {
			user.password = makePW;
			searchUser(sc);
		} else {
			System.out.println("Password does not match. Please try again.");
			createPW(sc, user);
		}
	}

	public static void main(String[] args) {
		login();
	}
}

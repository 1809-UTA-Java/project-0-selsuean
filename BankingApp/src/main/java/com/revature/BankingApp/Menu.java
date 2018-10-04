package com.revature.BankingApp;

import java.util.Scanner;

public class Menu {

	public static void menuPage(Scanner sc, User user) {
		System.out.println("Welcome to THE BANK.\n");

		System.out.println(
				"What would you like to do?\n" + 
						"1 - View user information.\n" + 
						"2 - View account information.\n" +
						"3 - Apply to create an account.\n" + 
						"4 - Apply to a joint account.\n" + 
						"5 - Deposit from an account.\n" + 
						"6 - Withdraw from an account.\n" + 
						"7 - Transfer money.");
		
		int choice = sc.nextInt();
		
		switch (choice) {
		case 1: 
			System.out.println("Name: " + user.name + "\n" + "Username: " + user.username + "\n" + "Age: "
					+ user.age + "\n" + "Birthday: " + user.birthday + "\n" + "City: " + user.city + "\n"
					+ "Number of accounts: " + user.getNumAccounts() + "\n");
			break;
		case 2:
			break;
		}

		menuPage(sc, user);
	}
}

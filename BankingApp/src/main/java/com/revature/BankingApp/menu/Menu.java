package com.revature.BankingApp.menu;

import java.util.Scanner;

import com.revature.BankingApp.Person;

public class Menu {

	public static int customerView(Scanner sc, Person user) {
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
		
		return choice;
	}
	
	public static int employeeView(Scanner sc, Person emp) {
		System.out.println("Welcome to THE BANK.\n");
		
		System.out.println(
				"What would you like to do?\n" + 
						"1 - View a user's information.\n" + 
						"2 - View a user's account information.\n" +
						"3 - Take action on open applications.\n");
		
		int choice = sc.nextInt();
		return choice;
	}
	
	public static int adminView(Scanner sc, Person admin) {
		System.out.println("Welcome to THE BANK.\n");
		
		System.out.println(
				"What would you like to do?\n" + 
						"1 - View a user's information.\n" + 
						"2 - View a user's account information.\n" +
						"3 - Take action on a user's account.\n" +
						"4 - Take action on pending applications.\n");
		
		int choice = sc.nextInt();
		return choice;
	}
}

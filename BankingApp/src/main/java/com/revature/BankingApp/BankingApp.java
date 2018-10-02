package com.revature.BankingApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class BankingApp 
{
	//arraylist of user objects 
	static ArrayList<Object> userList = new ArrayList<Object>();
	
	public static void login() {
		
		//new user input reader
		Scanner sc = new Scanner(System.in);
		
		//asks if person is a new user and stores user input in ans
		System.out.println("Are you a new user? Y/N");
		String ans = sc.next();
		sc.nextLine();
		
		//if they r a new user, goes to newUser method 
		if (ans.equalsIgnoreCase("y")) {
			System.out.println("newuser");
			newUser(sc);
		}
		
		//if not, goes to login page
		else if (ans.equalsIgnoreCase("n")) {
			System.out.println("existing user");
			//TODO: go to logIn();
		}
		
		//if does not match accepted inputs, prints declared statement and starts login() method again 
		else {
			System.out.println("Wrong input. \nAccepted inputs are: 'y', 'Y', 'n', or 'N'.");
			login();
		}
	}
	
	public static void newUser(Scanner sc) {
		//creates new User object
		Person newUser = new User();
		
		//adds User instance to arrayList
		userList.add(newUser);
		
		//gets name 
		System.out.println("What is your full name?");
		String makeName = sc.nextLine();
		newUser.name = makeName;
		
		//gets username; if there are spaces, removes spaces from string
		System.out.println("Enter your username:");
		String makeUsername = sc.nextLine();
		makeUsername = makeUsername.replaceAll("\\s","");
		
		//TODO: check for username availability 
		
		System.out.print("Your username is: " + makeUsername + ". \nIf there were any whitespaces included in your entry, "
				+ "they have been removed.\n");
		
		//sets User instance username 
		newUser.username = makeUsername;
		
		System.out.println("Enter your password: ");
		String makePW = sc.nextLine();
		
		System.out.println("Enter your password again: ");
		String pwCheck = sc.nextLine();
		
		if (makePW.equals(pwCheck)) {
			newUser.password = makePW;
		}
		else {
			System.out.println("Password does not match. Please try again.");
		}
	}
	
    public static void main( String[] args)    {
    	login();
    }
}

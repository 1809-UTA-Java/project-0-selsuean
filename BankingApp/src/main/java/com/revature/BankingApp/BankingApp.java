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
	
	//Map<String, ArrayList> bankDatabase = new HashMap<String, ArrayList>();
	
	public static void login() {
		//new user input reader
		Scanner sc = new Scanner(System.in);
		//asks if person is a new user and stores user input in ans
		System.out.println("Are you a new user? Y/N");
		String ans = sc.next();
		sc.nextLine();
		//System.out.println(ans);

		
		//if they r a new user, goes to newUser method 
		if (ans.equalsIgnoreCase("y")) {
			System.out.println("newuser");
			newUser(sc);
			//go to newUser();
		}
		
		//if not, goes to login page
		else if (ans.equalsIgnoreCase("n")) {
			System.out.println("existing user");
			//go to logIn();
		}
		
		//if does not match accepted inputs, prints declared statement and starts login() method again 
		else {
			System.out.println("Wrong input. \nAccepted inputs are: 'y', 'Y', 'n', or 'N'.");
			login();
		}
	}
	
	public static void newUser(Scanner sc) {
		Person newUser = new User();
		
		System.out.println("Enter your username:");
		String makeUsername = sc.nextLine();
		makeUsername = makeUsername.replaceAll("\\s","");
		//TODO: check for username availability 
		System.out.print("Your username is: " + makeUsername + ". \nIf there were any whitespaces included in your entry, "
				+ "they have been removed.");
		newUser.username = makeUsername;
	}
	
    public static void main( String[] args )
    {
        //Map<String, ArrayList> bankDatabase = new HashMap<String, ArrayList>();
    	login();
    }
}

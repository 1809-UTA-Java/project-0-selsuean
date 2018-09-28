// sandbox

package dev;

public class sandbox {

	public static void login() {
		//new user input reader
		Scanner sc = new Scanner(System.in);
		//asks if person is a new user and stores user input in ans
		System.out.println("Are you a new user? Y/N");
		Char ans = sc.next();
		System.out.println(ans);

		
		//if they r a new user, goes to newUser method 
		if (ans == 'Y' || ans == 'y') {
			System.out.println("newuser");
			//go to newUser();
		}
		
		//if not, goes to login page
		else if (ans == 'N' || ans == 'n') {
			System.out.println("existing user");
			//go to logIn();
		}
	}


	public static void newUser() {
		//should have three parameters: username, password, and password re-entry 
		//store answers in variables
		//create new Person object?
	
	}

	public static void logIn() {
		// checks userID and then redirects to either
		// custPage, empPage, or adminPage 
	}

	public static void custPage() {
		//gives choices
		// 1 - apply for a new account
		// 2 - withdraw from account
		// 3 - deposit into account
		// 4 - transfer money
		// 5 - logout 
	}

	public static void empPage() {
		// employee should be a class with a method that has
		// access to account information stored in a map -> arraylist 
		// 1 - view
	}

	public static void main(String args[]) {
		System.out.println("placeholder text."); 
	}	

}

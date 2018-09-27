// sandbox

package dev;

public class sandbox {

	public static void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Are you a new user? Y/N");
		Char ans = sc.next();
		System.out.println(ans);
		
		if (ans == 'Y' || ans == 'y') {
			//go to newUser();
		}
		
		else if (ans == 'N' || ans == 'n') {
			//go to logIn();
		}
	}


	public static void main(String args[]) {
		System.out.println("placeholder text."); 
	}	

}

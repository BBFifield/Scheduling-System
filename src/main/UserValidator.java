package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserValidator {
	
	public static String userLoggedIn;
	private static File loginInfo = new File("resources/loginInfo.txt");
	private static Scanner in = null;
	
	private UserValidator() {}
	
	public static boolean validate(String username, String password) throws FileNotFoundException {
		in = new Scanner(loginInfo);
		String userNameCurrent = "";
		String passwordCurrent = "";
		
		while(in.hasNextLine()) {
			userNameCurrent = in.next();
			passwordCurrent = in.next();
			if(userNameCurrent.equals(username) && passwordCurrent.equals(password)) {
				userLoggedIn = username; 
				in.close();
				return true;
			}
		}
		
		in.close();
		return false;
	}
}



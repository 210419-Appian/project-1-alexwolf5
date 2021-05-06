package com.piggybank.services;

import java.util.Scanner;

import com.piggybank.daos.UsersDAO;
import com.piggybank.daos.UsersDAOImpl;
import com.piggybank.models.Users;

public class Login {

	public void login() {
		Scanner input = new Scanner(System.in);
		System.out.printf("Username: ");
		String username = input.next();
		System.out.printf("Password: ");
		String password = input.next();
		
		UsersDAO log = new UsersDAOImpl();
		
		int userID = log.loggingIn(username, password);
		
		if (userID == 0) {
			System.out.println("Sorry, your account was not found.  Please try again!");
			input.close();
			login();
		}
		
		else if (userID != 0){
			System.out.println("Welcome, user " + userID);
			input.close();
			Users user = new Users();
			if (user.getRole() == "User") {
				//UserMenuLink
			}
			else if (user.getRole() == "Employee") {
				//EmployeeMenuLink
			}
			else if (user.getRole() == "Admin") {
				//AdminMenuLink
			}
			
		}
		
		input.close();
	}
}

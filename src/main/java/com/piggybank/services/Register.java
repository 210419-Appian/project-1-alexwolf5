package com.piggybank.services;

import java.util.Scanner;
import com.piggybank.models.Users;
import com.piggybank.daos.UsersDAO;
import com.piggybank.daos.UsersDAOImpl;

public class Register extends Users{

	public String Registration() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("This is the Registration Page!");
		
		System.out.printf("Please enter your first name: ");
		setFirstName(input.nextLine());
		System.out.printf("Please enter your last name: ");
		setLastName(input.nextLine());
		System.out.printf("Please enter your email: ");
		setEmail(input.nextLine());
		System.out.printf("Please enter your desired username: ");
		setUsername(input.nextLine());
		System.out.printf("Please enter your desired password: ");
		setPassword(input.nextLine());
////////////////////////////
		UsersDAO reg = new UsersDAOImpl();
		
		reg.register(getUsername(), getPassword(), getFirstName(), getLastName(), getEmail());
		
		input.close();
		return null;
	}
}

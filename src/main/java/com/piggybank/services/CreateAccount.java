package com.piggybank.services;

import java.util.Scanner;

import com.piggybank.daos.AccountsDAO;
import com.piggybank.daos.AccountsDAOImpl;

public class CreateAccount {

	public static void main(String[] args) {
		CreateAccount ca = new CreateAccount();
		ca.createAcct();
	}
	
	public void createAcct() {
		AccountsDAO create = new AccountsDAOImpl();
		Scanner input = new Scanner(System.in);
		
		System.out.println("What type of account do you wish to create, checking or savings?:");
		String type = input.nextLine();
		
		boolean creation = create.createAccount(type);
		
		if (creation == true) {
			System.out.println("Noice!");
		}
		else {
			System.out.println("Sad...");
		}
	}
}

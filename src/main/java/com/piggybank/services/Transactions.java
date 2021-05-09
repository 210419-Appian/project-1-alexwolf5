package com.piggybank.services;

public class Transactions {

	public double deposit(double balance, double deposit) {
		
		balance += deposit;
		return balance;
	}
	
	public double withdraw(double balance, double withdraw) {
		
		double checkBalance = balance;
		checkBalance -= withdraw;
		if (checkBalance >= 0) {
			
			balance -= withdraw;
			return balance;
		}
		
		else {
			//Add notification that transaction wasn't completed
			return balance;
		}
	}
	
	public double transfer(double balance, double transfer) {
		
		double checkBalance = balance;
		checkBalance -= transfer;
		if (checkBalance >= 0) {
			
			balance -= transfer;
			//Save transfer somehow
			return balance;
		}
		else {
		//Add notification that transaction wasn't completed
		return balance;
		}
	}
}

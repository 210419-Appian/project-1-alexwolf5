package com.piggybank.services;

import java.util.ArrayList;
import java.util.List;

import com.piggybank.models.Accounts;
import com.piggybank.models.Users;

public class Credentials {
	
	public static List<Users> getUserDetails() {
		Users user =  new Users();
		List<Users> list = new ArrayList<>();
		list.add(user);
		return list;
	}
	
	public List<Accounts> getAccountDetails() {
		Accounts account = new Accounts();
		List<Accounts> list = new ArrayList<>();
		list.add(account);
		return list;
	}
	
}

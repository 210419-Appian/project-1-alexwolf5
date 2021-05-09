package com.piggybank.daos;

import com.piggybank.daos.UsersDAO;

public interface UsersDAO {

	public void register(String username, String password, String firstname, String lastname, String email);
	public int loggingIn(String username, String password);
	public void newUsername (int userId, String username);
	public void newPassword (int userId, String password);
	public void newFirstname (int userId, String firstname);
	public void newLastname (int userId, String lastname);
	public void newEmail (int userId, String email);

}

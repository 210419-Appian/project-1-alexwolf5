package com.piggybank.daos;

import com.piggybank.daos.UsersDAO;
import com.piggybank.models.Users;

public interface UsersDAO {

	public void register(String username, String password, String firstname, String lastname, String email);
	public int loggingIn(String username, String password);
	public void newDetails (int userId, String username, String password, String firstname, String lastname, String email, String role);
	public int checkUsernameAvailability(String username);
	public Users findByUsername(String username);
	public String getRole(int userId);
	
}

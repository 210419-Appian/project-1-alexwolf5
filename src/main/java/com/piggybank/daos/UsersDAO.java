package com.piggybank.daos;

import com.piggybank.daos.UsersDAO;

public interface UsersDAO {

	public void register(String username, String password, String firstname, String lastname, String email);
	public int loggingIn(String username, String password);

}

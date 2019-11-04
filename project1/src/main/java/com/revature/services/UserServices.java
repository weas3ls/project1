package com.revature.services;

import com.revature.dao.UserDao;
import com.revature.models.User;

public class UserServices {
	// Services that the UserController object has access to
	UserDao userDao = new UserDao();
	
	/*
	 * REQUIRED: String, String
	 * MODIFIES: None
	 * EFFECTS: Returns true if the user login credentials have passed constraints. Returns false otherwise.
	 */
	public boolean userLogin(String username, String password) {
		// Creates a user by calling the getUserByCredentials method in UserDao. If credentials are invalid, then returns null
		User user = userDao.getUserByCredentials(username, password);
		if (user != null) { // Checks to make sure that user credentials are correct. If so, then user should reference some User object
			System.out.println("Login success. Welcome, " + user.getFirstName() + " " + user.getLastName());
			return true;
		} else {
			System.out.println("Login failed. Check username or password.");
			return false;
		}
	}
	
	
}

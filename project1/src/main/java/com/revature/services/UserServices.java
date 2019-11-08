package com.revature.services;

import com.revature.dao.UserDao;
import com.revature.models.User;

public class UserServices {
    // Services that the UserController object has access to
    UserDao userDao = new UserDao();

    /*
     * REQUIRED: String, String MODIFIES: None EFFECTS: Returns true if the user
     * login credentials have passed constraints. Returns false otherwise.
     */
    public User userLogin(String email, String password) {
        // Creates a user by calling the getUserByCredentials method in UserDao. If
        // credentials are invalid, then returns null
        System.out.println("Email in userLogin call: " + email);
        User user = userDao.getUserByCredentials(email, password);
        if (user != null) { // Checks to make sure that user credentials are correct. If so, then user
                            // should reference some User object
            System.out.println("Login success. Welcome, " + user.getFirstName() + " " + user.getLastName());
            return user;
        } else {
            System.out.println("Login failed. Check email or password.");
            return null;
        }
    }

    public static User save(User user) {
        return null;
    }

}

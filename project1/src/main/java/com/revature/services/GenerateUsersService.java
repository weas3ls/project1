package com.revature.services;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.models.User;
import com.revature.passwordhash.PasswordHashing;

public class GenerateUsersService {
    static UserDao userDao = new UserDao();

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // File file = new File("E:\\Revature\\Training\\Projects\\Project01\\MOCK_DATA.json");
        File file = new File("C:\\Users\\weas3ls\\Documents\\revature_training\\project1\\MOCK_DATA.json");

        User[] users;

        users = objectMapper.readValue(file, User[].class);
        for (User user : users) {
            Optional<String> passwordSalt = PasswordHashing.generateSalt(512);
            user.setPasswordSalt(passwordSalt.get());
            Optional<String> password = PasswordHashing.hashPassword(user.getPassword().toCharArray(),
                    passwordSalt.get());
            user.setPassword(password.get());
            if (user.getRoleId() != 1) {
                user.setRoleId(2);
            }
            userDao.registerUser(user);
        }

        // Create a new user using the UserDao's registerUser method
        User newUser = new User("manager3", "password111", "Yue", "Tan", "ytan@princeton.edu", 1);
        userDao.registerUser(newUser);

        testLogin("manager2", "password124");
        System.out.println("Logging into manager3..");
        testLogin("manager3", "password111");
    }

    // REQUIRED: valid user reference with username and password matching
    // MOCK_DATA.json
    // MODIFIES: None
    // EFFECTS: Tests the getUserByCredentials method in userDao to make sure that
    // login feature works as intended.
    public static void testLogin(String username, String password) {
        System.out.println("Testing user login");

        User certifiedUser = userDao.getUserByCredentials(username, password);

        // Checks to make sure certifiedUser is not null. If not, then it means that
        // certifiedUser has passed test assuming that user
        // has right credentials.
        if (certifiedUser != null) {
            System.out.println("Credentials confirmed! Welcome, " + certifiedUser.getUsername());
        } else {
            System.out.println("Login failed. Check username or password.");
        }
    }
}
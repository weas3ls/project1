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

        // File file = new File("E:\\Revature\\Training\\Projects\\Project
        // 01\\MOCK_DATA.json");
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
    }
}
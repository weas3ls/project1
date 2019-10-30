package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import com.revature.models.User;
import com.revature.util.*;

public class UserDao {
	/*
	 * Required: None
	 * Modifies: None
	 * Effects: Returns a list of type User by converting all entries 
	 * 
	 */
	public static List<User> getAllUsers() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			List<User> users = new ArrayList<>();
			String sql = "SELECT * FROM ers_users";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				users.add(extractUser(rs));
			}
			
			return users;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException in UserDao.getAllUsers()");
			return null;
		}
		
		
	}
	
	/*
	 * REQUIRED: valid ResultSet
	 * MODIFIES: None
	 * EFFECTS: Extracts from result set state in ers_users table and returns a User object with same state
	 * 
	 */
	private static User extractUser(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("ers_users_id");
		String username = rs.getString("ers_username");
		String password = rs.getString("ers_password");
		String passwordSalt = rs.getString("password_salt");
		String firstName = rs.getString("user_first_name");
		String lastName = rs.getString("user_last_name");
		String email = rs.getString("user_email");
		Integer roleId = rs.getInt("user_role_id");

		User user = new User(id, username, password, passwordSalt, firstName, lastName, email, roleId);
		return user;
	}
}

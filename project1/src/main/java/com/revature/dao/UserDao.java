package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import com.revature.models.User;
import com.revature.passwordhash.PasswordHashing;
import com.revature.util.*;

public class UserDao {
	Connection conn;
	
	
	// Creates a connection to the database.
	public void setConnection(Connection conn) {
		try {
			if (this.conn != null && !this.conn.isClosed()) {
				System.out.println("Closing connection");
				this.conn.close();
			}
			this.conn = conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Establishes connection to database upon instantiation of UserDao object.
	public UserDao() {
		this.conn = ConnectionUtil.getConnection();
	}
	
	/*
	 * Required: None
	 * Modifies: None
	 * Effects: Returns a list of type User by converting all entries 
	 * 
	 */
	public List<User> getAllUsers() {
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
	 * 
	 * REQUIRED: valid User reference
	 * Modifies: user
	 * Effects: Generates salt to user given password and sets the passwordSalt field in user to the generated password salt.
	 */
	public void generateSalt(User user) {
		Optional<String> salt = PasswordHashing.generateSalt(user.getPassword().length());
		String saltPassword = salt.get();
		user.setPasswordSalt(saltPassword);
		
	}
	
	/*
	 * 
	 * REQUIRED: Valid User reference with passwordSalt state
	 * MODIFIES: None
	 * EFFECTS: Hashes user password and sends it to the database
	 * 
	 */
	public void insertHashPassword(final User user) {
		 try (Connection conn = ConnectionUtil.getConnection()) {
			 String passwordSalt = new String(user.getPasswordSalt());
			 Optional<String> hashedPassword = PasswordHashing.hashPassword(user.getPassword().toCharArray(), passwordSalt);
			 String sql = "INSERT INTO ers_users (ers_password) VALUES (?) WHERE id = ?;";
			 PreparedStatement statement = conn.prepareStatement(sql);
			 statement.setString(1, hashedPassword.get());
			 statement.setInt(2, user.getId());
			 statement.executeUpdate();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQLException in UserDao.hashPassword");
			return;
		}
		 
	}
	
	
	/*
	 * 
	 * Required: String, String
	 * Modifies: None.
	 * Effects: Finds user in database by username and password. If results are found, then returns a new User object. Otherwise, returns null.
	 * 
	 */
	public User getUserByCredentials(String username, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			// Selects from ers_user table to receive all information from user with specified username
			String sql = "SELECT * FROM ers_users WHERE ers_username = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			
			/* Creates a salt by using the password given in function parameter and passes it in the verifyPassword method in PasswordHashing
			to verify that the hashed password matches the password field in the query. */
			
			Optional<String> salt = PasswordHashing.generateSalt(password.length());
			String saltPassword = salt.get();

			// If the hashed password is equal to the password in the query, then returns user object. Otherwise, returns null pointer reference.
			if (rs.next() && PasswordHashing.verifyPassword(password.toCharArray(), rs.getString("ers_password"), saltPassword)) {
				User user = extractUser(rs);
				return user;
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException found in UserDao.getUserCredentials");
			return null;
		}
	}
	
	
	/*
	 * REQUIRED: valid ResultSet
	 * MODIFIES: None
	 * EFFECTS: Extracts from result set state in ers_users table and returns a User object with same state
	 * 
	 */
	private User extractUser(ResultSet rs) throws SQLException {
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
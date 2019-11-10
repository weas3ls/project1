package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.passwordhash.PasswordHashing;
import com.revature.util.ConnectionUtil;

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
     * Required: None Modifies: None Effects: Returns a list of type User by
     * converting all entries
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
     * Required: String, String Modifies: None. Effects: Finds user in database by
     * username and password. If results are found, then returns a new User object.
     * Otherwise, returns null.
     * 
     */
    public User getUserByCredentials(String email, String password) {
        try (Connection conn = ConnectionUtil.getConnection()) {

            // Selects from ers_user table to receive all information from user with
            // specified username
            String sql = "SELECT * FROM ers_users WHERE user_email = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            // If the hashed password is equal to the password in the query, then returns
            // user object. Otherwise, returns null pointer reference.
            if (rs.next() && PasswordHashing.verifyPassword(password.toCharArray(), rs.getString("ers_password"),
                    rs.getString("password_salt"))) {
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
     * REQUIRED: Valid user reference MODIFIES: User EFFECTS: Register users from
     * generated data (JSON) JSON Path: E:\Revature\Training\Projects\Project
     * 01\MOCK_DATA.json
     */

    public void registerUser(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO ers_users (ers_username, ers_password, password_salt, user_first_name, user_last_name, user_email, user_role_id)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING ers_users_id;";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPasswordSalt());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getEmail());
            statement.setInt(7, user.getRoleId());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("ers_users_id"));
            }

            System.out.println(user.getUsername() + " has been registered.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException in registerUsers method in UserDao");
        }
    }

    /*
     * REQUIRED: valid ResultSet MODIFIES: None EFFECTS: Extracts from result set
     * state in ers_users table and returns a User object with same state
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

        User user = new User(username, password, firstName, lastName, email, roleId);
        user.setId(id);
        user.setPasswordSalt(passwordSalt);
        return user;
    }

	public User getUserById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_users WHERE ers_users_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				User user = extractUser(rs);
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


}

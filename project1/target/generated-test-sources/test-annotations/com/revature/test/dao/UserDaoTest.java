package com.revature.test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.postgresql.core.BaseStatement;

import com.revature.dao.UserDao;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
	
	private static UserDao userDao = new UserDao();

	// To be spied upon
	private static Connection conn;
	private static BaseStatement stmt;

	public UserDaoTest() throws SQLException {
		super();
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Create connection object
		Connection connection = ConnectionUtil.getConnection();
		
		// Set schema to testing so that all operations
		// happen on the testing schema
		connection.setSchema("testing");
		
		// Copy table that will be tested from public to
		// testing with no data
		connection.createStatement()
			.executeUpdate("CREATE TABLE if not exists testing.ers_user AS TABLE public.ers_users"
					+ " WITH NO DATA");
			
		// Insert a single record for testing purposes
		connection.createStatement()
			.executeUpdate("INSERT INTO testing.ers_users "
					+ "(ers_users_id, ers_username, ers_password, password_salt, user_first_name, user_last_name, user_email, user_role_id) VALUES (1, 'Test', 'Pass', 123, 'test_first', 'test_last', 'test_email', 1)");
		
		
		// Create spy objects watching Statement and Connection
		Statement statement = Mockito.spy(connection.createStatement());
		stmt = (BaseStatement) statement;
		conn = Mockito.spy(connection);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Drop created table after tests complete
		ConnectionUtil
			.getConnection()
			.createStatement()
			.executeUpdate("drop TABLE testing.ers_users");
		
		ConnectionUtil.testMode = false;
	}
	
	@Before
	public void setUp() throws SQLException {
		Mockito.reset(conn);
		Mockito.reset(stmt);
		userDao.setConnection(conn);
		Mockito.when(conn.createStatement()).thenReturn(stmt);
		
	}
	
	@Test
	public void test_getAllUsers() throws SQLException {
		List<User> users = userDao.getAllUsers();
		Mockito.verify(stmt).executeQuery(Mockito.anyString());
		
	}
	
	

}

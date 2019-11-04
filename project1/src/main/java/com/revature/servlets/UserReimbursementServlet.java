package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.*;
import com.revature.models.*;

public class UserReimbursementServlet extends HttpServlet {
	
	UserDao userDao = new UserDao();
	ReimbursementDao reimbursementDao = new ReimbursementDao();
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	
	// 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
}

package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.*;
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

//    UserDao userDao = new UserDao();
//    ReimbursementDao reimbursementDao = new ReimbursementDao();
    UserServices userServices = new UserServices();
    ReimbursementService reimbursementServices = new ReimbursementService();

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void destroy() {

    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = om.readValue(req.getReader(), User.class);
        User savedUser = UserService.save(user);
        om.writeValue(resp.getWriter(), savedUser);

    	// First generates string of location in request
    	String info = req.getPathInfo();
    	
    	if (info == null) {
    		resp.setStatus(400);
    		return;
    	}
    	
    	String[] parts = info.split("/");
    	
    	if (parts.length <= 0) {
    		resp.setStatus(400);
    		return;
    	}
    	
    	if (parts[1] == "login") {
    		
    	}
    }


}

package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserServices;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ObjectMapper om = new ObjectMapper();
    UserServices userServices = new UserServices();
    

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// Clears the id from userid context param
    	this.getServletContext().setInitParameter("userid", "");
    	System.out.println("Servlet Context Param Value from LoginServlet Service Call: " + this.getServletContext().getInitParameter("userid"));

        // Add CORS headers
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");

        super.service(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = om.readValue(req.getReader(), User.class);

        User userLoggedIn = userServices.userLogin(user.getEmail(), user.getPassword());

        if (userLoggedIn != null) {
            // Sets password and password salt to empty string to prevent server data from
            // leaking into the client side.
            // Moved into conditional statement to prevent nullPointerException
            userLoggedIn.setPassword("");
            userLoggedIn.setPasswordSalt("");

            resp.setStatus(200);
            om.writeValue(resp.getWriter(), userLoggedIn);
//            this.getServletContext().setInitParameter("userid", String.valueOf(userLoggedIn.getId()));
//            System.out.println("Context Param Value: " + this.getServletContext().getInitParameter("userid"));
            // Forwards the servlet to the profile page. Profile should pickup the user ID from the req object passed in this call, which
            // then its init method will set the context-param, thus giving us access to the user id until logout is called
            req.getRequestDispatcher("/s").forward(req, resp);
        } else {
            resp.setStatus(401);
            resp.getWriter().write("Log in failed!");
        }
    }
}

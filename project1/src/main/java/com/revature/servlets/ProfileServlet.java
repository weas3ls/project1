package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ObjectMapper om = new ObjectMapper();
    ReimbursementService reimbursementServices = new ReimbursementService();

    @Override
    public void init() throws ServletException {
        System.out.println("Init profileservlet method call");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");

        super.service(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String info = req.getPathInfo();

        String[] parts = info.split("/");

        System.out.println("Info in ProfileServlet: " + parts[1]);

        // if (parts[1] != session.getAttribute("userid")) {
        // resp.setStatus(403);
        // resp.getWriter().write("Do not have permission to access this profile");
        // return;
        // }

        // if (session == null) {
        // System.out.println("param not read");
        // resp.setStatus(400);
        // return;
        // }

        // System.out.println(session.getAttribute("userid"));

        // System.out.print("Param passed into profileservlet: ");
        // Integer userId = Integer.valueOf((String)
        // session.getAttribute("userid").toString());
        // System.out.println(userId);

        List<Reimbursement> userReimbursements = reimbursementServices.getUserTickets(Integer.parseInt(parts[1]));
        // System.out.println(userReimbursements.size());
        // System.out.println(userReimbursements);

        session.setAttribute("userid", parts[1]);
        om.writeValue(resp.getWriter(), userReimbursements);
        resp.setStatus(200);

    }

}

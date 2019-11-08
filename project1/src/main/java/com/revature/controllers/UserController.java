package com.revature.controllers;

import com.revature.services.UserServices;

public class UserController {
	private String command;
	private UserServices userServices = new UserServices();
	
	public UserController(String command) {
		this.command = command;
	}
	
	public void setControl(String control) {
		this.command = control;
	}
	
	public void executeControl() {
		switch (this.command) {
		case "login":
			userServices.userLogin(username, password);
			break;

		default:
			break;
		}
	}
}

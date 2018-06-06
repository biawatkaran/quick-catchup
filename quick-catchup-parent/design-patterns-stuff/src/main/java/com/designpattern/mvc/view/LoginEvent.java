package com.designpattern.mvc.view;

public class LoginEvent {

	private String user;

	public LoginEvent(String user) {
		super();
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	
}
package com.designpattern.mvc.controller;

import com.designpattern.mvc.view.LoginEvent;
import com.designpattern.mvc.view.LoginListener;

public class Controller implements LoginListener{

	
	@Override
	public void loginPerformed(LoginEvent event) {
		System.out.println("Logged User :" + event.getUser());
	}

}

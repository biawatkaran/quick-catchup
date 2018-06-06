package com.designpattern.mvc.application;

import javax.swing.SwingUtilities;

import com.designpattern.mvc.controller.Controller;
import com.designpattern.mvc.model.Model;
import com.designpattern.mvc.view.View;

/*
 * A) MVC, Observer pattern [data from view should get notified to controller]
 * 		1. create interface that will be implemented by listeners and should be implemented by client who is interested to listen (Controller)
 * 		2. addListener, on subject add listener first imagine as button who needs listener to handle (e.g. View)
 * 		3. when anything happens on subject then that will fire an event so listener get notified
 * 		3. iterate over listeners and all the listeners will get notifications (Controller in our case)
 * 		4. to provide extra information to client listeners, create and Event object e.g. LoginEvent that can be passed to listener methods
 */
public class MainApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runMainApp();
			}

		});
	}

	@SuppressWarnings("unused")
	protected static void runMainApp() {
		Model model = new Model();
		View view = new View();		
		Controller controller = new Controller();

		view.addListener(controller);
	}

}

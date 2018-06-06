package com.designpattern.mvc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<LoginListener> listeners = new ArrayList<LoginListener>();
	private JButton helloButton;

	public View() {
		helloButton = new JButton("Click Me!");
		helloButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireEvent(e);
			}
		});

		add(helloButton);

		setSize(500, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	protected void fireEvent(ActionEvent event) {
		String text = ((JButton) event.getSource()).getText();
		for (LoginListener listener : listeners) {
			listener.loginPerformed(new LoginEvent(text));
		}
	}

	public void addListener(LoginListener listener) {
		listeners.add(listener);
	}
}

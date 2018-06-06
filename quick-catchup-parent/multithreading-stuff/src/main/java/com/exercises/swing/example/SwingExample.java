package com.exercises.swing.example;

import javax.swing.SwingUtilities;

public class SwingExample {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SwingWorkerDemo();
			}
		});
	}
}
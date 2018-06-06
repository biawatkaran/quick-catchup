package com.exercises.swing.example;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class SwingWorkerDemo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel count = new JLabel("0");
	private JLabel statusLabel = new JLabel("Task not completed");
	private JButton start = new JButton("Start");

	public SwingWorkerDemo() {
		super("Swing Worker Demo!");
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		add(count, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		add(statusLabel, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		add(start, gc);

		setSize(200, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
	}

	public void start() {

		SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {

			@Override
			protected Boolean doInBackground() throws Exception {

				for (int i = 0; i < 30; i++) {
					Thread.sleep(100);
					System.out.println("Hello " + i);

					publish(i);
				}
				return true;
			}

			@Override
			protected void process(List<Integer> chunks) {
				Integer value = chunks.get(chunks.size() - 1);
				count.setText("current value :"+ value);
			}

			@Override
			protected void done() {
				try {
					Boolean status = get();
					statusLabel.setText("Completed with status : " + status);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}

		};

		worker.execute();
	}
}
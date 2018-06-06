package com.exercises.locks.main;

import com.exercises.locks.DeadLockExample;
import com.exercises.locks.ReentrantLockExample;

public class MainRunner {

	public static void main(String[] args) throws InterruptedException {

	    //tryReetrantLockExample();
		tryDeadLockExample();

	}

	@SuppressWarnings("unused")
	private static void tryReetrantLockExample() throws InterruptedException {

		System.out.println("Running Reentrant Lock");

		final ReentrantLockExample rle = new ReentrantLockExample();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					rle.thread1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, "FirstThread");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					rle.thread2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, "SecondThread");

		t1.start();
		t2.start();

		// wait for two to finish
		t2.join();
		t1.join();

		System.out.println("Counter value is: " + rle.getCounter());
	}

	private static void tryDeadLockExample() throws InterruptedException {
		System.out.println("Running Deadlock Lock");

		final DeadLockExample dle = new DeadLockExample();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					dle.firstThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, "FirstThread");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					dle.secondThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, "SecondThread");

		t1.start();
		t2.start();

		// wait for two to finish
		t1.join();
		t2.join();

		dle.finish();
	}
}

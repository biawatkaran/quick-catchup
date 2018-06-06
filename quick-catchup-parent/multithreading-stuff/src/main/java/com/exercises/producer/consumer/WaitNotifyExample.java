package com.exercises.producer.consumer;

import java.util.LinkedList;

/**
 * 1. wait method will stop, it won't print the next line. It simply handles the lock to someone to resume execution
 * 2. notify method will finish it's execution, i.e. after notify if any print statement then it will print first in fact
 *    finish the synchronized block then handles to other thread so that can be resumed where for previous thread the next
 *    line was waiting to get printed
 */
public class WaitNotifyExample {
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private Object lock = new Object();
	private final int LIMIT = 10;

	public static void main(String[] args) {
		final WaitNotifyExample mainObject = new WaitNotifyExample();
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					mainObject.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				;
			}

		}, "Producer");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					mainObject.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				;
			}

		}, "Consumer");

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void producer() throws InterruptedException {
		int value = 0;

		while (true) {
			synchronized (lock) {
				
				while (list.size() == LIMIT) {
					System.out.println("waiting...");
					lock.wait();
					System.out.println("resumed");
				}
				
				list.add(value);
				lock.notify();
            }
		}
	}

	public void consumer() throws InterruptedException {
		
		while (true) {
			synchronized (lock) {
				
				while (list.size() == 0) {
					lock.wait();
				}
			
				System.out.println("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("value taken is: " + value);
				lock.notify();
            }
            Thread.sleep(100);
		}
	}
}

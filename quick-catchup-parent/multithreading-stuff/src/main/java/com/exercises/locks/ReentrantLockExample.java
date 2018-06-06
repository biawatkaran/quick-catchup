package com.exercises.locks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 1. Use it when synchronised keyword not good enough e.g. waiting time to put into lock
 * 2. synchronised are structured and other not i.e. it has to be lock on same object
 * 3. reentrant locks are in favour to longest-waiting thread but other not
 * 4. ability to check lock held, using tryLock()
 * 
 *  Bad thing - need to use try/finally but always done to avoid exception situations
 *  
 *  Usage: traversing list, lock next node and unlock first one 
 */
public class ReentrantLockExample {

	private int counter;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();

	private void increment() {
		for (int i = 0; i < 10000; i++) {
			counter++;
		}
	}

	public void thread1() throws InterruptedException {

		lock.lock();
		System.out.println("waiting...");
		cond.await();
		System.out.println("resumed...");
		try {
			increment();
		} finally {
			lock.unlock();
		}

	}

	@SuppressWarnings("resource")
	public void thread2() throws InterruptedException {

		Thread.sleep(1000);
		lock.lock();

		System.out.println("Press enter to resume other thread!");
		new Scanner(System.in).nextLine();
		System.out.println("Enter key recieved!");

		cond.signal();

		try {
			increment();
		} finally {
			lock.unlock();
		}
		System.out.println("thread2 done");
	}

	public int getCounter() {
		return counter;
	}
}

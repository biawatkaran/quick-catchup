package com.exercises.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.exercises.locks.dao.Account;

/*
 * 1. use synchronized block
 * 2. use reentrant locks 
 *             both solve by using locks in same order in both threads ( as commented out code below)
 * 3. in order to avoid the ordering, you can use below example passing locks to acquireLocks(lock1, lock2) in any order
 */

public class DeadLockExample {

	Account act1 = new Account();
	Account act2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private void acquireLocks(Lock lock1, Lock lock2) throws InterruptedException {
		while(true){
			boolean gotfirstLock = false;
			boolean gotsecondLock = false;
			
			try{
				gotfirstLock = lock1.tryLock();
				gotsecondLock = lock2.tryLock();
			} finally{
				if(gotfirstLock && gotsecondLock){
					return;
				}
				
				if(gotfirstLock){
					lock1.unlock();
				}
				
				if(gotsecondLock){
					lock2.unlock();
				}
			}
			
			Thread.sleep(1);
		}
	}
	
	private void releaseLocks(){
		lock1.unlock();
		lock2.unlock();
	}

	public void firstThread() throws InterruptedException {

		for (int i = 0; i < 10000; i++) {
			acquireLocks(this.lock1,this.lock2);
				
			try {
				Account.transfer(act1, act2, 200);
			} finally {
				releaseLocks();
			}
		}
	}

	public void secondThread() throws InterruptedException {

		for (int i = 0; i < 10000; i++) {
			acquireLocks(this.lock2, this.lock1);
			try {
				Account.transfer(act2, act1, 200);
			} finally {
				releaseLocks();
			}
		}
	}
	
	
/*	
 * First Method
 * public void firstThread() throws InterruptedException {

		for (int i = 0; i < 10000; i++) {
			lock1.lock();
			lock2.lock();

			try {
				Account.transfer(act1, act2, 200);
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void secondThread() throws InterruptedException {

		for (int i = 0; i < 10000; i++) {
			lock1.lock();
			lock2.lock();

			try {
				Account.transfer(act2, act1, 200);
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
*/

	public void finish() {
		System.out.println("Account1 balance: " + act1.getBalance());
		System.out.println("Account2 balance: " + act2.getBalance());
		System.out.println("Total balance " + (act1.getBalance() + act2.getBalance()));
	}
}

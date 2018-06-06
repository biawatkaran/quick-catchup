package com.exercises.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.exercises.locks.dao.Connection;

/*
 * semaphore with count 1 acts same as lock.
 * acquire, holds the permit and waits in case none available
 * release, releases the permit to use by some other
 * 
 * Its used in conjunction with lock/synchornized
 * Advantages:
 * 1. semaphore can be released by any other thread, but not the case with lock
 */

public class SemaphoreExample {

	public static void main(String[] args) throws Exception {

/*		sem.acquire(); // will wait here in case PERMITS = 0
		sem.release();
		System.out.println("available permits :" + sem.availablePermits());*/

		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			executor.submit(new Runnable() {

				@Override
				public void run() {
					try {
						Connection.getConnectionInstance().connect();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			});
		}
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
	}
}
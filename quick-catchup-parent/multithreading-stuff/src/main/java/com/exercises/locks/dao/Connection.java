package com.exercises.locks.dao;

import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection connection = new Connection();
	private Semaphore sem = new Semaphore(10);
	
	private int connections;
	private Connection(){
		
	}
	
	public static Connection getConnectionInstance(){
		return connection;
	}

/*	this creates connections as many times requested
 * public void connect(){
		synchronized(this){
			connections++;
			System.out.println("Connections created :"+ connections);
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (this) {
			connections--;
		}
		
	}*/
	
	public void connect() throws InterruptedException {

		sem.acquire();

		try {
			doConnect();
		} finally {
			sem.release();
		}

	}

	public void doConnect() throws InterruptedException {

		synchronized (this) {
			connections++;
			System.out.println("Connections created :" + connections);
		}

		Thread.sleep(1000);

		synchronized (this) {
			connections--;
		}
	}
}
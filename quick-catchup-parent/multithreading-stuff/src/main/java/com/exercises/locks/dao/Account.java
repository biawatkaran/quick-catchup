package com.exercises.locks.dao;

public class Account {

	private int balance = 1000;
	
	public void withdraw(int amount){
		balance -= amount;
	}
	
	public void deposit(int amount){
		balance +=amount;
	}
	
	public static void transfer(Account act1, Account act2, int amount){
		act1.withdraw(amount);
		act2.deposit(amount);
	}
	
	public int getBalance(){
		return this.balance;
	}
}

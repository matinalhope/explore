package com.matianl.explore.java7concurrency.Chapter6.ch6_recipe07.src.com.packtpub.java7.concurrency.chapter6.recipe08.task;

/**
 * This class simulates a company that pays a salary an
 * insert money into an account 
 *
 */
public class Company implements Runnable {

	/**
	 * The account affected by the operations
	 */
	private Account account;
	
	/**
	 * Constructor of the class. Initializes the account
	 * @param account the account affected by the operations
	 */
	public Company(Account account) {
		this.account=account;
	}
	
	/**
	 * Core method of the Runnable
	 */
	@Override
	public void run() {
		for (int i=0; i<10; i++){
			account.addAmount(1000);
		}
	}

}

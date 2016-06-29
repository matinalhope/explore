package com.matianl.explore.java7concurrency.Chapter7.ch7_recipe03.src.com.packtpub.java7.concurrency.chapter7.recipe03.task;

import java.util.concurrent.TimeUnit;


/**
 * Task to be executed in the MyThread threads
 *
 */
public class MyTask implements Runnable {

	/**
	 * Main method of the Thread. Sleeps the thread during two seconds
	 */
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

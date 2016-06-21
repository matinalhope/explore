package com.matianl.explore.java7concurrency.appendix.recipe10.core;

import java.util.concurrent.locks.ReentrantLock;

import com.matianl.explore.java7concurrency.appendix.recipe10.task.Task;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReentrantLock lock=new ReentrantLock();
		for (int i=0; i<10; i++) {
			Task task=new Task(lock);
			Thread thread=new Thread(task);
			thread.start();
		}
	}

}

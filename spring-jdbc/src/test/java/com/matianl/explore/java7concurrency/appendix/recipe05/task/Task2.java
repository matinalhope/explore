package com.matianl.explore.java7concurrency.appendix.recipe05.task;

import java.util.concurrent.locks.Lock;

import com.matianl.explore.java7concurrency.appendix.recipe05.utils.Operations;

public class Task2 implements Runnable {

	private Lock lock;
	
	public Task2 (Lock lock) {
		this.lock=lock;
	}
	
	@Override
	public void run() {
		lock.lock();
		Operations.readData();
		lock.unlock();
		Operations.processData();
		lock.lock();
		Operations.writeData();
		lock.unlock();
	}
}

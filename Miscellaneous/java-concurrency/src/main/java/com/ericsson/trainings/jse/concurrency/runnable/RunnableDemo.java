package com.ericsson.trainings.jse.concurrency.runnable;

public class RunnableDemo {
	public static void main(String[] args) {
		Runnable taskRunner = new TaskRunner();
		new Thread(taskRunner, "TaskRunner").start();
	}
}

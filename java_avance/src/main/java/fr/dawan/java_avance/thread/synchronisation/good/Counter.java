package fr.dawan.java_avance.thread.synchronisation.good;

public class Counter {
	private volatile int count;
	
	public synchronized void increment() {
		count++;
	}

	public int getCount() {
		return count;
	}
	
}

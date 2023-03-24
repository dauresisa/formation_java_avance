package fr.dawan.java_avance.thread.synchronisation.good;

public class ThreadCounter implements Runnable {

	
	private Counter counter;
	
	
	@Override
	public void run() {
		int[] array = new int[100000];
		for (int i = 0; i < array.length; i++) {
			counter.increment();
		}
		
	}

	
public ThreadCounter() {
	// TODO Auto-generated constructor stub
}


public ThreadCounter(Counter counter) {
	super();
	this.counter = counter;
}


}

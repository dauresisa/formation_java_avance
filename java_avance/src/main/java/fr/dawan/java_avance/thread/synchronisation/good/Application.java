package fr.dawan.java_avance.thread.synchronisation.good;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		
		
		Counter counter = new Counter();
		ThreadCounter threadCounter = new ThreadCounter(counter);
		
		Thread t1 = new Thread(threadCounter);
		Thread t2 = new Thread(threadCounter);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("Count : "+counter.getCount());

	}

}

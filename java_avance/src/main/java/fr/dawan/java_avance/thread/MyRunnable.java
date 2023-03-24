package fr.dawan.java_avance.thread;

public class MyRunnable implements Runnable {
	//un attribut partagé par tous les Threads
	private String dataThread;
	

	public MyRunnable() {
		// TODO Auto-generated constructor stub
	}

	public MyRunnable(String dataThread) {
		super();
		this.dataThread = dataThread;
	}



	@Override
	public void run() {
			int[] array = new int[10];
		
		for (int i = 0; i < array.length; i++) {
			System.out.println("Runnable = "+Thread.currentThread().getId()+" "+this.dataThread);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}

	}
	

}

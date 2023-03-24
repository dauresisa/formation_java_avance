package fr.dawan.java_avance.thread;

public class MyThread extends Thread{
	
	//Donnée propre à chaque thread;
	private String dataThread;

	public MyThread(String dataThread) {
		super();
		this.dataThread = dataThread;
	}

	public MyThread() {
		super();
	}

	@Override
	public void run() {
		int[] array = new int[20];
		
		for (int i = 0; i < array.length; i++) {
			System.out.println("Thread "+ getName()+" = "+this.dataThread);
			try {
				//mise en sommeil du thread pour une durée déterminée
				sleep(200);
				//Elle est bloquante, elle lève une exception de type InterruptedException au cours de son exécution 
				//si un autre thread demande l'interruption de l'exécution du thread.
			} catch (InterruptedException e) {
				//e.printStackTrace();
				System.out.println("Le thread "+Thread.currentThread().getName()+" est interrompu");
				System.out.println("Thread.isInterrupted = "+Thread.currentThread().isInterrupted());
			}
		}
	}
	
	
	

}

package fr.dawan.java_avance.thread;

public class MythreadDaemon implements Runnable {

	@Override
	public void run() {
		
		while(true) {
			System.out.println("Execution du thread daemon");
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

	}

}

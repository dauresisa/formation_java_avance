package fr.dawan.java_avance.thread;

public class Application {

	public static void main(String[] args) {
		// **********1- classe java.lang.Thread ****************
		
		//création des threads
		MyThread th1 = new MyThread("java");
		MyThread th2 = new MyThread("Spring");
		
		th1.setName("T1");
		th2.setName("T2");

		//opération en parallèle
		th1.start();
		th2.start();
		
		//opération en séquentiel
		th1.run();
		th2.run();
		
		
		//******* 2- Interface Runnable **********
		
//		MyRunnable myRunnable = new MyRunnable("webService");
//		Thread t1 = new Thread(myRunnable);
//		Thread t2 = new Thread(myRunnable);

//		t1.start();
//		t2.start();
		
		//***** 3- cycle de vie d'un Thread ********
//		th1.interrupt();
//		System.out.println(th1.isInterrupted());
		
		
//		//***************4- méthode join *********************
//		try {
//			//le thread main va attendre que th1 et th2 ai fini
//			th1.join();
//			th2.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		

		
		
		/*
		 * pour préciser qu'un thread est un daemon, il faut mettre à true sa méthode setDaemon
		 * cette méthode doit être invoquée avant que le thread ne soit démarré
		 * Généralement les traitements d'un daemon s'exécute indéfiniment et il ne n'est jamais interrompus
		 * c'est l'arrêt de la JVM qui provoque sa fin
		 * 
		 * Lorsque tous les threads non daemon sont terminés , la jvm s'arrête et tous les daemons restants sont arrêtés
		 */
		
		MythreadDaemon daemon = new MythreadDaemon();
		Thread t3 = new Thread(daemon);
		t3.setDaemon(true);
		System.out.println(t3.isDaemon());
		t3.start();
		
		
		System.out.println(Thread.currentThread().getName());
	}

}

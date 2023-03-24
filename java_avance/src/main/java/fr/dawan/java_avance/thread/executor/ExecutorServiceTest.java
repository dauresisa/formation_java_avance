package fr.dawan.java_avance.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {
	
	/**
	 * Processus : programme en execution qui contiendra un thread
	 * Un thread : unité physique d'execution d'une tache dans un processus
	 * On peut avoir plusieurs thread dans un processus
	 * 
	 * tache : traitement que réalisera le thread
	 */
	
	public static void main(String[] args) {
		
//	//séquentiel :
//	job1();
//	job2();
		
		//multi-threading : l'objectif est de libérer le thread main
		//pas toujours synonyme de performance
		
		//new Thread(()->job1()).start();
		//new Thread(()->job2()).start();
		
		/*chaque tache ou sous-tache donne lieu à un thread donc non maitrise de l'ordre d'exécution
		on doit créer autant de thread que de traitements
		le coùt de création d'un thread est supérieur au coût d'exécution
		donc création de bcp de thread peut ralentir ou bloquer le programme
		
		donc solution : Thread Pool(réservoir)
		on va préinstancier un réservoir de thread, avantage limiter le délai d'éxécution
		 et gestion de la liste des taches
		
		2 méthode de l'interface ExecutorService permettant de créer différents type de pools de thread
		newScheduledThreadPool et newSingleThreadExecutor*/
		
		//on va créer un réservoir de thread
		
		// 1 - méthode
		ExecutorService executorService = Executors.newFixedThreadPool(10);//plusieurs thread en meme temp
		
		
		// 2- méthode
		//ExecutorService executorService = Executors.newSingleThreadExecutor();//un seul thread qui execute les 2 jobs
		
		executorService.execute(() -> job1());
		executorService.execute(() -> job2());
		
		
		
		/**
		 * ScheduledExecutorService créé un pool de threads de taille 2
		 * La méthode schedulAtFixedRate est ensuite appelée pour planifier
		 * l'exécution de la tache task après un délai initial de 3 secondes, puis répété toutes les 2 secondes
		 */
		
		ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
		service.scheduleAtFixedRate(()->{System.out.println("beep");}, 3, 2, TimeUnit.SECONDS);
		
		service.scheduleWithFixedDelay(()->{System.out.println("hey");}, 0, 1, TimeUnit.SECONDS);
		
		
		
		executorService.shutdown();
		
	}
	
	public static void job1() {
		for (int i = 0; i < 100; i++) {
			System.out.println("job 1 : "+i+" "+Thread.currentThread().getId());
		}
	}
	
	public static void job2() {
		for (int i = 0; i < 100; i++) {
			System.out.println("job 2 : "+i+" "+Thread.currentThread().getId());
		}
	}
	
	
	

	
}

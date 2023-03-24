package fr.dawan.java_avance.thread.executor.runnable_vs_callable_and_future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * La classe Future
 * @author Admin
 *
 */
public class App2 {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		//implémentation
		Callable<String> callable = () -> {
			Thread.sleep(5000);//permet de simuler une tache longue à éxectuer
			System.out.println("Running un tache longue");
			return "Callable task complete";
		};
 
		
		//appel
		Future<String> future = executorService.submit(callable);
		
		while (!future.isDone()) {
			System.out.println("la tache n'est pas terminée");
			try {
				Thread.sleep(1000);//attendre 1 seconde avant de vérifier à nouveau dans le while
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		String result = "";
		try {
			result= future.get();//bloquant jusqu'à ce que la tache soit terminée
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("Tache resultat : "+result);
		executorService.shutdown();
		
        /*
         * Dans cet exemple, nous avons soumis une tâche Callable à un ExecutorService et stocké l'objet Future 
         * qui est renvoyé par la méthode submit(). 
         * Nous avons ensuite utilisé une boucle while pour vérifier si la tâche est terminée en appelant 
         * la méthode isDone() sur l'objet Future. Une fois que la tâche est terminée, nous utilisons 
         * la méthode get() pour récupérer le résultat de la tâche. 
         * Notez que la méthode get() bloque le thread en cours d'exécution jusqu'à ce que le résultat soit disponible.
         */
		
	}

}

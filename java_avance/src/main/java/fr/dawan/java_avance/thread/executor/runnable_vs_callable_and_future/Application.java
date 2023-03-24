package fr.dawan.java_avance.thread.executor.runnable_vs_callable_and_future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable versus Runnable
 * @author Admin
 *
 */
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		/**
		 * il ne permet de renvoyer aucun résultat
		 */
		Runnable runnableTask = () -> {
			System.out.println("Running a runnable task");
			
		};
		
		executorService.execute(runnableTask);

		//implémentation
		Callable<String> callable = () -> {
			System.out.println("Running a callable task");
			return "Callable task complete";
		};
		
		//appel
		//méthode submit permet de soumettre la tache callable
		Future<String> future= executorService.submit(callable);
		
		try {
			/**
			 * la méthode get() permet de récupérer le résultat de la tache
			 * elle est bloquante
			 */
			System.out.println("Callable task result : "+future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		executorService.shutdown();
		
	}

}

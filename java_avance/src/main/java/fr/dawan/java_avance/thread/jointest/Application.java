package fr.dawan.java_avance.thread.jointest;

import java.util.Arrays;

import fr.dawan.java_avance.thread.MythreadDaemon;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * créer 2 tableaux de type list
		 * créer 2 thread
		 * On affiche d'abord la somme avant d'afficher un message de la méthode (ex : Tous les threads ont terminé leur travail)
		 */
		//
		
		CalculThread calcul1 = new CalculThread(Arrays.asList(1,2,3,4,5,6));
		CalculThread calcul2 = new CalculThread(Arrays.asList(7,8,9,10));

		
		Thread t1 = new Thread(calcul1::sumItemsTab);
		Thread t2 = new Thread(()->calcul2.sumItemsTab());
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		System.out.println("Tous les threads ont terminé leur travail");
		

		
		
	}

}

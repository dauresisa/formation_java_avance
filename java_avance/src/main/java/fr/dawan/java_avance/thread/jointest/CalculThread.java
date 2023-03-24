package fr.dawan.java_avance.thread.jointest;

import java.util.ArrayList;
import java.util.List;

public class CalculThread {
	List<Integer> numeros = new ArrayList<Integer>();

	public CalculThread(List<Integer> asList) {
		numeros=asList;
	}

	//parcourir la liste des numéros et les additionner et les afficher la somme dans la console
	public void sumItemsTab() {
		int somme=0;
		for (Integer integer : numeros) {
			somme+=integer;
		}
		System.out.println("la somme obtenue est "+somme);
	}

}

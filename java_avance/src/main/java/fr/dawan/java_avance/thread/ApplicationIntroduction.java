package fr.dawan.java_avance.thread;

public class ApplicationIntroduction {

	public static void main(String[] args) {
		//double a = Double.parseDouble("toto");
		
		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().getState());
		System.out.println(Thread.currentThread().getId());
	}

}

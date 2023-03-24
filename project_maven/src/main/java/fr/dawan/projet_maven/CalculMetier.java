package fr.dawan.projet_maven;

public class CalculMetier {
	
	public double somme(double a,double b) {
		return a + b;
	}
	
	public double produit(double a,double b) {
		return a * b;
	}

	public double division(double a,double b) {
		if(b==0) {
			throw new ArithmeticException("le diviseur est null");
		}
		return a / b;
	}
	
}

package fr.dawan.projet_maven;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.function.Executable;

@TestInstance(Lifecycle.PER_CLASS)
class CalculMetierTest {

	private CalculMetier metier;
	double a ;
	double b ;
	
	@BeforeAll
	void setUp() {
		metier = new CalculMetier();
		System.out.println("test début");
		a = 10;
		b = 15;
	}
	
	@AfterAll
	void afterTest() {
		System.out.println("après tous les tests");
	}
	
	@Test
	void testSomme() {
		double actual = a+b;
		double expected = metier.somme(a, b);
		assertEquals(expected, actual);		
	}

	@Test
	void testProduit() {
		double actual = a*b;
		double expected = metier.produit(a, b);
		//assertTrue(expected == actual);	
		assertEquals(expected, actual);	
	}

	@Test
	void testDivisionByZeroShouldRetunrExpectedResult() {
		double actual = a / b;
		double expected = metier.division(a, b);
		assertEquals(expected, actual);	
	}
	
	@Test
	void testDivisionByZeroShouldThrowException() {
		double b = 0;		
		assertThrows(ArithmeticException.class,() -> metier.division(a, b));	
		 
	}
	

}

package fr.dawan.java_avance.trainingtest;

public class FactoryGeneric {
	
	public static <T> Object createInstance(Class<T> clazz) throws Exception {
		return  clazz.getConstructor().newInstance();
	}

}

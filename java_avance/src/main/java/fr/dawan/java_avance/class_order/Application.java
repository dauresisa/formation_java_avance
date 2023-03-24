package fr.dawan.java_avance.class_order;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Application {

	public static void main(String[] args) {
		CustomClassLoader classLoader = new CustomClassLoader();

		try {
			Object obj =classLoader.findClass("fr.dawan.java_avance.class_order.CustomClassLoader").getDeclaredConstructor().newInstance();
			Method[] methods = obj.getClass().getDeclaredMethods();
			System.out.println(String.format("méthode of %s classe", obj.getClass().getName()));
			for (Method method : methods) {
				System.out.println(method.getName());
			}
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}

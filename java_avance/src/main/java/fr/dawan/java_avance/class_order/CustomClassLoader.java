package fr.dawan.java_avance.class_order;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader{
	
/**
 * Elle va nous permettre de trouver une classe à partir de son nom complet
 * name : nom complet (ex : fr.dawan.class_loader.CustomClassLoader)
 */
@Override
protected Class<?> findClass(String name) throws ClassNotFoundException {
	byte[] b = loadClassFromFile(name);
	return defineClass(name,b, 0,b.length);
}

/**
 * Charger les données de la classe à partir d'un fichier
 * @return
 */
private byte[] loadClassFromFile(String fileName) {
	InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName.replace('.', File.separatorChar)+".class");
	byte[] buffer;
	ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	int nextValue = 0;
	try {
		while ((nextValue = inputStream.read())!=-1) {
			byteStream.write(nextValue);
			
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//le fichier est chargé dans un tableau de byte en utilisant un objet ByteArrayOutputStream
	buffer = byteStream.toByteArray();
	return buffer;
}
}

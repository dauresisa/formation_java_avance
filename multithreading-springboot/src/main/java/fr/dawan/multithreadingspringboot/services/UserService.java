package fr.dawan.multithreadingspringboot.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.multipart.MultipartFile;

import fr.dawan.multithreadingspringboot.entities.User;


/**
 * cette interface fournit des opérations asynchrones pour enregistrer des utilisateur à partir
 * de fichier CSV et récupérer tous les utilisateurs
 * @author Admin
 *
 */

public interface UserService {
	
    /*
     * Les CompletableFuture sont des objets qui représentent une valeur qui sera disponible dans le futur. 
     * Ils permettent de rendre les opérations de service asynchrones. 
     * Cela signifie que la méthode peut retourner immédiatement sans bloquer l'exécution de l'application 
     * pendant que l'opération de service est en cours. Le résultat de l'opération de service sera disponible plus tard, 
     * lorsqu'il sera résolu par la CompletableFuture
     */
    
    /*
     * MultipartFile est une interface dans Spring Framework qui représente un fichier téléchargé 
     * dans une application Web. 
     * Elle fournit des méthodes pour accéder au contenu du fichier, comme son nom, sa taille, 
     * son type de contenu et les octets qui le composent.
     */
	
	
	public CompletableFuture<List<User>> saveUsers(MultipartFile file) throws Exception;
	public CompletableFuture<List<User>> findAllUsers();

	
	

}

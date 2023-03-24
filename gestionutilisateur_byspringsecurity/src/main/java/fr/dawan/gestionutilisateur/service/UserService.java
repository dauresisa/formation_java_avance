package fr.dawan.gestionutilisateur.service;

import fr.dawan.gestionutilisateur.dto.UserDTO;
import fr.dawan.gestionutilisateur.service.exceptions.EmailAlreadyInUseException;
import fr.dawan.gestionutilisateur.service.exceptions.PassWordNotMatching;

/**
 * cette interface fournit des opérations asynchrones pour enregistrer des utilisateur à partir
 * de fichier CSV et récupérer tous les utilisateurs
 * @author Admin
 *
 */

public interface UserService {
	
 UserDTO findByEmail(String email);
 
 UserDTO saveUser(UserDTO userDTO) throws EmailAlreadyInUseException, PassWordNotMatching;
	
	

}

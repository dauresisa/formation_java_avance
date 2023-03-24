package fr.dawan.user_mockito.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.user_mockito.entities.User;
import fr.dawan.user_mockito.services.UserService;
import jakarta.persistence.PostRemove;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;
	
	/**
	 * cette classe représente toute la réponse HTTP
	 * elle contient l'entête, le body et le code état status 
	 * @return
	 * Le paramètres "produces" spécifie le type MiMe de la réponse que la méthode peut produire
	 * le type MIME est "application/json" pour indiquer que le corps de la réponse doit être le type json
	 */
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users= service.getUsers();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		
	}
	
	
	/**
	 * La valeur de la variable {id} doit être extraite de l'url de la requête et liée à la variable "id" dans la méthode du contrôleur
	 * cela permet de récupérer des données à partir de l'url de la requête
	 * et de les utiliser pour effectuer des opérations de traitement
	 * @param id
	 * @return
	 */
	//===> /api/users/2
	@GetMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		User user=null;;
		try {
			user = service.getUserById(id);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		} catch (Exception e) {	
			e.printStackTrace();
			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
		}	
	}
	
	
	/**
	 * @RequestBody indique que le paramètre "user" doit être lié au body de la requête HTTP
	 * Cette annotation indique que le corps de la requete doit être converti en un objet "User" et lié à la variable "user'
	 * @param user
	 * @return
	 */
	@PutMapping( consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user){
		User u=service.saveOrUpdate(user);
		return new ResponseEntity<User>(u,HttpStatus.CREATED);
	}
	
	/**
	 * @RequestBody indique que le paramètre "user" doit être lié au body de la requête HTTP
	 * Cette annotation indique que le corps de la requete doit être converti en un objet "User" et lié à la variable "user'
	 * @param user
	 * @return
	 */
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user){
		 User u=service.saveOrUpdate(user);
		 if(u!=null) {
			 return new ResponseEntity<User>(u,HttpStatus.OK);
		 }else {
			 return new ResponseEntity<User>(u,HttpStatus.NOT_FOUND);
		 }	 
	}
	
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	@DeleteMapping(value="/{id}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		try {			
			service.deleteUser(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {	
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}

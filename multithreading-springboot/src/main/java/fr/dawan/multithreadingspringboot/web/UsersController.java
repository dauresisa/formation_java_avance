package fr.dawan.multithreadingspringboot.web;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.dawan.multithreadingspringboot.entities.User;
import fr.dawan.multithreadingspringboot.services.UserService;



@RequestMapping("/api/users")
@RestController
public class UsersController {
	
	private static final Logger  logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;

	

/*
         * Il s'agit d'une méthode @PostMapping Java Spring Boot qui accepte un tableau
         * d'objets MultipartFile en tant que paramètre. La méthode est mappée sur
         * l'endpoint /users, et elle consomme le type de média
         * MULTIPART_FORM_DATA_VALUE et produit le type de média application/json.
         * 
         * 
         * L'annotation @RequestParam est utilisée pour lier le paramètre "files" aux
         * fichiers qui sont téléchargés à partir du formulaire. Les fichiers
         * téléchargés peuvent être accessibles via le tableau "files".
         */
	///api/users?files
	@PostMapping( consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> saveUsers(@RequestParam(value="files") MultipartFile[] files) throws InterruptedException, ExecutionException{
		
		CompletableFuture<List<User>> future = null;
		
		for (MultipartFile multipartFile : files) {
			try {
				future= userService.saveUsers(multipartFile);
				//return new ResponseEntity<List<User>>(future.get(),HttpStatus.CREATED);				
			} catch (Exception e) {			
				logger.equals(e.getMessage());
				return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
			}
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@GetMapping(value="usersFormateur", produces = "application/json")
	public CompletableFuture<ResponseEntity<?>> findAllUsersFormateur(){
		//la méthode "thenApply()" est utilisée pouir trransformer l'objet "CompletableFuture<List<User>>"
		//retourné par userService.findAll() en un objet ResponseEntity<?>
		
		return userService.findAllUsers().thenApply(ResponseEntity::ok);
	}
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<User>> findAllUsers(){
		CompletableFuture<List<User>> future = null;
		future = userService.findAllUsers();
		try {
			return new ResponseEntity<List<User>>(future.get(),HttpStatus.OK);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//la méthode "thenApply()" est utilisée pouir trransformer l'objet "CompletableFuture<List<User>>"
		//retourné par userService.findAll() en un objet ResponseEntity<?>
		return null;
	}
	
	/**
	 * la méthode est appelée 3 fois donc 3 taches sont éxectée de manière asynchrone pour récupérer la liste des utilisateurs
	 * @return
	 */
	
	@GetMapping(value = "/usersByThread", produces = "application/json")
	public ResponseEntity<User> getUsersByThread(){
		CompletableFuture<List<User>> user1=userService.findAllUsers();		
		
		CompletableFuture<List<User>> user2=userService.findAllUsers();
		CompletableFuture<List<User>> user3=userService.findAllUsers();

		/*
         * La méthode "CompletableFuture.allOf()" est utilisée pour attendre que toutes les 
         * tâches asynchrones soient terminées avant de continuer l'exécution de la méthode. 
         * Cela garantit que toutes les tâches ont été exécutées avant que la méthode ne renvoie une réponse.
         */
		CompletableFuture.allOf(user1,user2,user3).join();
		return ResponseEntity.status(HttpStatus.OK).build();		
	}
	
}

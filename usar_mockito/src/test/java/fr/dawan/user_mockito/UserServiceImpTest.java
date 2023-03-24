package fr.dawan.user_mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.dawan.user_mockito.entities.User;
import fr.dawan.user_mockito.repositories.UserRepository;
import fr.dawan.user_mockito.services.UserServiceImpl;

/**
 * Cette annotation est utilisée pour activer l'intégration de mockito avec Junit5 jupiter
 * elle permet de créer des objets mock à utiliser dans les tests unitaires
 * @author Admin
 *
 */
@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class UserServiceImpTest {

	/**
	 * Cette annotation est utilisée pour créer un objet mock d'une classe ou d'un interface
	 * cela nous permet de remplacer la vraie implémentation de cette classe par un objet qui va simmuler le comportement
	 * de la vraie implémentation
	 */
	@Mock
	private UserRepository repository;
	
	/**
	 * Cela permet à mockito d'injecter automatiquement les objets mocks créés avec @Mock 
	 * dans la classe à tester (ici UserServiceImpl)
	 * Elle permet de simplifier la configuration de l'objet à tester en injectant automatiquement toutes les dépendances simulées.
	 */
	@InjectMocks
	private UserServiceImpl serviceImpl;
	
	//Créer une liste de users fictive
	List<User> users = new ArrayList<User>();
	
	//créer 2 utilisateurs
	User user1 ;
	User user2 ;
	
	
	@BeforeAll
	void setup() {	
		user1 = new User(1L,"jean","Némarre","jean.nemarre@gmail.com","Male");
		user2 = new User(2L,"anne","Akronisme","anne.akronisme@gmail.com","Female");
		users.add(user1);
		users.add(user2);
	}

	@Test
	void testGetUsers() {		
		//définir le comportement simulé du mock pour la méthode du repository, findAll()
		Mockito.when(repository.findAll()).thenReturn(users);
		
		//appeler la méthode findall() du service implémenté;
		List<User> result = serviceImpl.getUsers();
		
		//vérifier que la méthode findall a bien été exécuter une fois
		Mockito.verify(repository,Mockito.times(1)).findAll();
		
		//vérifier que la liste envoyée par la méthode findall du service est égale à la liste fictive
		//créée précedemment
		
		assertEquals(users, result);
		
		//vérification que la liste retournée contient les 2 utilisateurs
		assertEquals(2, result.size());
		assertEquals(user1.getFirstname(), result.get(0).getFirstname());
		assertEquals(user2.getFirstname(), result.get(1).getFirstname());
		
	}

	@Test
	void testGetUserByIdReturnNull() throws Exception {		
		Long userId=123l;

		Mockito.when(repository.findById(userId)).thenReturn(Optional.empty());
		Exception e= assertThrows(Exception.class,()-> serviceImpl.getUserById(userId));
		
		assertEquals("User n'existe pas pour l'id "+userId, e.getMessage());
		
	}
	
	
	@Test
	void testGetUserById() throws Exception {
		
		Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(user1));
		User u = serviceImpl.getUserById(1L);
		Mockito.verify(repository,Mockito.times(1)).findById(1L);
		assertEquals(user1, u);
	}

	@DisplayName("save new user")
	@Test
	void testSaveOrUpdate_save() {		
		Mockito.when(repository.save(user1)).thenReturn(user1);
		User u = serviceImpl.saveOrUpdate(user1);
		assertEquals(user1, u);
	}
	
	
	@Test
	void testSaveOrUpdate_update() {
		
		user2 = new User(2L,"anne","Akronisme","anne.akronisme@gmail.com","Female");
		//créer un nouvel objet pour représenter l'utilisateur à mettre à jour
		User userUpdate = new User(2l,"annita","aaaa","annita.aaaa@gmail.com","Female");
		
		
		Mockito.when(repository.save(userUpdate)).thenReturn(userUpdate);		
		user2 = serviceImpl.saveOrUpdate(userUpdate);
		assertEquals(user2.getEmail(), userUpdate.getEmail());
	}

	@Test
	void testDeleteUserUser() {
		//les 2 lignes fonctionnent ensembles, quand on indique le comportement simulé il faut simuler derrière la fonction
		doNothing().when(repository).delete(user1);		
		serviceImpl.deleteUser(user1);
		
		
		verify(repository,times(1)).delete(user1);
		
		//les 2 lignes fonctionnent ensembles, quand on indique le comportement simulé il faut simuler derrière la fonction
		Mockito.when(repository.findById(user1.getId())).thenReturn(Optional.empty());
        Optional<User> deleteUser = repository.findById(user1.getId());
        
        
        assertFalse(deleteUser.isPresent());

	}
	

	@Test
	void testDeleteUserLong() throws Exception {	
//		User user3 = new User(3L, "toto", "qsfds", "email", "Male");
		Long idUserMock = user1.getId();
		Mockito.when(repository.findById(idUserMock)).thenReturn(Optional.of(user1));
		
		//les 2 lignes fonctionnent ensembles, quand on indique le comportement simulé il faut simuler derrière la fonction
		doNothing().when(repository).delete(user1);		
		serviceImpl.deleteUser(idUserMock);
		
		
		verify(repository,times(1)).delete(user1);
		
		//les 2 lignes fonctionnent ensembles, quand on indique le comportement simulé il faut simuler derrière la fonction
		Mockito.when(repository.findById(user1.getId())).thenReturn(Optional.empty());
        Optional<User> deleteUser = repository.findById(user1.getId());
        
        
        assertFalse(deleteUser.isPresent());

	}

}

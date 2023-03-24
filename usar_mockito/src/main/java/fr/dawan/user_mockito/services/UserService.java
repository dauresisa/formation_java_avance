package fr.dawan.user_mockito.services;

import java.util.List;

import fr.dawan.user_mockito.entities.User;

public interface UserService {
	
	List<User> getUsers();
	
	User getUserById(Long id) throws Exception;
	
	User saveOrUpdate(User user);
	
	void deleteUser(User user);

	void deleteUser(Long id) throws Exception;
	
	

}

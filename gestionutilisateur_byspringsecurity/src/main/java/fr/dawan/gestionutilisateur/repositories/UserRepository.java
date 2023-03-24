package fr.dawan.gestionutilisateur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.gestionutilisateur.entities.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}

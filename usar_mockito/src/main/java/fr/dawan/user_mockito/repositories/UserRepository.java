package fr.dawan.user_mockito.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.user_mockito.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

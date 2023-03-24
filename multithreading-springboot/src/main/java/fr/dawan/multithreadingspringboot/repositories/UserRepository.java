package fr.dawan.multithreadingspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.multithreadingspringboot.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

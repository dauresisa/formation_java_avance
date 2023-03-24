package fr.dawan.gestionutilisateur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.gestionutilisateur.entities.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}

package fr.dawan.gestionutilisateur.dto;

import java.util.Collection;

import fr.dawan.gestionutilisateur.entities.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {

private Long id;
        
        @Size (min = 3, max = 10, message = "Invalid first name!(3-10 characters)")
        private String firstName;
        
        @Size (min = 3, max = 10, message = "Invalid first name!(3-10 characters)")
        private String lastName;

        
        @NotNull
        @Pattern(regexp = "^[_a-zA-Z0-9-]{1,}[.]?[_a-zA-Z0-9-]{0,}[@]{1}[a-z]{5,}[.]{1}[A-Za-z]{2,5}$", message="Invalid Email")
        private String email;
        
        @Size (min = 4, max = 50, message = "Invalid password !(5-15 characters)")
        @NotNull
        private String password;
        
        @NotNull
        private String repeatPassword;
        
        private Collection<Role> roles;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRepeatPassword() {
			return repeatPassword;
		}

		public void setRepeatPassword(String repeatPassword) {
			this.repeatPassword = repeatPassword;
		}

		public Collection<Role> getRoles() {
			return roles;
		}

		public void setRoles(Collection<Role> roles) {
			this.roles = roles;
		}
        





}

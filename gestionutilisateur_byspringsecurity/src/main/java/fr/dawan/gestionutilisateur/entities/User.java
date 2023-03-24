package fr.dawan.gestionutilisateur.entities;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Table(name="users")
@Entity
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstname;
	private String lastname;
	private String pwd;
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="users_roles",
               joinColumns = @JoinColumn(
               name="user_id", referencedColumnName = "id"), 
               inverseJoinColumns = @JoinColumn(
               name="role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(unique = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public User() {

	}
	public User(String firstname, String lastname, String pwd, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.pwd = pwd;
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
	public User(String pwd, String email) {
		super();
		this.pwd = pwd;
		this.email = email;
	}
	
	
	

	
}

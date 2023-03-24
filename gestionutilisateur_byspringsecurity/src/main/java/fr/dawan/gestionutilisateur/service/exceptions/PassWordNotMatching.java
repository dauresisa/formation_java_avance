package fr.dawan.gestionutilisateur.service.exceptions;

public class PassWordNotMatching extends Exception {

	private String passwordNotMatch;

	public PassWordNotMatching() {
		// TODO Auto-generated constructor stub
	}
	public PassWordNotMatching(String passwordNotMatch) {
		super();
		this.passwordNotMatch = passwordNotMatch;
	}

	public String getPasswordNotMatch() {
		return passwordNotMatch;
	}
	
	
}

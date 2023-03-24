package fr.dawan.gestionutilisateur.service.exceptions;

public class EmailAlreadyInUseException extends Exception {
	
	private String emailAlreadyUsed;
	
	public EmailAlreadyInUseException() {
		// TODO Auto-generated constructor stub
	}

	public EmailAlreadyInUseException(String emailAlreadyUsed) {
		
		this.emailAlreadyUsed = emailAlreadyUsed;
		System.out.println(this.emailAlreadyUsed);
	}

	public String getEmailAlreadyUsed() {
		return emailAlreadyUsed;
	}
	
	

}

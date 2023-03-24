package fr.dawan.gestionutilisateur.securitie;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.dawan.gestionutilisateur.dto.UserDTO;
import fr.dawan.gestionutilisateur.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDTO usDto=userService.findByEmail(email);
		
		Collection<? extends GrantedAuthority> authorities = usDto.getRoles().stream()
				.map(t -> new SimpleGrantedAuthority(t.getName())).collect(Collectors.toList());
		
		User user  = new User(usDto.getEmail(), usDto.getPassword(), authorities);
		return null;
	}

}

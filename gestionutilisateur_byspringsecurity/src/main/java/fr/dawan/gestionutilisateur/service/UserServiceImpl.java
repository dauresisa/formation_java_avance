package fr.dawan.gestionutilisateur.service;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.gestionutilisateur.dto.UserDTO;
import fr.dawan.gestionutilisateur.entities.Role;
import fr.dawan.gestionutilisateur.entities.User;
import fr.dawan.gestionutilisateur.repositories.RoleRepository;
import fr.dawan.gestionutilisateur.repositories.UserRepository;
import fr.dawan.gestionutilisateur.service.exceptions.EmailAlreadyInUseException;
import fr.dawan.gestionutilisateur.service.exceptions.PassWordNotMatching;




@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public UserDTO findByEmail(String email) {
		User user = userRepository.findByEmail(email); 
		return mapper.map(user, UserDTO.class);
	}

	
	
	@Override
	public UserDTO saveUser(UserDTO userDTO) throws EmailAlreadyInUseException, PassWordNotMatching {
		User user = mapper.map(userDTO, User.class);
		User userBdd = userRepository.findByEmail(user.getEmail());
		
		if(userBdd!=null) {
			throw new EmailAlreadyInUseException("This email is already used");
		}
		
		if(userDTO.getPassword().equals(userDTO.getRepeatPassword())) {
			throw new PassWordNotMatching("password not matching");
		}
		
		String hashedPwd = passwordEncoder.encode(userDTO.getPassword());
		user.setPwd(hashedPwd);
		
		//on attribut par défaut le role user
		Role role = roleRepository.findByName("USER");
		user.setRoles(Arrays.asList(role));
		User saveUser = userRepository.save(user);
		
		return mapper.map(saveUser, UserDTO.class);
	}

	
}

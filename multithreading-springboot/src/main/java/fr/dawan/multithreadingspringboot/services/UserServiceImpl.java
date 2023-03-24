package fr.dawan.multithreadingspringboot.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fr.dawan.multithreadingspringboot.entities.User;
import fr.dawan.multithreadingspringboot.repositories.UserRepository;



@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	@Async // permet l'execution asynchrone de cette méthode
	public CompletableFuture<List<User>> saveUsers(MultipartFile file) throws Exception {
		long start = System.currentTimeMillis();

		List<User> users = parseCSVFile(file);
		logger.info("saving list of users of size {}",users.size()," "+Thread.currentThread().getName());

		users = userRepository.saveAll(users);

		long end = System.currentTimeMillis();
		logger.info("Total time {}",(end - start));

		return CompletableFuture.completedFuture(users);
	}

	@Override
	@Async
	public CompletableFuture<List<User>> findAllUsers() {
		logger.info("get list users by "+Thread.currentThread().getName());

		List<User> users = userRepository.findAll();
		return CompletableFuture.completedFuture(users);
	}

	/**
	 * cette méthode est utilisée pour extraire les données d'un fichier CSV et convertir en une liste de user
	 * elle prend en argument un multipartfile qui représente le fichier CSV à extraire
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	private List<User> parseCSVFile(MultipartFile file) throws Exception{


		List<User> users = new ArrayList<User>();

		try {
			//pour garantir que la ressource sera fermée à la fin, la méthode utilise "try-with-ressouces"
			try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){
				String line = br.readLine();
				while ((line=br.readLine())!=null) {
					String[] data = line.split(";");
					User user = new User();
					user.setFirstname(data[0]);
					user.setLastname(data[1]);
					user.setEmail(data[2]);
					user.setGender(data[3]);
					users.add(user);
				}
			}
		}
		catch (Exception e) {
			logger.error("error lors du parse CSV files {}",e);
			throw new Exception("error lors du parse CSV files {}",e);
		}

		return users;
	}
}

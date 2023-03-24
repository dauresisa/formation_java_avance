package fr.dawan.java_avance.trainingtest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PenguinService {
	
	Penguin creatSimplePinguin();
	
	List<Penguin> getPinguins(Penguin...penguins);
	
	Penguin getPenguinByName(List<Penguin> penguins, String name) throws Exception;
	
	Optional<Penguin> getFriend(Penguin penguin) throws Exception;
	
	List<Penguin> getPenguinWithLetter(List<Penguin> penguins, String letter);
	
	List<Penguin> getFriends(List<Penguin> penguins);
	
	List<Penguin> getSmallestSize(List<Penguin> penguins1,List<Penguin> penguins2);
	

	
}

package fr.dawan.java_avance.trainingtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PenguiServiceImpl implements PenguinService {

	@Override
	public Penguin creatSimplePinguin() {
		// TODO Auto-generated method stub
		return new Penguin("p1");
	}

	@Override
	public List<Penguin> getPinguins(Penguin... penguins) {	
		List<Penguin> l = Arrays.asList(penguins).stream().collect(Collectors.toList());
		return l;
	}

	@Override
	public Penguin getPenguinByName(List<Penguin> penguins, String name) throws Exception {
		if(penguins==null) {
			throw new Exception("La liste en entrée est nulle, vous devez la remplir.");
		}
		Penguin t =penguins.stream().filter(x ->x.getName().equals(name)).findFirst().orElse(null);
		if(t==null) {
			throw new Exception("Le pinguin "+name+" n'existe pas");
		}
		return t; 
	}

	@Override
	public Optional<Penguin> getFriend(Penguin penguin) throws Exception {
		// TODO Auto-generated method stub
		Optional<Penguin> o= Optional.ofNullable(penguin.getFriend());
		if(o.isEmpty()) {
			throw new Exception("Le pinguoin "+penguin.getName()+" n'a pas de friend");
		}
		 return o;
	}

	@Override
	public List<Penguin> getPenguinWithLetter(List<Penguin> penguins, String letter) {
		// TODO Auto-generated method stub
		return penguins.stream().filter(t ->t.getName().contains(letter)).toList();
	}

	@Override
	public List<Penguin> getFriends(List<Penguin> penguins) {
		List<Penguin> l = new ArrayList<Penguin>();
		// TODO Auto-generated method stub
		for (Penguin penguin : penguins) {
			l.add(penguin.getFriend());
		}
		return l;
	}

	@Override
	public List<Penguin> getSmallestSize(List<Penguin> penguins1, List<Penguin> penguins2) {
		return Collections.min(Arrays.asList(penguins1,penguins2),Comparator.comparingInt(List::size));
	}

}

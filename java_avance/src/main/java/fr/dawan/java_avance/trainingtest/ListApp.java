package fr.dawan.java_avance.trainingtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListApp {
	List<Integer> buildList(int a, int b, int c){
		List<Integer> list = Arrays.asList(a,b,c);
		return list;
	}

	List<Integer> buildList(int ...number){
		List<Integer> list = new ArrayList<>();
		for (int i : number) {
			list.add(i);
		}
		return list;
	}
	
	
	boolean equality(List<Integer> l1, List<Integer> l2 ) {
		
		if(l1==null)return false;
		return l1.equals(l2);
	}
	
	int first(List<Integer> ints) {
		return ints.get(0);
	}
	
	int last(List<Integer> ints) {
		return ints.get(ints.size()-1);
	}
	
	int medium(List<Integer> ints) {
		int mediumIndex = (ints.size()-1)/2;
		return ints.get(mediumIndex);
	}
	
	
}

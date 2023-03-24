package fr.dawan.java_avance.trainingtest;

import java.util.Objects;

public class Penguin {
	private String name;
	private  int speed = 10;

	private Penguin friend;
	
	
	
	
	public Penguin() {
		// TODO Auto-generated constructor stub
	}
	public Penguin(String name) {
		super();
		this.name = name;
	}
	public Penguin(String name, Penguin friend) {
		super();
		this.name = name;
		this.friend = friend;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Penguin getFriend() {
		return friend;
	}
	public void setFriend(Penguin friend) {
		this.friend = friend;
	}
	

	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Penguin other = (Penguin) obj;
		return Objects.equals(name, other.name);
	}
	

	

}

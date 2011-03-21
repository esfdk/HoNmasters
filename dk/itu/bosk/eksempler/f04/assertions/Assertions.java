package dk.itu.bosk.eksempler.f04.assertions;

import java.util.ArrayList;
import java.util.List;

public class Assertions {
	
	List<String> l = new ArrayList<String>();
	String name = "";
	int age = -2;
	
	public static void main(String[] args){
		Assertions a = new Assertions();
		
		a.addToList("Hej");
		a.addToList("Java");
		a.emptyList();
		a.nameInCapital();
		a.increaseAge();
	}
	
	public void addToList(String s){
		l.add(s);
	}
	
	void emptyList(){
		l = new ArrayList<String>();
		assert l.size() == 0: "list is not empty";
	}

	void nameInCapital(){
		name = name.toUpperCase();
		assert classInvariant(): "name is not valid";
	}
	
	void increaseAge(){
		age++;
		assert classInvariant(): "age is not valid";
	}
	
	
	private boolean classInvariant(){
		if (name.equals("")||name == null)
			return false;
		if (age < 0)
			return false;
		return true;
	}

}

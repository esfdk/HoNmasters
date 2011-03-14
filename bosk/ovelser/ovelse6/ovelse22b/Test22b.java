package bosk.ovelser.ovelse6.ovelse22b;

import java.util.ArrayList;

public class Test22b {
	/**
	 *  Tests the system to check if a person
	 *  can have both a consultant and instructor role
	 * @param args
	 */
	public static void main(String[] args){
		  Person a = new Person("Jens", new ArrayList<Role>());
		  ArrayList<String> temp = new ArrayList<String>();
		  temp.add("BGPP");
		  a.addRole(new InstructorRole(temp));
		  
		  Person b = new Person("Hans", new ArrayList<Role>());
		  b.addRole(new ConsultantRole(3));
		  
		  Person c = new Person("Børge", new ArrayList<Role>());
		  ArrayList<String> temp2 = new ArrayList<String>();
		  temp2.add("BGPP");
		  temp2.add("BPAK");
		  c.addRole(new InstructorRole(temp2));
		  c.addRole(new ConsultantRole(5));
		  
		  System.out.println(a);
		  System.out.println(b);
		  System.out.println(c);
		 }

}

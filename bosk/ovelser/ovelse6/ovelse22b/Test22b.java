package bosk.ovelser.ovelse6.ovelse22b;

import java.util.ArrayList;

public class Test22b {
	
	public static void main(String[] args){
		  Person a = new Person("Jens", new ArrayList<Role>());
		  ArrayList<String> temp = new ArrayList<String>();
		  temp.add("BGPP");
		  a.addRole(new InstructorRole(temp));
		  
		  Person b = new Person("Hans", new ArrayList<Role>());
		  b.addRole(new ConsultantRole(3));
		  
		  Person c = new Person("B�rge", new ArrayList<Role>());
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

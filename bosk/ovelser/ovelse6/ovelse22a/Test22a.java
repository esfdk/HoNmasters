package bosk.ovelser.ovelse6.ovelse22a;

import java.util.ArrayList;

public class Test22a {
	
	public static void main(String[] args){
		  Instructor a = new Instructor("Jens", new ArrayList<String>());
		  a.addCourse("BGPP");
		  Consultant b = new Consultant("Hans", 3);
		  System.out.println(a.name + ", " + a.getCourses());
		  System.out.println(b.name + ", " + b.getCompetence());
		 }

}

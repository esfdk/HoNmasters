package bosk.ovelser.ovelse6.ovelse22a;

import java.util.ArrayList;


public class Instructor extends Person {
	
	private ArrayList<String> courses;
	
	public Instructor(String n, ArrayList<String> c)
	{
		setName(n);
		// makes a deepcopy of inputlist
		courses = new ArrayList<String>();
		for(String s: c){
			courses.add(s);
		}
	}
	
	public ArrayList<String> getCourses()
	{
		return courses;
	}
	
	public void removeCourse(String c)
	{
		courses.remove(c);
	}
	
	public void addCourse(String c)
	{
		courses.add(c);
	}
}

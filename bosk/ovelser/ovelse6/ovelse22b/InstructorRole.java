package bosk.ovelser.ovelse6.ovelse22b;

import java.util.ArrayList;

public class InstructorRole implements Role {

private ArrayList<String> courses;
	
	public InstructorRole( ArrayList<String> c)
	{
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
	
	public String toString()
	{
		String s = "Courses: ";
		for(String c : courses){
			s += c;
		}
		return s;
	}
}

package bosk.ovelser.ovelse6.ovelse22a;

import java.util.ArrayList;


public class Instructor extends Person {
	
	private ArrayList<String> courses;
	
	/**
	 * Constructor for Instructor
	 * makes a deepcopy of inputlist
	 * @param n name of the person
	 * @param c list of attending courses
	 */
	public Instructor(String n, ArrayList<String> c)
	{
		setName(n);
		// makes a deepcopy of inputlist
		courses = new ArrayList<String>();
		for(String s: c){
			courses.add(s);
		}
	}
	/**
	 * returns a list of attending courses for a instructor
	 * @return a list of attendting courses
	 */
	public ArrayList<String> getCourses()
	{
		return courses;
	}
	/**
	 * removes a course from the list of attending courses
	 * @param c list of attending courses
	 */
	public void removeCourse(String c)
	{
		courses.remove(c);
	}
	/**
	 *  adds a course to the list of attendig courses
	 * @param c list of attending courses
	 */
	public void addCourse(String c)
	{
		courses.add(c);
	}
}

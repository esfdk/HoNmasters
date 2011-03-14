package bosk.ovelser.ovelse6.ovelse22b;

import java.util.ArrayList;

public class InstructorRole implements Role {

private ArrayList<String> courses;
	

	/**
	 * Constructor for InstructorRole
	 * makes a deepcopy of inputlist
	 * @param n name of person
	 * @param c list of attending courses
	 */
	public InstructorRole( ArrayList<String> c)
	{
		// makes a deepcopy of inputlist
		courses = new ArrayList<String>();
		for(String s: c){
			courses.add(s);
		}
	}
	/**
	 * returns a list of attending courses
	 * @return list of courses
	 */
	public ArrayList<String> getCourses()
	{
		return courses;
	}
	/**
	 * removes a course from the courselist
	 * @param c list of attending courses
	 */
	public void removeCourse(String c)
	{
		courses.remove(c);
	}
	/**
	 * adds a course to the courselist
	 * @param c list of attending courses
	 */
	public void addCourse(String c)
	{
		courses.add(c);
	}
	
	/*'
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		String s = "Courses: ";
		for(String c : courses){
			s += c + ", ";
		}
		return s;
	}
}

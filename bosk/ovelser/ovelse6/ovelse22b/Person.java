package bosk.ovelser.ovelse6.ovelse22b;

import java.util.ArrayList;

public class Person {
	
	private String name;
	private ArrayList<Role> roles;

	/**
	 * Constructor for Person
	 * @param n name of person
	 * @param r roles a person have
	 */
	public Person(String n, ArrayList<Role> r)
	{
		name = n;
		roles = r;
	}
	/**
	 * sets the name for person
	 * @param n name of person
	 */
	public void setName(String n)
	{
		name = n;
	}
	/**
	 * returns the name of person
	 * @return a name for a person
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * adds a role to person
	 * @param r roles a person have
	 */
	public void addRole(Role r)
	{
		roles.add(r);
	}
	/**
	 * removes a role from person
	 * @param r roles a person have
	 */
	public void removeRole(Role r)
	{
		roles.remove(r);
	}
	/**
	 * returns the complet list of roles for person
	 * @return a list of roles
	 */
	public ArrayList<Role> getRoles()
	{
		return roles;
	}
	/**
	 * Gets all roles of the input type that person has
	 * @param r roles a person have
	 * @return An ArrayList<Role> containing all roles of the same type as input
	 */
	public ArrayList<Role> getSpecificRole(Role r)
	{
		ArrayList<Role> temp = new ArrayList<Role>();
		for(Role b : roles){
			if(b.getClass()==r.getClass()){
				temp.add(b);
			}
		}
		return temp;
	}
	
	/*'
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		  String s = name;
		  for(Role r : roles){
		   s += (r.toString() + ", ");
		  }
		return s;
		 }
}


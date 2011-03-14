package bosk.ovelser.ovelse6.ovelse22b;

import java.util.ArrayList;

public class Person {
	
	private String name;
	private ArrayList<Role> roles;

	public Person(String n, ArrayList<Role> r)
	{
		name = n;
		roles = r;
	}

	public void setName(String n)
	{
		name = n;
	}

	public String getName()
	{
		return name;
	}

	public void addRole(Role r)
	{
		roles.add(r);
	}
	public void removeRole(Role r)
	{
		roles.remove(r);
	}
	
	public ArrayList<Role> getRoles()
	{
		return roles;
	}
	
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
	public String toString(){
		  String s = name;
		  for(Role r : roles){
		   s += (r.toString() + ", ");
		  }
		return s;
		 }
}

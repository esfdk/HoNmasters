package bosk.ovelser.ovelse5.ovelse54;

import java.util.HashSet;
import java.util.Collection;

public class Division
{
	boolean isOrganisation;
	HashSet<Division> divisions = new HashSet<Division>();
	HashSet<Employee> employees = new HashSet<Employee>();
	String name;
	Employee leader;
	
	
	/**
	*	Constructor for class Division
	*
	*	@para n Name of organisation
	*	@para o True if Divison is an organisation, false if just a division
	*	@para d Collection of divisions to add to this division
	*	@para e Collection of employees to add to this division
	*	@para l Leader of this division
	*/
	public Division(String n, boolean o, Collection<Division> d, Collection<Employee> e, Employee l){
		name = n;
		isOrganisation = o;
		for(Division u : d){
			divisions.add(u);
		}
		for(Employee m : e){
			employees.add(m);
		}
		leader = l;
	}
	
	public HashSet<Division> getDivisions(){
		return divisions;
	}
	
	public HashSet<Employee> getEmployees(){
		return employees;
	}
	
	public boolean isOrganisation(){
		return isOrganisation;
	}
	
	public String getName(){
		return name;
	}
	
	public void changeName(String n){
		name = n;
	}
	
	public void addDivision(Division d){
		divisions.add(d);
	}
	
	public void removeDivision(Division d){
		divisions.remove(d);
	}
	
	public void addEmployee(Employee e){
		employees.add(e);
	}
	
	public void removeEmployee(Employee e){
		employees.remove(e);
	}
	
	public void changeOrgStatus(boolean b){
		isOrganisation = b;
	}
	
	public Employee getLeader(){
		return leader;
	}
	
	public void changeLeader(Employee e){
		leader = e;
	}
}
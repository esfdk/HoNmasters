package bosk.ovelse05.ovelse54;
public class Employee
{
	String name;
	
	/**
	*	Constructor for class Employee
	*
	*	@para n Name of employee
	*/
	public Employee(String n){
		name = n;
	}

	public String getName(){
		return name;
	}
	
	public void changeName(String n){
		name = n;
	}
}
package bosk.ovelser.ovelse7.ovelse71;

public enum SignalColor {
	RED("red"), YELLOW("yellow"), GREEN("green");
	private boolean drivingAllowed = false;
	private String description;
	
	private SignalColor(String d){
		description = d;
		if(description.equals("green")) drivingAllowed = true;
	}
	
	/**
	 * Checks if driving is allowed.
	 * 
	 * @return True, if driving is allowed. False, if not.
	 */
	public boolean isDrivingAllowed(){
		return drivingAllowed;
	}
	
	/**
	 * Returns a string containing information about the signal colour.
	 * 
	 * @return Description Returns a string containing information about the signal colour. 
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Returns a string containing information about the signal colour.
	 * 
	 * @return Returns a string containing information about the signal colour.
	 */
	@Override
	public String toString(){
		return description;
	}
	
}

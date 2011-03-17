package bosk.ovelser.ovelse7.ovelse71;

public enum SignalColor {
	RED("red"), YELLOW("yellow"), GREEN("green");
	private boolean drivingAllowed = false;
	private String description;
	private SignalColor(String d){
		description = d;
		if(description.equals("green")) drivingAllowed = true;
	}
	public boolean isDrivingAllowed(){
		return drivingAllowed;
	}
	public String getDescription(){
		return description;
	}
}

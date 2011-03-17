package bosk.ovelser.ovelse7.ovelse71;


public class TrafficLight {
	
	SignalColor colorOfLight = SignalColor.RED;
	public void setSignalColor(SignalColor sc){
		colorOfLight = sc;
	}
	public String getColorOfLight(){
		return colorOfLight.getDescription();
	}
	public boolean drivingAllowed(){
		return colorOfLight.isDrivingAllowed();
	}
	public String toString(){
		if(drivingAllowed()) return "The light is " + getColorOfLight() + ". Driving is allowed!";
		return "The light is " + getColorOfLight() + ". Driving is not allowed!";
	}
}

package bosk.ovelser.ovelse7.ovelse71;


public class TrafficLight {
	
	SignalColor colorOfLight = SignalColor.RED;
	
	/**
	 * Method to change the colour of the signal in the traffic light.
	 * @param sc The colour the traffic lgiht should change to.
	 */
	public void setSignalColor(SignalColor sc){
		colorOfLight = sc;
	}
	
	/**
	 * The current colour of the traffic light.
	 * 
	 * @return The current colour of the traffic light.
	 */
	public String getColorOfLight(){
		return colorOfLight.getDescription();
	}
	
	/**
	 * Checks if driving is allowed with the current colour of the traffic light.
	 * 
	 * @return Checks if driving is allowed with the current colour of the traffic light.
	 */
	public boolean drivingAllowed(){
		return colorOfLight.isDrivingAllowed();
	}
	
	/*'
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		if(drivingAllowed()) return "The light is " + getColorOfLight() + ". Driving is allowed!";
		return "The light is " + getColorOfLight() + ". Driving is not allowed!";
	}
}

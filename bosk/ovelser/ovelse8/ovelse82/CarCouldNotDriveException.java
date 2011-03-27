package bosk.ovelser.ovelse8.ovelse82;

public class CarCouldNotDriveException extends Exception{

	Exception e;
	
	/**
	 * Constructor for Exception CarCouldNotDrive
	 * 
	 * @param e The exception to be included with this exception.
	 */
	public CarCouldNotDriveException(Exception e){
		this.e = e;
	}
}

package bosk.ovelser.ovelse8.ovelse82;

public class CarCouldNotDriveException extends Exception{
	Exception e;
	public CarCouldNotDriveException(Exception e){
		this.e = e;
	}
}

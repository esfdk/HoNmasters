package bosk.ovelse08.ovelse82;

/*
 * In this call the following three parts of the excercise can be found.
 * "Grib fejlen og håndter den"
 * "Grib fejlen og smid en ny af en anden type"
 * "Ignorer fejlen (virker kun ved unchecked/RuntimeExceptions)"
 * 
 * The last part of the excercise can be found in Car
 */
public class Main {

	public static void main(String [] args){
		System.out.println("Calling startEngine");
		Car c = new Car("Porsche");
		
		//Catches an exception and handles it.
		try{
			c.stopEngine();
		}catch(Exception e){
			System.out.println("Could not stop engine");
		}
		
		try{
			driveCar(c);	
		}catch(Exception e){
			System.out.println("For some reason, the car could not drive." + e);
		}
		//Unchecked exception expected
		c.startEngine();
	}

	//Catches an exception and changes it.
	private static void driveCar(Car c) throws CarCouldNotDriveException{
		try{
			c.drive();
		}catch(Exception e){
			throw new CarCouldNotDriveException(e);
		}
	}

}

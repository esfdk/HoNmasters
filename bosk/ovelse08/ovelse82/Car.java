package bosk.ovelse08.ovelse82;

/*This class contains one part of the excercise. Main contains the other three.
 * "Viderekast fejlen – dvs. erklær den i metodens throws-del"
 */ 
public class Car {
	
	private String name;
	private boolean engineRunning;
	private boolean carBlocked;

	public Car(String n){
		name = n;
		engineRunning = false;
		carBlocked = false;
	}

	/**
	 * Starts the car's engine.
	 */
	public void startEngine(){
		if(!engineRunning){
			engineRunning = true;
		}
		else{
			throw new EngineFailureException();
		}
	}

	/**
	 * Stops the car's engine.
	 */
	public void stopEngine(){
		if(engineRunning){
			engineRunning = false;
		}
		else{
			throw new EngineFailureException();
		}
	}

	/**
	 * Tries to make the car drive.
	 * 
	 * @throws CarBlockedException
	 */
	public void drive() throws CarBlockedException{
		if(!carBlocked){
		}
		else{
			throw new CarBlockedException();
		}
	}
}

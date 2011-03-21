package bosk.ovelser.ovelse8.ovelse82;

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

	public void startEngine(){
		if(!engineRunning){
			engineRunning = true;
		}
		else{
			throw new EngineFailureException();
		}
	}

	public void stopEngine(){
		if(engineRunning){
			engineRunning = false;
		}
		else{
			throw new EngineFailureException();
		}
	}

	//Throws an exception.
	public void drive() throws CarBlockedException{
		if(!carBlocked){
			//TODO Make car move
		}
		else{
			throw new CarBlockedException();
		}
	}
}

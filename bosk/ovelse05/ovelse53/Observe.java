package bosk.ovelse05.ovelse53;
import java.util.Observable;
import java.util.Observer;



public class Observe implements Observer{
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg);
		
	}
}

package bosk.ovelser.ovelse6.ovelse25;
import bosk.ovelser.ovelse6.ovelse25.Time;


/*
* Illustrerer hvordan man kan lave anonyme klasser.
* Nedarver fra klassen Time.
*/
public class TimeTester{
	public static void main( String[] args ){
		Time dkTime = Time.createDanishTime();
		dkTime.setTime( 2, 54 );
		System.out.println( dkTime );
		dkTime.setTime( 13, 35 );
		System.out.println( dkTime );

		Time ukTime = Time.createEnglishTime();
		ukTime.setTime( 2, 54 );
		System.out.println( ukTime );
		ukTime.setTime( 13, 35 );
		System.out.println( ukTime );
	}
}
abstract class Time{
	int hour, minutes;
	void setTime( int hour, int minutes){
		if( hour < 0 || hour > 23 ){
			throw new IllegalArgumentException( hour + ":" + minutes + " is not a legal time" );
		}
		this.hour = hour;
		if( minutes < 0 || minutes > 59 ){
			throw new IllegalArgumentException( hour + ":" + minutes + " is not a legal time" );
		}
		this.minutes = minutes;
	}
	static Time createDanishTime(){
		return new Time(){
			public String toString(){
				return hour + ":" + minutes;
			}
		}; // bem�rk semikolon'et
	}
	static Time createEnglishTime(){
		return new Time(){
			public String toString(){
				if( hour == 0 ){
					return "12:" + minutes + " AM";	
				}
				else if( hour <= 12 ){
					return hour + ":" + minutes + " AM";
				}
				else{
					return (hour-12) + ":" + minutes + " PM";
				}
			}
		}; // bem�rk semikolon'et
	}
}
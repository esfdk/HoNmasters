package bosk.ovelser.ovelse6.ovelse25;
import bosk.ovelser.ovelse6.ovelse25.Time;

/**
 * Class Timetester is used to test internal implementation of Time.
 * @author Jakob Melnyk
 * @version Vers 1
 */
public class TimeTester{
	/**
	 * Main method prints out Danish time and English time.
	 * @param args Not used
	 */
	public static void main( String[] args ){
		Time dkTime = new Time.Danish();
		dkTime.setTime(2, 54);
		System.out.println(dkTime);
		dkTime.setTime(13, 35);
		System.out.println(dkTime);

		Time ukTime = new Time.English();
		ukTime.setTime(2, 54);
		System.out.println( ukTime );
		ukTime.setTime(13, 35);
		System.out.println( ukTime );
	}
}
/**
 * Intended to extend into regional times.
 * @author Jakob Melnyk
 * @version Vers 1
 */
abstract class Time{
	int hour, minutes;
	/**
	 * Sets the time of the time object
	 * @param hour How many hours the time is
	 * @param minutes How many minutes the time is
	 */
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
	/**
	 * Inner class for Danish time 
	 * @author Jakob Melnyk
	 * @version Vers 1
	 */
	static class Danish extends Time{
		/**
		 * Returns time as a String
		 * @return Time as a String	
		 */
		public String toString(){
			return hour + ":" + minutes;
			}
		}
	/**
	 * Inner class for English time
	 * @author Jakob Melnyk
	 * @version Vers 1
	 */
	static class English extends Time{
		/**
		 * Returns time as a String
		 * @return Time as a String	
		 */	
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
	}
}
package bosk.ovelser.ovelse6.ovelse25;
import bosk.ovelser.ovelse6.ovelse25.Weekday;


/**
 * Class WeekdayTester is used to test internal implementation of Weekday
 * @author Jakob Melnyk
 * @version Vers 1
 */
public class WeekdayTester{
	public static void main( String[] args ){
		Weekday dkDay = Weekday.createDanishWeekday();
		dkDay.setDay(3);
		System.out.println(dkDay);
		Weekday ukDay = Weekday.createEnglishWeekday();
		ukDay.setDay(4);
		System.out.println(ukDay);
	}
}
/**
 * Class Weekday is intended to be extended into different regional weekdays.
 * @author Jakob Melnyk
 * @version Vers 1
 */
abstract class Weekday{
	int day;
	void setDay( int day ){
		if( day < 1 || day > 7 ){
			throw new IllegalArgumentException( day + " is not a legal day" );
		}
		this.day = day;
	}
	/**
	 * Creates and returns a weekday of the Danish region type.
	 * @return A weekday object of the Danish region type
	 */
	public static Weekday createDanishWeekday() {
		return new Weekday(){
				public String toString(){
					switch( day ){
						case 1: return "Mandag";
						case 2: return "Tirsdag";
						case 3: return "Onsdag";
						case 4: return "Torsdag";
						case 5: return "Fredag";
						case 6: return "Lørdag";
						default: return "Søndag";
					}
			}
		};
	}
	/**
	 * Creates and returns a weekday of the English region type.
	 * @return A weekday object of the English region type
	 */
	static Weekday createEnglishWeekday(){
		return new Weekday(){
			public String toString(){
				switch( day ){
					case 1: return "Monday";
					case 2: return "Tuesday";
					case 3: return "Wednesday";
					case 4: return "Thursday";
					case 5: return "Friday";
					case 6: return "Saturday";
					default: return "Sunday";
				}
			}
		};
	}
}
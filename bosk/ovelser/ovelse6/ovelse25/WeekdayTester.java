package bosk.ovelser.ovelse6.ovelse25;
import bosk.ovelser.ovelse6.ovelse25.Weekday;


/*
* Illustrerer hvordan man kan lave indlejrede / nestede klasser
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
abstract class Weekday{
	int day;
	void setDay( int day ){
		if( day < 1 || day > 7 ){
			throw new IllegalArgumentException( day + " is not a legal day" );
		}
		this.day = day;
	}
	
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
package itu.bosk.labs.f02.inner;


/*
* Illustrerer hvordan man kan lave indlejrede / nestede klasser
*/
public class WeekdayTester{
	public static void main( String[] args ){
		Weekday dkDay = new Weekday.Danish( );
		dkDay.setDay( 3 );
		System.out.println( dkDay );
		Weekday ukDay = new Weekday.English( );
		ukDay.setDay( 4 );
		System.out.println( ukDay );
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
	static class Danish extends Weekday{
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
	}
	static class English extends Weekday{
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
	}
}
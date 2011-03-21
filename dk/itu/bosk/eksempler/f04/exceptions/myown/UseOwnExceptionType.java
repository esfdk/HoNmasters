package dk.itu.bosk.eksempler.f04.exceptions.myown;

/*
 * Eksempelprogrammet viser: 
 * (1) konstruktion af egen exceptiontype (se nederst) 
 * (2) instantiering af nyt exception-objekt 
 * (3) kastning af exception-objektet
 * (4) gribning af exception-objektet
 * (5) angivelse af at metoden kan finde på at kaste fejlen 
 */
public class UseOwnExceptionType {
	public static void main(String[] args) {
		for (int day = 0; day <= 8; day++) {
			try {
				printNameOfDay(day);
			} catch (IllegalDayException e) {
				// gribning af exception-objektet
				System.out.println("Fangede følgende Exception: " + e);
			}
		}
	}

	// angivelse af at metoden kan finde på at kaste fejlen
	public static void printNameOfDay(int dayNumber) throws IllegalDayException {
		switch (dayNumber) {
		case 1:
			System.out.println("Monday");
			return;
		case 2:
			System.out.println("Tuesday");
			return;
		case 3:
			System.out.println("Wednesday");
			return;
		case 4:
			System.out.println("Thursday");
			return;
		case 5:
			System.out.println("Friday");
			return;
		case 6:
			System.out.println("Saturday");
			return;
		case 7:
			System.out.println("Sunday");
			return;
		default:
			String msg = dayNumber + " is not a legal number for a day. ";
			msg += "Day must be an integer between 1 and 7.";

			// instantiering af nyt exception-objekt
			IllegalDayException e = new IllegalDayException(msg);

			// kastning af exception-objektet
			throw e;
		}
	}
}

// konstruktion af egen exceptiontype
class IllegalDayException extends Exception {

	public IllegalDayException() {
		super();
	}

	public IllegalDayException(String message) {
		super(message);
	}

	public IllegalDayException(Throwable cause) {
		super(cause);
	}

	public IllegalDayException(String message, Throwable cause) {
		super(message, cause);
	}
}

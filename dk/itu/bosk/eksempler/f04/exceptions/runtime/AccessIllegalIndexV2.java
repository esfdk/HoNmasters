package dk.itu.bosk.eksempler.f04.exceptions.runtime;
/*
 * Viser en RuntimeException - nemlig IndexOutOfBoundsException,
 * der håndteres. Viser også at det her virker tåbeligt... 
 * Faktisk er det et eksempel på rigtig dårlig brug af
 * Exceptions - nemlig til at opnå almindeligt flow med - 
 * dvs. vi helt bevidst forventer at få en fejl hver gang....!
 */

public class AccessIllegalIndexV2 {
	public static void main(String[] args) {
		String[] s = new String[3];
		s[0] = "Hej";
		s[1] = " med";
		s[2] = " dig!\n";
		try {
			int n = 0;
			while (true) {
				System.out.print(s[n]);
				n++;
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Vi forhindrede programmet i at gå ned, "
					+ "selvom vi tilgik et ulovligt index i arrayet");
		}
	}
}

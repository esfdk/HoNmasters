package dk.itu.bosk.eksempler.f04.exceptions.runtime;

/*
 * Viser en RuntimeException - nemlig IndexOutOfBoundsException,
 * der ikke håndteres
 */

public class AccessIllegalIndexV1 {
	public static void main(String[] args) {
		String[] s = new String[3];
		s[0] = "Hej";
		s[1] = " med";
		s[2] = " dig!\n";
		for (int n = 0; n <= 3; n++) {
			System.out.print(s[n]);
		}
	}
}

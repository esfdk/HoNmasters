package dk.itu.bosk.eksempler.f04.exceptions.runtime;

/*
 * Viser tre RuntimeExceptions - nemlig: 
 * NullPointerException, ArithmeticException og ClassCastException
 */

public class MoreRuntimeExceptions {
	// initialiseres automatisk til null
	static String s;

	public static void main(String[] args) {
		// Ex med NullPointerException
		try {
			System.out.println("Prøver s.trim() hvor s == null");
			s.trim();
		} catch (NullPointerException e) {
			System.out.println("\n\tGreb følgende fejl: " + e);
		}
		// Ex med ArithmeticException
		try {
			System.out.println("\n\n\nPrøver 12 / 0");
			int n = 12 / 0;
		} catch (ArithmeticException e) {
			System.out.println("\n\tGreb følgende fejl: " + e);
		}
		// Ex med ClassCastException (se klasserne nedenfor)
		try {
			System.out.println("\n\n\nPrøver: ");
			System.out.println("Role r = new Role();");
			System.out.println("StudentRole sr = (StudentRole)r;");
			Role r = new Role();
			StudentRole sr = (StudentRole) r;
		} catch (ClassCastException e) {
			System.out.println("\n\tGreb følgende fejl: " + e);
			e.printStackTrace();
		}
	}
}

class Role {
}

class StudentRole extends Role {
}
package bosk.ovelser.ovelse9.ovelse92;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {

		// Create a single person
		Person p = new Person(5, "Ole", "Jensen");
		System.out.println("Created the person: " + p);

		// Test if we can save a person to DB
		try {
			DbDataAccessObject.savePerson(p);
			System.out.println("Saved the person: " + p);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to save person");
			System.exit(1);
		}

		// Test if we can load a person from DB
		try {
			Person personFromDb = DbDataAccessObject.loadPerson(5);
			System.out.println("Loaded the person: " + personFromDb);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to load person");
			System.exit(1);
		}
	}
}

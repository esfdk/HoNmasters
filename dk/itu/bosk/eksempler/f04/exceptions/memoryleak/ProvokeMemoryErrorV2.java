package dk.itu.bosk.eksempler.f04.exceptions.memoryleak;

/*
 * Viser en Error - nemlig OutOfMemoryError, og at de i absurde situationer
 * kan h�ndteres - det er dog sj�ldent tilr�deligt at g�re det.
 */

import java.util.*;

public class ProvokeMemoryErrorV2 {
	public static void main(String[] args) {
		Collection c = new LinkedList();
		System.out.println("Starts to fill the list with Date-objects!");
		while (true) {
			try {
				c.add(new Date());
			} catch (OutOfMemoryError e) {
				c.clear();
				System.out.println(e + "\nJust cleared the list!");
				
				String msg = "\nStarts to fill the list with Date objects again!";
				System.out.println(msg);
			}
		}
	}
}

package dk.itu.bosk.eksempler.f04.exceptions.memoryleak;

/*
 * Viser en Error - nemlig OutOfMemoryError
 */
import java.util.*;

public class ProvokeMemoryErrorV1 {
	public static void main(String[] args) {
		Collection c = new LinkedList();
		while (true) {
			c.add(new Date());
		}
	}
}
/*
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
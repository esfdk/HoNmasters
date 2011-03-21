package dk.itu.bosk.eksempler.f04.exceptions.checked;

/*
 * Viser at Exceptions skal håndteres (modsat Errors og RuntimeExceptions)
 * Her vises det med metoden Object.clone(), der jf. API-dokumentationen
 * kaster en CloneNotSupportedException
 */

public class MustHandleCheckedExceptions {
	public static void main(String[] args) {
		Person p1 = new Person("Ib");

		// Nedenstående linie går ikke
		Person p2 = (Person) p1.clone();

		// Udkommenter nedenstående istedet
		/*
		 * try{ Person p2 = (Person)p1.clone(); } catch(
		 * CloneNotSupportedException e ){ System.out.println( "Her kommer vi
		 * aldrig ind..." + e ); }
		 */
		
	
	}
}

class Person implements Cloneable {
	String name;

	Person(String n) {
		this.name = n;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

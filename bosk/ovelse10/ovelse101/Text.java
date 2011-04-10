package bosk.ovelse10.ovelse101;

/*
* Skal bruges til ugens opgaver.
*
* Vha. Text.getTextAsLinkedList() kan du f� fat i en LinkedList, med en
* masse sm� bidder af en samlet tekst. K�r evt. Text, idet den har en
* main-metode, der demonstrerer indholdet af listen.
*
* Skal du have listens indhold over i en anden type samling - fx en
* ArrayList, kan det g�res s�dan her:
*
* ArrayList arrayList = new ArrayList();
* arrayList.addAll( Text.getTextAsLinkedList() );
*/
import java.util.*;
public class Text{
	public static void main(String[] args ){
		LinkedList list = Text.getTextAsLinkedList();
		Iterator i = list.iterator();
		while( i.hasNext() ){
			System.out.println( i.next() );
		}
	}
	public static LinkedList getTextAsLinkedList(){
		String s = "Det er morgen. Klinikassistenten fotokopierer den side fra aftalebogen, "
			+ "som viser dagens aftaler. Hun kigger dem igennem og tjekker, at det er de rigtige "
			+ "journaler, hun har fundet frem dagen f�r. Hun l�gger journalerne og kopien fra "
			+ "kalenderen ind p� bordet i frokoststuen. Tandl�gen s�tter sig ved bordet og skimmer "
			+ "journalerne for at se, om der er noget, hun skal v�re opm�rksom p�. Hun skriver noter "
			+ "ned p� kopien fra aftalebogen. Hun kan se, at Lise skal have lavet en plastfyldning, "
			+ "og at de har lavet en plastfyldning p� hende f�r - hun noterer ned p� dagsplanen "
			+ "hvilken farve plast, der skal bruges - og at Lise plejer at blive bed�vet. P� denne "
			+ "m�de er det let for klinikassistenten at finde de rigtige ting frem, f�r patienten "
			+ "kommer. P� samme m�de skriver hun noter om de �vrige af dagens patienter. Ved nogle "
			+ "patienter har hun skrevet en gul 'postit' med hvad hun skal lave. Det er dog kun ved "
			+ "st�rre behandlingsforl�b. Knap et kvarter senere er hun f�rdig med at gennemg� "
			+ "dagsplanen. Hun tager nu kontokortene ud af samtlige journaler og l�gger dem ud i "
			+ "receptionen til sekret�ren. Dagsplanen og journalerne tager hun med ind p� klinikken.";
		StringTokenizer st = new StringTokenizer( s, " .,-'", true );
		LinkedList list = new LinkedList();
		String current;
		while( st.hasMoreTokens() ){
			current = st.nextToken();
			if( ! current.equals( " " ) ){
				list.add( current.toLowerCase() );
			}
		}
		return list;
	}


}
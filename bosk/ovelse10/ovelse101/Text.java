package bosk.ovelse10.ovelse101;

/*
* Skal bruges til ugens opgaver.
*
* Vha. Text.getTextAsLinkedList() kan du få fat i en LinkedList, med en
* masse små bidder af en samlet tekst. Kør evt. Text, idet den har en
* main-metode, der demonstrerer indholdet af listen.
*
* Skal du have listens indhold over i en anden type samling - fx en
* ArrayList, kan det gøres sådan her:
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
			+ "journaler, hun har fundet frem dagen før. Hun lægger journalerne og kopien fra "
			+ "kalenderen ind på bordet i frokoststuen. Tandlægen sætter sig ved bordet og skimmer "
			+ "journalerne for at se, om der er noget, hun skal være opmærksom på. Hun skriver noter "
			+ "ned på kopien fra aftalebogen. Hun kan se, at Lise skal have lavet en plastfyldning, "
			+ "og at de har lavet en plastfyldning på hende før - hun noterer ned på dagsplanen "
			+ "hvilken farve plast, der skal bruges - og at Lise plejer at blive bedøvet. På denne "
			+ "måde er det let for klinikassistenten at finde de rigtige ting frem, før patienten "
			+ "kommer. På samme måde skriver hun noter om de øvrige af dagens patienter. Ved nogle "
			+ "patienter har hun skrevet en gul 'postit' med hvad hun skal lave. Det er dog kun ved "
			+ "større behandlingsforløb. Knap et kvarter senere er hun færdig med at gennemgå "
			+ "dagsplanen. Hun tager nu kontokortene ud af samtlige journaler og lægger dem ud i "
			+ "receptionen til sekretæren. Dagsplanen og journalerne tager hun med ind på klinikken.";
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